# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups


create sequence s_task_id;

create table task (
  id                        bigint DEFAULT nextval('s_task_id'),
  label                     varchar(255),
  constraint pk_scala primary key (id))
;






# --- !Downs

drop table task;
drop sequence s_task_id;


