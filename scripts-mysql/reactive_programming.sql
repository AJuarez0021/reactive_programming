-- DROP DATABASE "reactive_programming" ------------
DROP DATABASE IF EXISTS `reactive_programming`;
-- ---------------------------------------------------------

-- CREATE DATABASE "reactive_programming" ----------
CREATE DATABASE `reactive_programming` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `reactive_programming`;

-- CREATE TABLE "courses" --------------------------------
CREATE TABLE `courses` ( 
     `courses_id` int(10) UNSIGNED AUTO_INCREMENT NOT NULL,    
     `name`       varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
     `acronym`    varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,     
     `status`     boolean NOT NULL,
     `creation_datetime` datetime NOT NULL,
     `modification_datetime` datetime NULL,
     PRIMARY KEY ( `courses_id` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 0;


-- CREATE TABLE "student" --------------------------------
CREATE TABLE `student` ( 
     `student_id` int(10) UNSIGNED AUTO_INCREMENT NOT NULL,    
     `courses_id` int(10) UNSIGNED NOT NULL,
     `names`      varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
     `surnames`   varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
     `curp`       varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
     `age`        int(3) UNSIGNED NOT NULL,
     `creation_datetime` datetime NOT NULL,
     `modification_datetime` datetime NULL,
     PRIMARY KEY ( `student_id` ))
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB
AUTO_INCREMENT = 0;

