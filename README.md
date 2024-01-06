# TASK 11

## BookStore
Task 1**.**

Create an ER database diagram, a set of DDL scripts for creating a structure and a set of DML scripts that allow you to fill the database for your program from previous classes with test data. To create a .bat file that allows you to quickly install the database and fill it with test data.

Task 2.

Prepare model entities from the program written during the previous lessons for storage in the database (presence of id, correct connections).

Task 3.

Implement connection to the database created in step 1 in your application using jdbc.

Task 4.

Implement transaction support in the developed program. Add transactionality to application methods where necessary.

Task Requirements:

the application must comply with the principles of OOP;

the application must have a DAO level to work with the database;

using the GenericDAO design pattern;

the object of the class of obtaining a connection to the database, as well as the connection itself, must be in a single instance;

the application must have high-quality exception handling;

compliance with the principle of high cohesion;

all literals must be in constants;

jdbc configuration via properties;

the main CRUD methods should be implemented through an object-oriented approach (an object should come to the “input” to the method, at the “output” we get an object or a list of objects);

