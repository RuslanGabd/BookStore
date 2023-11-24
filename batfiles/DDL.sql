use bookstore;

CREATE TABLE `Book` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(25) NOT NULL,
    `author` VARCHAR(25) NOT NULL,
    `price`  INT NOT NULL,
	`status`  enum('IN_STOCK', 'NOT_AVAILABLE', 'OUT_OF_STOCK') NOT NULL,
	`description` VARCHAR(25) NULL,
     `datePublication` date NOT NULL,
        PRIMARY KEY (`id`)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


CREATE TABLE `Order` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `buyer` VARCHAR(50) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `status` enum('NEW', 'COMPLETED', 'CANCELLED'),
    `totalPrice` int,
    `dateCreated` date NOT NULL,
    `dateExecution` date, 
    PRIMARY KEY (`id`)
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `Request` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `bookid` INT UNSIGNED NOT NULL,
    `date` date NOT NULL,
   PRIMARY KEY (`id`),
            CONSTRAINT `Constr_RequestBook_Request_fk`
        FOREIGN KEY `Request` (`bookid`) REFERENCES `book` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE                
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `BooksOrder` (
    `BookID` INT UNSIGNED NOT NULL,
    `OrderID` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`BookID`, `OrderID`),
    CONSTRAINT `Constr_BooksOrder_Book_fk`
        FOREIGN KEY  (`BookID`) REFERENCES `book` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `Constr_BooksOrder_Order_fk`
        FOREIGN KEY (`OrderID`) REFERENCES `order` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;