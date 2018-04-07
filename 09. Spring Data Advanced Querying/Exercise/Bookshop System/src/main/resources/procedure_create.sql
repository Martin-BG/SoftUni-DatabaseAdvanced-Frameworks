USE `bookshop_system`;

DROP PROCEDURE IF EXISTS udp_find_books_by_author;

DELIMITER $$
CREATE PROCEDURE udp_find_books_by_author(first_name VARCHAR(255), last_name VARCHAR(255))
BEGIN
    SELECT 
        COUNT(b.book_id)
    FROM
        `books` AS b
            JOIN
        `authors` AS a ON b.author_id = a.author_id
    WHERE
        a.first_name = first_name
            AND a.last_name = last_name
    GROUP BY a.author_id;
END $$
DELIMITER ;

#CALL udp_find_books_by_author('Amanda', 'Rice');
