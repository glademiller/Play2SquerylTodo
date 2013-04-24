package controllers

import play.api._
import play.api.mvc._


import play.api.data._
import play.api.data.Forms._
import play.api.data.Form

import org.squeryl.PrimitiveTypeMode._
import models.{AppDB,Task}





object Application extends Controller {
  val taskForm = Form(
    mapping(
      "label" -> nonEmptyText
    )(Task.apply)(Task.unapply)
  )

  def getTasks = {
    inTransaction {
      from(AppDB.taskTable)(taskTable => select(taskTable)).toList
    }
  }

  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    val tasks = getTasks
    Ok(views.html.index(tasks,taskForm))
  }

  def newTask = Action { implicit request => 
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(getTasks,errors)),
      task => {
        inTransaction(AppDB.taskTable insert task)
        Redirect(routes.Application.tasks)        
      }
    )
  }

  def deleteTask(id: Long) = Action {
    inTransaction {
      AppDB.taskTable.delete(id)
    }

    Redirect(routes.Application.tasks)

  }
  
}