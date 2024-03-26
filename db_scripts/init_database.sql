-- MySQL Script generated by MySQL Workbench
-- Fri Mar 15 12:27:07 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ayu
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ayu
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ayu` DEFAULT CHARACTER SET utf8 ;
USE `ayu` ;

-- -----------------------------------------------------
-- Table `ayu`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `employee_number` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  `user_role` VARCHAR(45) NULL,
  `department_id` VARCHAR(45) NULL,
  `designation` VARCHAR(100) NULL,
  `password` VARCHAR(45) NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`store_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`store_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `type_id` INT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `created_date`),
  INDEX `type_idx` (`type_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  CONSTRAINT `type`
    FOREIGN KEY (`type_id`)
    REFERENCES `ayu`.`store_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`medicine_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`medicine_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`medicine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`medicine` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `code` VARCHAR(45) NULL,
  `type` INT NOT NULL COMMENT ' row matirial 0 or finish medicine 1',
  `medicine_type` INT NOT NULL COMMENT 'Kalka, choorna',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `medicine_type`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `mditype_idx` (`medicine_type` ASC) VISIBLE,
  CONSTRAINT `mditype`
    FOREIGN KEY (`medicine_type`)
    REFERENCES `ayu`.`medicine_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`stock_retrieval`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`stock_retrieval` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `source` VARCHAR(100) NULL,
  `store_id` INT NOT NULL,
  `request_by` VARCHAR(100) NOT NULL,
  `approved_by` VARCHAR(100) NOT NULL COMMENT ' row matirial 0 or finish medicine 1',
  `reference` VARCHAR(100) NULL COMMENT 'Kalka, choorna',
  `description` VARCHAR(1000) NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ayu`.`stock_retrieval_detail`
-- -----------------------------------------------------

CREATE TABLE `ayu`.`stock_retrieval_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` timestamp NULL DEFAULT NULL,
  `medicine_id` int NOT NULL,
  `qty` int NOT NULL,
  `stock_retrieval_id` int NOT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_stock_retrieve_idx` (`stock_retrieval_id`),
  CONSTRAINT `FK_stock_retrieve` FOREIGN KEY (`stock_retrieval_id`) REFERENCES `stock_retrieval` (`id`)
) ENGINE=InnoDB ;



-- -----------------------------------------------------
-- Table `ayu`.`stock_transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`stock_transfer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `from_store` INT NOT NULL,
  `to_store` INT NOT NULL,
  `approved_by` VARCHAR(100) NOT NULL COMMENT ' row matirial 0 or finish medicine 1',
  `reference` VARCHAR(100) NULL COMMENT 'Kalka, choorna',
  `description` VARCHAR(1000) NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`medicine_issue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`medicine_issue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `medicine_id` INT NOT NULL,
  `store_id` INT NULL COMMENT 'Kalka, choorna',
  `doctor_name` VARCHAR(45) NULL,
  `issued_by` VARCHAR(100) NULL,
  `issue_type` INT NOT NULL COMMENT ' issue_type OPD 0 or ward 1',
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`medicine_lot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`medicine_lot` (
`id` int NOT NULL AUTO_INCREMENT,
  `lot_num` varchar(100) NOT NULL,
  `expire_date` datetime NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `medicine_id` int DEFAULT NULL,
  `qty` int DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ayu`.`medicine_moment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ayu`.`medicine_moment` (
 `id` int NOT NULL AUTO_INCREMENT,
   `medicine_id` int NOT NULL,
   `reference_id` int NOT NULL,
   `lot_id` int NOT NULL,
   `store_id` int NOT NULL,
   `description` varchar(255) DEFAULT NULL,
   `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `in_qty` int DEFAULT NULL,
   `out_qty` int DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `batch_id_idx` (`lot_id`),
   KEY `medi_id_idx` (`medicine_id`),
   KEY `fk_store_idx` (`store_id`),
   CONSTRAINT `batch_id` FOREIGN KEY (`lot_id`) REFERENCES `medicine_lot` (`id`),
   CONSTRAINT `fk_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
   CONSTRAINT `medi_id` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`)
 )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
