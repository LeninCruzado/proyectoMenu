# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bebida (
  id                        bigint not null,
  nombre                    varchar(255),
  stock                     integer,
  imagen                    varchar(255),
  constraint pk_bebida primary key (id))
;

create table cliente (
  id                        bigint not null,
  nombre                    varchar(255),
  login                     varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  direccion                 varchar(255),
  telf                      varchar(255),
  tipousuario_id            bigint,
  constraint pk_cliente primary key (id))
;

create table entrada (
  id                        bigint not null,
  nombre                    varchar(255),
  precio                    float,
  stock                     integer,
  imagen                    varchar(255),
  constraint pk_entrada primary key (id))
;

create table menu (
  id                        bigint not null,
  nombre                    varchar(255),
  tipomenu_id               bigint,
  constraint pk_menu primary key (id))
;

create table pedido (
  id                        bigint not null,
  nombre                    varchar(255),
  descripcion               varchar(255),
  estado                    varchar(255),
  plato_id                  bigint,
  cant_plato                integer,
  entrada_id                bigint,
  cant_entrada              integer,
  bebida_id                 bigint,
  cant_bebida               integer,
  postre_id                 bigint,
  cant_postre               integer,
  menu_id                   bigint,
  cant_menu                 integer,
  promocion_id              bigint,
  cant_promocion            integer,
  constraint pk_pedido primary key (id))
;

create table plato (
  id                        bigint not null,
  nombre                    varchar(255),
  precio                    float,
  stock                     integer,
  imagen                    varchar(255),
  constraint pk_plato primary key (id))
;

create table postre (
  id                        bigint not null,
  nombre                    varchar(255),
  stock                     integer,
  imagen                    varchar(255),
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

create table tipomenu (
  id                        bigint not null,
  nombre                    varchar(255),
  constraint pk_tipomenu primary key (id))
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

create sequence cliente_seq;

create sequence entrada_seq;

create sequence menu_seq;

create sequence pedido_seq;

create sequence plato_seq;

create sequence postre_seq;

create sequence promocion_seq;

create sequence tipomenu_seq;

create sequence tipousuario_seq;

create sequence usuario_seq;

alter table cliente add constraint fk_cliente_tipousuario_1 foreign key (tipousuario_id) references tipousuario (id) on delete restrict on update restrict;
create index ix_cliente_tipousuario_1 on cliente (tipousuario_id);
alter table menu add constraint fk_menu_tipomenu_2 foreign key (tipomenu_id) references tipomenu (id) on delete restrict on update restrict;
create index ix_menu_tipomenu_2 on menu (tipomenu_id);
alter table pedido add constraint fk_pedido_plato_3 foreign key (plato_id) references plato (id) on delete restrict on update restrict;
create index ix_pedido_plato_3 on pedido (plato_id);
alter table pedido add constraint fk_pedido_entrada_4 foreign key (entrada_id) references entrada (id) on delete restrict on update restrict;
create index ix_pedido_entrada_4 on pedido (entrada_id);
alter table pedido add constraint fk_pedido_bebida_5 foreign key (bebida_id) references bebida (id) on delete restrict on update restrict;
create index ix_pedido_bebida_5 on pedido (bebida_id);
alter table pedido add constraint fk_pedido_postre_6 foreign key (postre_id) references postre (id) on delete restrict on update restrict;
create index ix_pedido_postre_6 on pedido (postre_id);
alter table pedido add constraint fk_pedido_menu_7 foreign key (menu_id) references menu (id) on delete restrict on update restrict;
create index ix_pedido_menu_7 on pedido (menu_id);
alter table pedido add constraint fk_pedido_promocion_8 foreign key (promocion_id) references promocion (id) on delete restrict on update restrict;
create index ix_pedido_promocion_8 on pedido (promocion_id);
alter table usuario add constraint fk_usuario_tipousuario_9 foreign key (tipousuario_id) references tipousuario (id) on delete restrict on update restrict;
create index ix_usuario_tipousuario_9 on usuario (tipousuario_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bebida;

drop table if exists cliente;

drop table if exists entrada;

drop table if exists menu;

drop table if exists pedido;

drop table if exists plato;

drop table if exists postre;

drop table if exists promocion;

drop table if exists tipomenu;

drop table if exists tipousuario;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists bebida_seq;

drop sequence if exists cliente_seq;

drop sequence if exists entrada_seq;

drop sequence if exists menu_seq;

drop sequence if exists pedido_seq;

drop sequence if exists plato_seq;

drop sequence if exists postre_seq;

drop sequence if exists promocion_seq;

drop sequence if exists tipomenu_seq;

drop sequence if exists tipousuario_seq;

drop sequence if exists usuario_seq;

