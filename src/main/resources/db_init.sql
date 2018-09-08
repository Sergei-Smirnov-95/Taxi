CREATE TABLE `business`.`Driver` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45),
  `email` VARCHAR(45),
  `phone` VARCHAR(10) NOT NULL,
  `busy` TINYINT,
  `rating` FLOAT,
  PRIMARY KEY (`id`));

  CREATE TABLE `business`.`Operator` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45),
  `email` VARCHAR(45),
  `phone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `business`.`Passenger` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45),
  `email` VARCHAR(45),
  `phone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `business`.`Cost_calculation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `route_length` FLOAT NOT NULL,
  `waitingTime` FLOAT NOT NULL,
  `totalCost` FLOAT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `business`.`Recall` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `passenger_text` VARCHAR(200),
  `passenger_score` INT,
  `driver_text` VARCHAR(200),
  `confirmed` TINYINT,
  PRIMARY KEY (`id`));

CREATE TABLE `business`.`Order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `source_address` VARCHAR(45) NOT NULL,
  `destination_address` VARCHAR(45) NOT NULL,
  `passenger_id` INT NOT NULL,
  `operator_id` INT NOT NULL,
  `driver_id` INT NOT NULL,
  `order_status` ENUM('NEW','PROCESSING','APPOINTED','REJECTED','ACCEPTED','EXECUTED','DEAD') NOT NULL,
  `rating` FLOAT,
  `creation_date` DATETIME NOT NULL,
  `execution_date` DATETIME NOT NULL,
  `cost_calculation_id` INT NOT NULL,
  `recall_id` INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`passenger_id`)
   REFERENCES `business`.`Passenger` (`id`)
   ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`operator_id`)
   REFERENCES `business`.`Operator` (`id`)
   ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`driver_id`)
   REFERENCES `business`.`Driver` (`id`)
   ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`cost_calculation_id`)
   REFERENCES `business`.`Cost_calculation` (`id`)
   ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`recall_id`)
   REFERENCES `business`.`Recall` (`id`)
   ON UPDATE CASCADE ON DELETE CASCADE);