SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `brew` ;
CREATE SCHEMA IF NOT EXISTS `brew` DEFAULT CHARACTER SET utf8 ;
USE `brew` ;

-- -----------------------------------------------------
-- Table `brew`.`ingredient_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`ingredient_type` ;

CREATE  TABLE IF NOT EXISTS `brew`.`ingredient_type` (
  `ingredient_type_id` INT NOT NULL AUTO_INCREMENT ,
  `ingredient_typecol` VARCHAR(45) NULL ,
  PRIMARY KEY (`ingredient_type_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`ingredient` ;

CREATE  TABLE IF NOT EXISTS `brew`.`ingredient` (
  `ingredient_id` INT NOT NULL AUTO_INCREMENT ,
  `ingredient_type_id` INT NOT NULL ,
  `ingredientDescription` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`ingredient_id`) ,
  INDEX `fk_ingredient_type_idx` (`ingredient_type_id` ASC) ,
  CONSTRAINT `fk_ingredient_type`
    FOREIGN KEY (`ingredient_type_id` )
    REFERENCES `brew`.`ingredient_type` (`ingredient_type_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`beer_style`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`beer_style` ;

CREATE  TABLE IF NOT EXISTS `brew`.`beer_style` (
  `beer_style_id` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`beer_style_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`beer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`beer` ;

CREATE  TABLE IF NOT EXISTS `brew`.`beer` (
  `beer_id` INT NOT NULL AUTO_INCREMENT ,
  `beer_name` VARCHAR(255) NOT NULL ,
  `predicted_abv` DOUBLE NULL ,
  `beer_style_id` INT NOT NULL ,
  PRIMARY KEY (`beer_id`) ,
  INDEX `fk_beer_style_id_idx` (`beer_style_id` ASC) ,
  CONSTRAINT `fk_beer_style_id`
    FOREIGN KEY (`beer_style_id` )
    REFERENCES `brew`.`beer_style` (`beer_style_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`recipe` ;

CREATE  TABLE IF NOT EXISTS `brew`.`recipe` (
  `recipe_id` INT NOT NULL AUTO_INCREMENT ,
  `beer_id` INT NOT NULL ,
  PRIMARY KEY (`recipe_id`) ,
  INDEX `fk_recipe_beer_id` (`beer_id` ASC) ,
  CONSTRAINT `fk_recipe_beer_id`
    FOREIGN KEY (`beer_id` )
    REFERENCES `brew`.`beer` (`beer_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`brewer_task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`brewer_task` ;

CREATE  TABLE IF NOT EXISTS `brew`.`brewer_task` (
  `brewer_task_id` INT NOT NULL AUTO_INCREMENT ,
  `brewer_action_type` INT NOT NULL ,
  `task_description` VARCHAR(255) NOT NULL ,
  `task_sub_type` INT NOT NULL ,
  `task_time` DOUBLE NOT NULL ,
  `recipe_id` INT NOT NULL ,
  PRIMARY KEY (`brewer_task_id`) ,
  INDEX `fk_recipe_id` (`recipe_id` ASC) ,
  CONSTRAINT `fk_recipe_id`
    FOREIGN KEY (`recipe_id` )
    REFERENCES `brew`.`recipe` (`recipe_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`measurement_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`measurement_type` ;

CREATE  TABLE IF NOT EXISTS `brew`.`measurement_type` (
  `measurement_type_id` INT NOT NULL AUTO_INCREMENT ,
  `measurementType` VARCHAR(45) NOT NULL ,
  `measurementAbrev` VARCHAR(15) NOT NULL ,
  `measurementDescription` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`measurement_type_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`batch_measurement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`batch_measurement` ;

CREATE  TABLE IF NOT EXISTS `brew`.`batch_measurement` (
  `batch_measurement_id` INT NOT NULL AUTO_INCREMENT ,
  `measurement_type_id` INT NOT NULL ,
  `measurement` DOUBLE NOT NULL ,
  PRIMARY KEY (`batch_measurement_id`) ,
  INDEX `fk_measurement_type_idx` (`measurement_type_id` ASC) ,
  CONSTRAINT `fk_measurement_type`
    FOREIGN KEY (`measurement_type_id` )
    REFERENCES `brew`.`measurement_type` (`measurement_type_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`user` ;

CREATE  TABLE IF NOT EXISTS `brew`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(75) NOT NULL ,
  `password` VARCHAR(150) NOT NULL ,
  `fname` VARCHAR(45) NOT NULL ,
  `lname` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`user_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`beer_term`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`beer_term` ;

CREATE  TABLE IF NOT EXISTS `brew`.`beer_term` (
  `beer_term_id` INT NOT NULL AUTO_INCREMENT ,
  `term` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `possibleCause` VARCHAR(255) NULL ,
  PRIMARY KEY (`beer_term_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `brew`.`beer_term_mapping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `brew`.`beer_term_mapping` ;

CREATE  TABLE IF NOT EXISTS `brew`.`beer_term_mapping` (
  `beer_term_mapping_id` INT NOT NULL AUTO_INCREMENT ,
  `beer_id` INT NOT NULL ,
  `beer_term_id` INT NOT NULL ,
  PRIMARY KEY (`beer_term_mapping_id`) ,
  INDEX `fk_beer_id_idx` (`beer_id` ASC) ,
  INDEX `fk_beer_term_id_idx` (`beer_term_id` ASC) ,
  CONSTRAINT `fk_beer_id`
    FOREIGN KEY (`beer_id` )
    REFERENCES `brew`.`beer` (`beer_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_beer_term_id`
    FOREIGN KEY (`beer_term_id` )
    REFERENCES `brew`.`beer_term` (`beer_term_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `brew`.`beer_style`
-- -----------------------------------------------------
START TRANSACTION;
USE `brew`;
INSERT INTO `brew`.`beer_style` (`beer_style_id`) VALUES (1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `brew`.`beer`
-- -----------------------------------------------------
START TRANSACTION;
USE `brew`;
INSERT INTO `brew`.`beer` (`beer_id`, `beer_name`, `predicted_abv`, `beer_style_id`) VALUES (1, 'Better Not Pout Stout', 6.1, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `brew`.`recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `brew`;
INSERT INTO `brew`.`recipe` (`recipe_id`, `beer_id`) VALUES (1, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `brew`.`brewer_task`
-- -----------------------------------------------------
START TRANSACTION;
USE `brew`;
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (1, 0, '5.5 qts of water to 155 Fahrenheit', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (2, 1, '1.75 lbs crystal malt to brewer\'s sock', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (3, 1, '5.0 oz black patent malt', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (4, 1, '14oz US 2-row malt', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (5, 1, '7.0oz chocolate malt', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (6, 1, '4.0oz roasted barley', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (7, 1, '6.0oz flaked oats', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (8, 0, 'Insert brewer\'s sock into 155 fahrenheit water and steep grains', -1, 0, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (9, 0, 'Remove brewer\'s sock from water', -1, 45, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (10, 0, 'Bring water to a boil', -1, 45, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (11, 1, '1.0oz 5% alpha acid 5 AAU Kent Golding hops', -1, 45, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (12, 1, '0.5oz Cascade hops', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (13, 1, '1.0lb clover honey', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (14, 1, '1 tbsp cinnamon', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (15, 1, '1 tbsp nutmeg', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (16, 1, '2.0oz freshly grated ginger', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (17, 1, '2 tsp allspice', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (18, 1, '0.75 tsp clove', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (19, 1, 'orange zest from 3 medium oranges', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (20, 1, '1 tsp brewing salts', -1, 90, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (21, 1, '1 tsp Irish moss', -1, 95, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (22, 0, 'Remove wort from heat', -1, 105, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (23, 0, 'Tranfer wort in primary fermentor', -1, 105, 1);
INSERT INTO `brew`.`brewer_task` (`brewer_task_id`, `brewer_action_type`, `task_description`, `task_sub_type`, `task_time`, `recipe_id`) VALUES (24, 1, '2 packets of Danstar Nottingham dried yeast when wort reaches 68 fahrenheit', -1, 110, NULL);

COMMIT;
