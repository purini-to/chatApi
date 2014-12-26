# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table authentication (
  id                        bigint not null,
  delete_flag               boolean,
  login_id                  varchar(255) not null,
  password                  varchar(255) not null,
  user_id                   bigint,
  created_date              timestamp not null,
  update_date               timestamp not null,
  constraint pk_authentication primary key (id))
;

create table issued_service_user (
  id                        bigint not null,
  delete_flag               boolean,
  service_name              varchar(255) not null,
  api_key                   varchar(255) not null,
  created_date              timestamp not null,
  update_date               timestamp not null,
  constraint pk_issued_service_user primary key (id))
;

create table user (
  id                        bigint not null,
  delete_flag               boolean,
  name                      varchar(255) not null,
  mail                      varchar(255),
  created_date              timestamp not null,
  update_date               timestamp not null,
  constraint pk_user primary key (id))
;

create table user_token (
  id                        bigint not null,
  delete_flag               boolean,
  token                     varchar(255) not null,
  validity_period           timestamp not null,
  user_id                   bigint,
  created_date              timestamp not null,
  update_date               timestamp not null,
  constraint pk_user_token primary key (id))
;

create sequence authentication_seq;

create sequence issued_service_user_seq;

create sequence user_seq;

create sequence user_token_seq;

alter table authentication add constraint fk_authentication_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_authentication_user_1 on authentication (user_id);
alter table user_token add constraint fk_user_token_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_token_user_2 on user_token (user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists authentication;

drop table if exists issued_service_user;

drop table if exists user;

drop table if exists user_token;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists authentication_seq;

drop sequence if exists issued_service_user_seq;

drop sequence if exists user_seq;

drop sequence if exists user_token_seq;

