-- task 1
SELECT
        model,
        speed,
        hd
FROM
        pc
WHERE
        price<500;
-- task 2
SELECT
        maker
FROM
        product
WHERE
        type IN ('printer');
-- task 3
SELECT
        model,
        ram  ,
        screen
FROM
        laptop
WHERE
        price>1000;
-- task 4
SELECT
        *
FROM
        printer
WHERE
        color='Y';
-- task 5
SELECT
        model,
        speed,
        hd
FROM
        pc
WHERE
        price<600
AND     cd IN ('12x',
              '24x');
-- task 6
SELECT
        p.maker,
        l.speed
FROM
        laptop AS l
JOIN
        product AS p
ON
        l.model=p.model
WHERE
        l.hd>100;
-- task 7
SELECT
        p.model,
        l.price AS price
FROM
        laptop l
JOIN
        product p
ON
        p.model=l.model
WHERE
        p.maker = 'B'

UNION

SELECT
        p.model,
        pc.price AS price
FROM
        pc
JOIN
        product p
ON
        p.model=pc.model
WHERE
        p.maker = 'B'

UNION

SELECT
        p.model,
        prin.price AS price
FROM
        printer prin
JOIN
        product p
ON
        p.model=prin.model
WHERE
        p.maker = 'B';
-- task 8
SELECT DISTINCT
        maker
FROM
        Product
WHERE
        type = 'PC'
AND     maker NOT IN
        (
                SELECT
                        maker
                FROM
                        Product
                WHERE
                        type = 'Laptop');
-- task 9
SELECT DISTINCT
        maker
FROM
        Product p
JOIN
        pc
ON
        p.model=pc.model
WHERE
        pc.speed<450;
-- task 10
SELECT
        model,
        max(price)
FROM
        printer;
-- task 11
SELECT
        avg(speed)
FROM
        pc;
-- task 12
SELECT
        avg(speed)
FROM
        laptop
WHERE
        price>1200;
-- task 13
-- FINd the average speed of PCs releASed by manufacturer A.
SELECT
        avg(pc.speed)
FROM
        product p
RIGHT JOIN
        pc
ON
        pc.model=p.model
WHERE
        p.maker='A';
-- task 14
-- For each speed value, fINd the average cost of a
-- PC with the same processor speed. BrINg out: speed, average price
SELECT
        speed,
        avg(price)
FROM
        pc
GROUP BY
        speed;
-- task 15
-- FINd the sizes of hard drives that match two or more PCs. Output: HD
SELECT
        hd
FROM
        pc
GROUP BY
        hd
HAVING
        COUNT(hd)>1;
-- task 16
SELECT DISTINCT
        pca.model,
        pcb.model,
        pca.speed,
        pca.ram
FROM
        pc pca
JOIN
        pc pcb
ON
        (
                pca.speed = pcb.speed
        and pca.ram       = pcb.ram
        and pca.model     > pcb.model);
-- task 17
SELECT
        p.type ,
        l.model,
        l.speed
FROM
        laptop l
JOIN
        product p
ON
        l.model=p.model
WHERE
        l.speed <
        (
                SELECT
                        MIN(speed)
                FROM
                        pc);
-- task 18
SELECT
        p.maker,
        pch.price
FROM
        product p
JOIN
        (
                SELECT
                        model,
                        MIN(price) AS price
                FROM
                        printer
                WHERE
                        color = 'Y') pch
ON
        p.model=pch.model;
-- task 19
SELECT
        pr.maker,
        AVG(l.screen)
FROM
        laptop l
JOIN
        product pr
ON
        pr.model = l.model
GROUP BY
        pr.maker;
-- task 20
SELECT
        maker,
        COUNT(1)
FROM
        product
WHERE
        type = 'pc'
GROUP BY
        maker
HAVING
        COUNT(1) >= 3;
-- task 21
SELECT
        product.maker,
        MAX(pc.price)
FROM
        product,
        pc
WHERE
        product.model = pc.model
GROUP BY
        product.maker;
-- task 22
SELECT
        speed,
        AVG(price) AS avg_price
FROM
        pc
WHERE
        speed>600
GROUP BY
        speed;
-- task 23
SELECT
        maker
FROM
        product pr
JOIN
        pc
ON
        pc.model= pr.model
WHERE
        pc.speed>750
AND     maker IN
        (
                SELECT
                        maker
                FROM
                        product pr
                JOIN
                        laptop l
                ON
                        l.model= pr.model
                WHERE
                        l.speed>750);
-- task 24
SELECT
        model
FROM
        (
                SELECT
                        model,
                        max(price)
                FROM
                        pc
                
                UNION
                
                SELECT
                        model,
                        max(price)
                FROM
                        laptop
                
                UNION
                
                SELECT
                        model,
                        max(price)
                FROM
                        printer) AS
List;
-- task 25
SELECT DISTINCT
        p.maker
FROM
        product p
WHERE
        type = 'printer'
AND     maker IN
        (
                SELECT
                        maker
                FROM
                        product p,
                        pc
                WHERE
                        p.model = pc.model
                AND     pc.ram IN
                        (
                                SELECT
                                        MIN(ram)
                                FROM
                                        pc)
                AND     pc.speed IN
                        (
                                SELECT
                                        max(speed)
                                FROM
                                        pc
                                WHERE
                                        ram IN
                                        (
                                                SELECT
                                                        MIN(ram)
                                                FROM
                                                        pc)));