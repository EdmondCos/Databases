create database users;
use users;

create table userz(
id int(2) auto_increment primary key, 
surname varchar(20) not null,
name varchar(20) not null,
mail varchar(20)
);

insert into userz (surname, name, mail)
values ("Mihai", "Popescu", "mihai.pop@gmail.com"),
("Andrei", "Vasile", "andre.vas@yahoo.com"),
("Ion", "Surub", "ion.shuru@gmail.com"),
("Radu", "Cristian", "isus69@yahoo.com"),
("Thor", "Asgard", "strongest@gmail.com");

insert into userz (surname, name, mail)
values ("Mihai", "Popescu", "mihai.pop@gmail.com");

alter table userz auto_increment = 1;
alter table userz add column age int default 18;
alter table userz add unique (age); -- conditie sa nu pui duplicate
alter table userz drop index age; -- scoti conditia de no duplicates

update userz set age = 43 where name = 'Cristian';
update userz set age = 21 where name = 'Vasile';
update userz set age = 21 where name = 'Asgard';

delete from userz where id>0;
select * from userz;
select * from userz where mail like '%GMAIL%';
select distinct age, name from userz;
select * from userz 
	where ((age between 18 and 21) and (mail like '%@yahoo.com') 
		or (age > 21 and mail like '%gmail.com'));
select name as nume, 
	surname as prenume,
    mail as 'e-mail',
    age as varsta
from userz;

select surname like 'M%' as 'incepe prenumele cu M?',
	name as nume
from userz;

select * from userz where age <26;
select * from userz where (age = (select max(age) from userz) 
						or age = (select min(age) from userz));
select count(*) as 'Total People' from userz;
select age as 'Varsta', count(*) as 'Total Users'
from userz
group by age;