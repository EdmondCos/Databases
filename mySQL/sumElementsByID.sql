create database x;
use x;

create table plays(
id integer not null,
title varchar(30),
writer varchar(30),
unique(id)
);

INSERT INTO plays (id, title, writer) VALUES
("1", "CEO", "Gabi"),
("2", "CFO", "Mihai"),
("3", "Manager", "Dan"),
("4", "Boss", "Dan");

create table reservations (
id integer not null,
play_id integer not null,
number_of_tickets integer not null,
unique (id)
);

INSERT INTO reservations (id, play_id, number_of_tickets) VALUES
("1", "1", "101"),
("2", "1", "200"),
("3", "1", "103"),
("4", "3", "51"),
("5", "3", "52"),
("6", "3", "53"),
("7", "2", "1001"),
("8", "2", "1002"),
("9", "4", "156");


SELECT P.id, P.title, R.tickets as "total bilete"
FROM plays P
LEFT JOIN (SELECT play_id, sum(number_of_tickets) as tickets from reservations group by play_id) R
ON P.id = R.play_id ORDER BY tickets;



