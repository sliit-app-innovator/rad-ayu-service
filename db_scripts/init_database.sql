CREATE SCHEMA IF NOT EXISTS `ayu` DEFAULT CHARACTER SET utf8 ;
USE `ayu` ;

-- -----------------------------------------------------
-- Table `hgcmip`.`config_data_point`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ayu`.`user` ;
CREATE TABLE IF NOT EXISTS `ayu`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `designation` VARCHAR(100) NOT NULL,
  `user_role` VARCHAR(45) NULL,
  `employee_number` VARCHAR(45)  NOT NULL,
  `department_id` INT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CONSTRAINT `user_qk` UNIQUE(`employee_number`),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;