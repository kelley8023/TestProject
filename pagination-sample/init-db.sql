DROP DATABASE IF EXISTS page;
CREATE DATABASE page;
USE page;
CREATE TABLE user (
  id       INT AUTO_INCREMENT PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  birthday DATETIME
);
DELIMITER //
CREATE PROCEDURE init_user(IN count INT)
  BEGIN
    DECLARE i INT;
    SET i = 1;
    WHILE i <= count DO
      INSERT INTO user (name, password, birthday) VALUES (concat('用户', i), concat('123456', i), now());
      SET i = i + 1;
    END WHILE;
  END//
DELIMITER ;
CALL init_user(202);

DELIMITER //
CREATE PROCEDURE user_counts(OUT count INT)
  BEGIN
    SELECT count(id)
    INTO count
    FROM user;
  END//
DELIMITER ;
