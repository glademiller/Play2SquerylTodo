package models
import org.squeryl.{Schema, KeyedEntity}

case class Task(label: String) extends KeyedEntity[Long] {
    val id: Long = 0
}

object AppDB extends Schema {
    var taskTable = table[Task]("task")
}


