create table hibernate_sequence (next_val bigint) engine=MyISAM;
insert into hibernate_sequence values ( 1 );
create table operation (id bigint not null, number_n double precision not null, number_x double precision not null, number_y double precision not null, result double precision not null, primary key (id)) engine=MyISAM;

create table hibernate_sequence (next_val bigint) engine=MyISAM;
insert into hibernate_sequence values ( 1 );
create table operation (id bigint not null, number_n double precision not null, number_x double precision not null, number_y double precision not null, result double precision not null, primary key (id)) engine=MyISAM;
