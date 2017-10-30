--Este SQL se mantiene actualizado con la última versión de las tablas a generar.
--Los SQL de updates van actualizando las tablas conforme los cambios, siendo el primero de ellos la primera versión de éste.

drop table if exists geo_galicia cascade;
drop table if exists provincia cascade;
drop table if exists municipio cascade;
drop table if exists parroquia cascade;
drop table if exists lugar cascade;
drop table if exists informante cascade;
drop table if exists audicion cascade;
drop table if exists xuizo cascade;
drop table if exists pregunta cascade;
drop table if exists respuesta cascade;
drop table if exists respuesta_informante cascade;
drop table if exists informante_outras_residencias cascade;
drop sequence if exists id_informante;
drop sequence if exists id_audicion;
drop sequence if exists id_xuizo;
drop sequence if exists id_respuesta_informante;
drop sequence if exists id_respuesta;
drop sequence if exists id_pregunta;


-- Tablas xeradas do nomenclator
create table geo_galicia (
 geo geometry(MULTIPOLYGON, 4258)
);

create table provincia (
  codigo char(2) primary key,
  nome varchar(255) not null,
  capital char(9),
  geo geometry(MULTIPOLYGON, 4258)
);

create table municipio (
  codigo char(5) primary key,
  nome varchar(255) not null,
  provincia char(2) not null 
            references provincia(codigo) 
                       on delete restrict
                       on update cascade,
  poblacion integer,
  capital char(9),
  geo geometry(MULTIPOLYGON, 4258)
);

create table parroquia (
  codigo char(7) primary key,
  nome varchar(255) not null,
  municipio char(5) not null
            references municipio(codigo)
                       on delete restrict
                       on update cascade
);

create table lugar (
  codigo char(9) primary key,
  nome varchar(255) not null,
  municipio char(5) not null
            references municipio(codigo)
                       on delete restrict
                       on update cascade,
  parroquia char(7)
            references parroquia(codigo)
                       on delete restrict
                       on update cascade,
  poblacion integer,
  geo geometry(POINT, 4258)
);

alter table provincia 
    add foreign key (capital)
        references lugar(codigo)
                    on delete restrict
                    on update cascade;

alter table municipio 
    add foreign key (capital)
        references lugar(codigo)
                    on delete restrict
                    on update cascade;

-- Táboas da enquisa para almacenar informantes, audicións e xuizos.

create sequence id_informante;
create table informante (
  id_informante bigint default nextval('id_informante') primary key,
  ano_nacemento integer not null,
  sexo char(1) not null,
  estudos varchar(50) not null,
  ocupacion varchar(50) not null,
  outro_nacemento text,
  outro_residencia text,
  nacemento_pais_coincide boolean not null default false,
  lugar_nacemento char(9)
                  references lugar(codigo)
                             on delete restrict
                             on update cascade,
  lugar_residencia char(9)
                  references lugar(codigo)
                             on delete restrict
                             on update cascade,
  viviu_fora_galicia boolean not null default false,
  lingua_nativa char(13) not null,
  lingua_falada char(13) not null,
  lugares_desprazamento text,
  grao_diferenza_fala varchar(50) not null,
  mellor_galego text,
  peor_galego text,
  diferente_galego text
);

create table informante_outras_residencias (
  informante bigint not null references informante,
  municipio char(5) not null references municipio,
  primary key (informante, municipio)
);

create sequence id_audicion;
create table audicion (
  id_audicion bigint default nextval('id_audicion') primary key,
  titulo varchar(500),
  path varchar(500),
  visible boolean,
  lugarAudicion char(9) 
  				  references lugar(codigo)
                             on delete restrict
                             on update cascade
);

create sequence id_xuizo;
create table xuizo (
  id bigint default nextval('id_xuizo') primary key,
  informante integer not null
             references informante(id_informante)
                        on delete restrict
                        on update cascade,
  audicion integer not null
             references audicion(id_audicion)
                        on delete restrict
                        on update cascade,
  geo geometry(POLYGON, 4258),
  parecido integer,

  unique (informante, audicion)
 );