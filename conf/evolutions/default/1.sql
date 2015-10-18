# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bebida (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_bebida primary key (id))
;

create table entrada (
  id                        bigint not null,
  name                      varchar(255),
  price                     varchar(255),
  constraint pk_entrada primary key (id))
;

create table menu (
  id                        bigint not null,
  name                      varchar(255),
  created                   timestamp,
  updated                   timestamp,
  tipo_menu_id              bigint,
  entrada_id                bigint,
  plato_id                  bigint,
  bebida_id                 bigint,
  postre_id                 bigint,
  constraint pk_menu primary key (id))
;

create table plato (
  id                        bigint not null,
  name                      varchar(255),
  price                     varchar(255),
  constraint pk_plato primary key (id))
;

create table postre (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_postre primary key (id))
;

create table promocion (
  id                        bigint not null,
  nombre                    varchar(255),
  descripcion               varchar(255),
  precio_ant                float,
  precio_act                float,
  constraint pk_promocion primary key (id))
;

create table tipo_menu (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_tipo_menu primary key (id))
;

create sequence bebida_seq;

create sequence entrada_seq;

create sequence menu_seq;

create sequence plato_seq;

create sequence postre_seq;

create sequence promocion_seq;

create sequence tipo_menu_seq;

alter table menu add constraint fk_menu_tipoMenu_1 foreign key (tipo_menu_id) references tipo_menu (id) on delete restrict on update restrict;
create index ix_menu_tipoMenu_1 on menu (tipo_menu_id);
alter table menu add constraint fk_menu_entrada_2 foreign key (entrada_id) references entrada (id) on delete restrict on update restrict;
create index ix_menu_entrada_2 on menu (entrada_id);
alter table menu add constraint fk_menu_plato_3 foreign key (plato_id) references plato (id) on delete restrict on update restrict;
create index ix_menu_plato_3 on menu (plato_id);
alter table menu add constraint fk_menu_bebida_4 foreign key (bebida_id) references bebida (id) on delete restrict on update restrict;
create index ix_menu_bebida_4 on menu (bebida_id);
alter table menu add constraint fk_menu_postre_5 foreign key (postre_id) references postre (id) on delete restrict on update restrict;
create index ix_menu_postre_5 on menu (postre_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bebida;

drop table if exists entrada;

drop table if exists menu;

drop table if exists plato;

drop table if exists postre;

drop table if exists promocion;

drop table if exists tipo_menu;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists bebida_seq;

drop sequence if exists entrada_seq;

drop sequence if exists menu_seq;

drop sequence if exists plato_seq;

drop sequence if exists postre_seq;

drop sequence if exists promocion_seq;

drop sequence if exists tipo_menu_seq;

