create database depozit;
use depozit;

create table produse(
ID_Produs int(2) auto_increment primary key,
Cod_Produs int(3) not null,
Nume_Produs varchar(30),
Cantitate int(5) not null,
Pret double(7,2)
);

show tables;
describe produse;

insert into produse(Cod_Produs, Nume_Produs, Cantitate, Pret)
values (11, "Caiet A4", 325, 4000.25),
(32, "Pix Negru", 1212, 2000.00),
(15, "Calculator Stintifiic", 112, 55000.75),
(32, "Pix Albastru", 1013, 2000.20),
(33, "Creion", 818, 1000.20),
(52, "Top Hartie", 52, 60000.00),
(99, "Dosar Sina", 100, 12000.60),
(32, "Capse Hartie", 455, 2000.00),
(27, "Capsator", 76, 12000.20),
(7, "Agrafe Birou", 12654, 100.25);

-- 5. Pret mai mic de 55000
select * from produse where Pret < 55000.00;

-- 6. Cantitate cel putin 100
select * from produse where Cantitate > 100;

-- 7. Produse care incep cu litera "C" si au pret intre 1000 si 10000
select * from produse 
	where (Nume_Produs like 'C%') and (Pret between 1000.00 and 10000.00);
    
-- 8. Afisati nume si cod produs si valoarea toatala(Cantitate*Pret) afisat ca valoare totala
select Cod_Produs, Nume_Produs,
	Cantitate*Pret as 'Valoare_Totala'
from produse;

-- 9. Afisati numar total produse
select sum(Cantitate) as 'Cantitate_finala' from produse;

-- 10. Selectati produsul cu pretul cel mai mic/mare afisati ca Ieftin/Scump
select Pret as 'Cel_mai_ieftin' from produse where Pret = (select min(Pret) from produse);
select Pret as 'Cel_mai_scump' from produse where Pret = (select max(Pret) from produse);
-- select * from Produse where Pret  = (select max(pret) from Produse) or pret = (select min(pret) from Produse);

-- 11. Selectati suma produselor in cantitate impara si produsul celor in cantitate para
select sum(Cantitate) as 'Toatal_Par' from produse where Cantitate%2 = 0;
select sum(Cantitate) as 'Toatal_Impar' from produse where Cantitate%2 != 0;


select * from produse group by Pret;
