-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema rigor
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rigor
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rigor` DEFAULT CHARACTER SET utf8 ;
USE `rigor` ;

-- -----------------------------------------------------
-- Table `rigor`.`feedback_tbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rigor`.`feedback_tbl` (
  `feedback_id` VARCHAR(10) NOT NULL,
  `date` DATETIME NULL,
  `type` VARCHAR(45) NULL,
  `user_id` VARCHAR(10) NULL,
  `dept_id` VARCHAR(10) NULL,
  `category_id` VARCHAR(10) NULL,
  `description` VARCHAR(300) NULL,
  `status` INT NULL,
  PRIMARY KEY (`feedback_id`),
  INDEX `user_fk` (`user_id` ASC),
  INDEX `cat_fk` (`category_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rigor`.`user_tbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rigor`.`user_tbl` (
  `user_id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(45) NULL,
  `emp_no` INT NULL,
  `email` VARCHAR(100) NULL,
  `password` VARCHAR(300) NULL,
  `role_id` VARCHAR(10) NULL,
  `status` INT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `role_fk` (`role_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `rigor`.`feedback_tbl` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rigor`.`role_tbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rigor`.`role_tbl` (
  `role_id` VARCHAR(10) NOT NULL,
  `description` VARCHAR(100) NULL,
  `status` INT NULL,
  PRIMARY KEY (`role_id`),
  CONSTRAINT `role_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `rigor`.`user_tbl` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rigor`.`category_tbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rigor`.`category_tbl` (
  `category_id` VARCHAR(10) NOT NULL,
  `depart_id` VARCHAR(10) NULL,
  `description` VARCHAR(100) NULL,
  `status` INT NULL,
  PRIMARY KEY (`category_id`),
  INDEX `dept_fk` (`depart_id` ASC),
  CONSTRAINT `cat_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `rigor`.`feedback_tbl` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rigor`.`dept_tbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rigor`.`dept_tbl` (
  `dept_id` VARCHAR(10) NOT NULL,
  `dept_name` VARCHAR(100) NULL,
  `status` INT NULL,
  PRIMARY KEY (`dept_id`),
  CONSTRAINT `dept_fk`
    FOREIGN KEY (`dept_id`)
    REFERENCES `rigor`.`feedback_tbl` (`dept_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cat_fk`
    FOREIGN KEY (`dept_id`)
    REFERENCES `rigor`.`category_tbl` (`depart_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
