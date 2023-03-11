-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mealprepdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mealprepdb` ;

-- -----------------------------------------------------
-- Schema mealprepdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mealprepdb` DEFAULT CHARACTER SET utf8 ;
USE `mealprepdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(500) NULL,
  `email` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe` ;

CREATE TABLE IF NOT EXISTS `recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `hidden` TINYINT NULL,
  `image_url` TEXT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_recipe_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ingredient` ;

CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `instruction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `instruction` ;

CREATE TABLE IF NOT EXISTS `instruction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NULL,
  `recipe_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_instruction_recipe1_idx` (`recipe_id` ASC),
  CONSTRAINT `fk_instruction_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_recipe` ;

CREATE TABLE IF NOT EXISTS `user_has_recipe` (
  `user_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `recipe_id`),
  INDEX `fk_user_has_recipe_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_user_has_recipe_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_recipe_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_recipe_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `recipe_has_ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `recipe_has_ingredient` ;

CREATE TABLE IF NOT EXISTS `recipe_has_ingredient` (
  `recipe_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `amount` VARCHAR(45) NULL,
  `measurement` VARCHAR(45) NULL,
  PRIMARY KEY (`recipe_id`, `ingredient_id`),
  INDEX `fk_recipe_has_ingredient_ingredient1_idx` (`ingredient_id` ASC),
  INDEX `fk_recipe_has_ingredient_recipe1_idx` (`recipe_id` ASC),
  CONSTRAINT `fk_recipe_has_ingredient_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_has_ingredient_ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mealplan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mealplan` ;

CREATE TABLE IF NOT EXISTS `mealplan` (
  `user_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `recipe_id`),
  INDEX `fk_user_has_recipe1_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_user_has_recipe1_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_recipe1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_recipe1_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `measurement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `measurement` ;

CREATE TABLE IF NOT EXISTS `measurement` (
  `id` INT NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS mealprep;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'mealprep' IDENTIFIED BY 'mealprep';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'mealprep';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `enabled`, `role`) VALUES (1, 'admin', 'admin', 'admin@admin.com', 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `recipe` (`id`, `name`, `description`, `hidden`, `image_url`, `user_id`) VALUES (1, 'Macaroni and cheese', 'You know what that is', 1, 'https://ameessavorydish.com/wp-content/uploads/2011/03/Baked-mac-and-cheese-feature.jpg', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `ingredient` (`id`, `name`) VALUES (1, 'Macaroni');
INSERT INTO `ingredient` (`id`, `name`) VALUES (2, 'Cheese');

COMMIT;


-- -----------------------------------------------------
-- Data for table `instruction`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `instruction` (`id`, `description`, `recipe_id`) VALUES (1, 'Boil Macaroni', 1);
INSERT INTO `instruction` (`id`, `description`, `recipe_id`) VALUES (2, 'Add cheese.', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `user_has_recipe` (`user_id`, `recipe_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `recipe_has_ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `recipe_has_ingredient` (`recipe_id`, `ingredient_id`, `amount`, `measurement`) VALUES (1, 1, '10', 'lb');
INSERT INTO `recipe_has_ingredient` (`recipe_id`, `ingredient_id`, `amount`, `measurement`) VALUES (1, 2, '5', 'mg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mealplan`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `mealplan` (`user_id`, `recipe_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `measurement`
-- -----------------------------------------------------
START TRANSACTION;
USE `mealprepdb`;
INSERT INTO `measurement` (`id`, `description`) VALUES (1, 'mg');
INSERT INTO `measurement` (`id`, `description`) VALUES (2, 'g');
INSERT INTO `measurement` (`id`, `description`) VALUES (3, 'kg');
INSERT INTO `measurement` (`id`, `description`) VALUES (4, 'oz');
INSERT INTO `measurement` (`id`, `description`) VALUES (5, 'lb');
INSERT INTO `measurement` (`id`, `description`) VALUES (6, 'cups');
INSERT INTO `measurement` (`id`, `description`) VALUES (7, 'pint');
INSERT INTO `measurement` (`id`, `description`) VALUES (8, 'quart');
INSERT INTO `measurement` (`id`, `description`) VALUES (9, 'gallon');
INSERT INTO `measurement` (`id`, `description`) VALUES (10, 'fl oz');
INSERT INTO `measurement` (`id`, `description`) VALUES (11, 'tsp');
INSERT INTO `measurement` (`id`, `description`) VALUES (12, 'tbsp');
INSERT INTO `measurement` (`id`, `description`) VALUES (13, 'pinch');

COMMIT;

