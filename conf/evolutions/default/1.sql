# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bebida (
  id                        bigint not null,
  nombre                    varchar(255),
  stock                     integer,
  constraint pk_bebida primary key (id))
;

create table entrada (
  id                        bigint not null,
  nombre                    varchar(255),
  precio                    float,
  stock                     integer,
  constraint pk_entrada primary key (id))
;

create table plato (
  id                        bigint not null,
  nombre                    varchar(255),
  precio                    float,
  stock                     integer,
  constraint pk_plato primary key (id))
;

create table postre (
  id                        bigint not null,
  nombre                    varchar(255),
  stock                     integer,
  constraint pk_postre primary key (id))
;

create table promocion (
  id                        bigint not null,
  nombre                    varchar(255),
  descripcion               varchar(255),
  precio_ant                float,
  precio_act                float,
  stock                     integer,
  constraint pk_promocion primary key (id))
;

create table tipo_menu (
  id                        bigint not null,
  nombre                    varchar(255),
  constraint pk_tipo_menu primary key (id))
;

create table tipousuario (
  id                        bigint not null,
  nombre                    varchar(255),
  constraint pk_tipousuario primary key (id))
;

create table usuario (
  id                        bigint not null,
  nombre                    varchar(255),
  login                     varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  tipousuario_id            bigint,
  constraint pk_usuario primary key (id))
;

create sequence bebida_seq;

create sequence entrada_seq;

create sequence plato_seq;

create sequence postre_seq;

create sequence promocion_seq;

create sequence tipo_menu_seq;

create sequence tipousuario_seq;

create sequence usuario_seq;

alter table usuario add constraint fk_usuario_tipousuario_1 foreign key (tipousuario_id) references tipousuario (id) on delete restrict on update restrict;
create index ix_usuario_tipousuario_1 on usuario (tipousuario_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bebida;

drop table if exists entrada;

drop table if exists plato;

drop table if exists postre;

drop table if exists promocion;

drop table if exists tipo_menu;

drop table if exists tipousuario;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists bebida_seq;

drop sequence if exists entrada_seq;

drop sequence if exists plato_seq;

drop sequence if exists postre_seq;

drop sequence if exists promocion_seq;

drop sequence if exists tipo_menu_seq;

drop sequence if exists tipousuario_seq;

drop sequence if exists usuario_seq;

