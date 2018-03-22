# noinspection SqlNoDataSourceInspectionForFile

-- 01. Initial Setup
-- Create new database called “MinionsDB” where we will keep information about our minions and villains. 
-- For each minion keep information about its name, age and town.  
-- Each town has name and information about in which country is located. 
-- Villains have name and evilness factor (good, bad, evil, super evil). 
-- Each minion can serve to several villains and each villain can have several minions to serve him. 
-- Fill all tables with at least 5 records each.

DROP DATABASE IF EXISTS `minions_db`;

CREATE DATABASE `minions_db`;

USE `minions_db`;

CREATE TABLE `towns` (
  `id`      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `name`    VARCHAR(50) NOT NULL UNIQUE,
  `country` VARCHAR(50) NOT NULL
);

CREATE TABLE `minions` (
  `id`      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  `name`    VARCHAR(50)      NOT NULL UNIQUE,
  `age`     TINYINT UNSIGNED NOT NULL,
  `town_id` INT UNSIGNED     NOT NULL,
  CONSTRAINT `fk_minions_towns` FOREIGN KEY (`town_id`)
  REFERENCES `towns` (`id`)
);

CREATE TABLE `villains` (
  `id`              INT UNSIGNED                                        AUTO_INCREMENT PRIMARY KEY,
  `name`            VARCHAR(50)                                NOT NULL UNIQUE,
  `evilness_factor` ENUM ('good', 'bad', 'evil', 'super evil') NOT NULL DEFAULT 'good'
);

CREATE TABLE `minions_villains` (
  `minion_id`  INT UNSIGNED NOT NULL,
  `villain_id` INT UNSIGNED NOT NULL,
  CONSTRAINT `fk_minions_villains_minions` FOREIGN KEY (`minion_id`)
  REFERENCES `minions` (`id`),
  CONSTRAINT `fk_minions_villains_villains` FOREIGN KEY (`villain_id`)
  REFERENCES `villains` (`id`),
  CONSTRAINT `pk_minions_villains` PRIMARY KEY (`minion_id`, `villain_id`)
);

INSERT INTO `towns`
(`name`, `country`)
VALUES
  ('Sofia', 'Bulgaria'),
  ('Varna', 'Bulgaria'),
  ('Plovdiv', 'Bulgaria'),
  ('Berlin', 'Germany'),
  ('Paris', 'France');


INSERT INTO `minions`
(`name`, `age`, `town_id`)
VALUES
  ('Bob', 10, 1),
  ('Kevin', 13, 3),
  ('Steward', 8, 2),
  ('Jimmy', 16, 5),
  ('Dodo', 23, 4);

INSERT INTO `villains`
(`name`, `evilness_factor`)
VALUES
  ('Gru', 'good'),
  ('Victor', 'super evil'),
  ('Koko', 'evil'),
  ('Juji', 'bad'),
  ('Misho', 'good');

INSERT INTO `minions_villains`
VALUES
  (1, 1),
  (3, 1),
  (1, 2),
  (2, 2),
  (4, 5),
  (2, 1),
  (5, 5),
  (4, 1),
  (4, 2),
  (4, 3),
  (5, 1),
  (5, 2),
  (5, 3),
  (5, 4),
  (3, 3);

-- 02. Get Villains’ Names
-- Write a program that prints on the console all villains’ names and their number of 
-- minions of those who has more than 3 minions ordered descending by number of minions.

SELECT *
FROM
  (SELECT
     v.name,
     COUNT(mv.minion_id) AS 'mn'
   FROM
     `villains` AS v
     JOIN `minions_villains` AS mv ON v.id = mv.villain_id
   GROUP BY v.id
   ORDER BY `mn` DESC) AS vm
WHERE
  vm.mn >= 3;