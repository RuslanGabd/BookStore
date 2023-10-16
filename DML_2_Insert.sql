INSERT INTO product (maker, model, type)
VALUES ('HP', 'DSF100', 'Laptop'),
	   ('HP', 'HP300', 'PC'),
	   ('B', 'NRK300', 'Laptop'),
       ('B', 'NRK500', 'Laptop'),
	   ('B', 'Power1000', 'PC'),
       ('B', 'NRK700', 'Laptop'),
	   ('B', 'NRK750', 'Laptop'),
       ('A', 'IBM750', 'PC'),
       ('A', 'IBM950', 'PC'),
	   ('A', 'IBM1000', 'PC'),
	   ('A','IBM350', 'PC'),
       ('HP', 'HP1020', 'Printer'),
       ('Canon', 'SC3000', 'Printer'),
       ('Canon', 'ColorJet200', 'Printer');
       
       
INSERT INTO laptop (model, speed, ram, hd, cd, price, screen)
VALUES ('DSF100', 600, 8, 120, '12x', 500, 15),
	   ('NRK300', 300, 8, 80, '12x', 800, 13),
	   ('NRK500', 500, 12, 90, '24x', 1200, 15),
	   ('NRK700', 700, 12, 90, '24x', 1400, 15),
	   ('NRK750', 760, 12, 90, '24x', 1500, 15);
 
 
        
INSERT INTO pc (model, speed, ram, hd, cd, price)
VALUES ('Power1000', 1000, 12, 900, '24x', 300),
       ('HP300', 800, 4, 900, '24x', 500),
	   ('IBM350', 350, 4, 200, '8x', 300),
	   ('IBM750', 750, 9, 800, '12x', 600),
       ('IBM950', 950, 12, 1000, '12x', 800),
       ('IBM1000', 1000, 12, 600, '16x', 1250);
       
       
INSERT INTO printer (model, color, type, price)
VALUES ('HP1020', 'N', 'laser', 300),
	   ('SC3000', 'N', 'ink', 100),
       ('ColorJet200', 'Y', 'ink', 400);

SELECT * FROM product;       
SELECT * FROM pc;
SELECT * FROM laptop;
SELECT * FROM printer;