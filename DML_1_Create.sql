CREATE TABLE product (
maker varchar(10) NOT NULL,
model varchar (50) PRIMARY KEY NOT NULL,
type varchar (50) NOT NULL
);

CREATE TABLE pc (
code int PRIMARY KEY AUTO_INCREMENT NOT NULL,
model varchar (50) ,
speed smallint NOT NULL,
ram smallint NOT NULL,
hd real NOT NULL,
cd varchar(10) NOT NULL,
price decimal(15,2),
FOREIGN KEY (model) REFERENCES product (model)
);
CREATE TABLE laptop (
code int PRIMARY KEY AUTO_INCREMENT NOT NULL,
model varchar (50) ,
speed smallint NOT NULL,
ram smallint NOT NULL,
hd real NOT NULL,
cd varchar(10) NOT NULL,
price decimal(15,2),
screen tinyint NOT NULL,
FOREIGN KEY (model) REFERENCES product (model)
);

CREATE TABLE printer (
code int PRIMARY KEY AUTO_INCREMENT NOT NULL,
model varchar (50) ,
color char(1) NOT NULL,
type varchar(10) NOT NULL,
price decimal(15,2),
FOREIGN KEY (model) REFERENCES product (model)
)