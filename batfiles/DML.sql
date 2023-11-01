use bookstore;
INSERT INTO book  (`Title`, `Author`, `Price`, `Status`,`Description`, `DatePublication`)
values	('Finalist','Teodor D','100','IN_STOCK',null,'2023-06-05'),
('Silver','Jack London','50','IN_STOCK',null,'2023-05-21'),
('Chemistry','Mohjan P.','180','IN_STOCK',null,'2023-04-24'),
('King','Sven Richi','90','IN_STOCK',null,'2023-03-17'),
('Wolf','Tramp K.','10','IN_STOCK',null,'2023-02-11'),
 ('Fox','Brus Li','146','IN_STOCK',null,'2023-01-05'),
 ('Cat','Uma Turman','123','IN_STOCK',null,'2022-12-03');
 

INSERT INTO `order` (`Buyer`, `Address`,  `Status`,`TotalPrice`,`DateCreated`, `DateExecution`)
VALUES
( 'Klinton M.L.','address one','NEW',150,'2023-06-30 00:00:00','2023-06-30 00:00:00'),
('Ruslan','443031','NEW','150','2023-01-30',null),
('Sergei','st. Coast 7','NEW',270,'2023-02-15',null),
('Mike','st. New River ap. 8','CANCELLED',246,'2023-03-01',null),
('Mike','st. New River ap. 8','NEW',246,'2023-04-10',null),
('Lilla','New York','NEW',256,'2023-02-25',null),
('Ollya','Texas','NEW',336,'2023-05-06',null);

INSERT INTO `request` ( `BookID`,`Date`)
VALUES	('5','2023-09-13'),
('1','2023-06-05'),
('2','2023-09-13');

INSERT INTO booksorder (`BookID`, `OrderID`)
VALUES ('1','1'),
('2','1'),
('3','2'),
('4','2'),
('4','3'),
('5','3'),
('6','3'),
('4','4'),
('5','4'),
('6','4'),
('1','5'),
('5','5'),
('6','5'),
('3','6'),
('5','6'),
('6','6');

