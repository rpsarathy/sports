DROP TABLE IF EXISTS PLAYERS_SPORTS;

delete from PLAYERS;

insert into PLAYERS(id,email,age,gender,level) values (1,'Sarathy@gmail.com',40,'male',10);
insert into PLAYERS(id,email,age,gender,level) values (2,'Sam@gmail.com',20,'male',5);
insert into PLAYERS(id,email,age,gender,level) values (3,'Paul@gmail.com',30,'male',3);

delete from SPORTS;

insert into SPORTS(id, name ) values (1,'tennis');
insert into SPORTS(id, name ) values (2,'soccer');
insert into SPORTS(id, name ) values (3,'basketball');

create table players_sports (players_id integer not null, sports_id integer not null, primary key (players_id, sports_id));
alter table if exists players_sports add constraint FKoab55663r3kqvlo7ftjibcp5m foreign key (sports_id) references sports;
alter table if exists players_sports add constraint FKksbu2cfhs4fdsoq46wkudfmk5 foreign key (players_id) references players;

insert into PLAYERS_SPORTS (PLAYERS_ID ,SPORTS_ID ) values (1,1);
insert into PLAYERS_SPORTS (PLAYERS_ID ,SPORTS_ID ) values (1,2);
insert into PLAYERS_SPORTS (PLAYERS_ID ,SPORTS_ID ) values (1,3);
insert into PLAYERS_SPORTS (PLAYERS_ID ,SPORTS_ID ) values (2,3);

