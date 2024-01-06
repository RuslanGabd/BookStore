# Senla_hw_10

##Task 1 (difficulty 3)

Write DDL scripts to create the database described below. Create a DML script to populate the resulting database with test data.

Database schema:

Description of tables:

Product (maker, model, type)

PC (code, model, speed, ram, hd, cd, price)

Laptop (code, model, speed, ram, hd, screen, price)

Printer (code, model, color, type, price)

The Product table represents the manufacturer (maker), model number (model) and type ('PC' - PC, 'Laptop' - PC-notepad or 'Printer' - printer). It is assumed that the model numbers in the Product table are unique for all manufacturers and product types.

In the PC table, for each PC, uniquely determined by a unique code – code, the model – model (the foreign key to the Product table), speed - speed (processor in megahertz), memory - ram (in megabytes), disk size - hd (in gigabytes), the speed of the reader - cd (for example, '4x') and price - price.

The Laptop table is similar to the PC table, except that instead of the CD speed, it contains the screen size (in inches). The Printer table for each printer model indicates whether it is color - color ('y' if color), printer type - type (laser – 'Laser', inkjet – 'Jet' or matrix – 'Matrix') and price - price.

Task 2 (difficulty 7)

Solve all the tasks on the database created in the first task.
