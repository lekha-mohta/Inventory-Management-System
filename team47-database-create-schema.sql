CREATE DATABASE IF NOT EXISTS `team047`;
USE `team047`;

-- Table structure for table `Address`
DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address` (
  `house_number` varchar(45) DEFAULT NULL,
  `road_name` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) DEFAULT NULL,
  `user_address` int NOT NULL,
  PRIMARY KEY (`user_address`),
  CONSTRAINT `user_address` FOREIGN KEY (`user_address`) REFERENCES `Users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Address` VALUES ('12','Wellington St','Sheffield','S14AA',30884),('21','The Diamond','Sheffield','S1 4HL',54189);

-- Table structure for table `BankingDetails`
DROP TABLE IF EXISTS `BankingDetails`;
CREATE TABLE `BankingDetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `card_type` varchar(45) NOT NULL,
  `card_name` varchar(45) NOT NULL,
  `card_number` varchar(45) NOT NULL,
  `expiry` varchar(45) NOT NULL,
  `cvv` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid_idx` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `Users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `BankingDetails` VALUES (1,30884,'VISA','Ahsan Adnan','NDA5NzU4MzAxOTYxNzE4NQ==','03/32','456'),(2,30884,'MASTERCARD','Ahsan Adnan','NTAzNDU4MzAxOTYxNzE4NQo=','11/28','756'),(3,30884,'VISA','Ahsan Adnan','NDA5NzU4MTIzNDU2Nzg5MQo=','05/32','123'),(4,30884,'VISA','ahsann','NDA5NzU4MTIzNDU2Nzg5MA==','11/22','111'),(5,1,'VISA','Mr Manager','NDA5NzU4MDQxMTAzNjQ4Mw==','02/31','999'),(6,54307,'VISA','Joel Foster','NDEyMzQ1Njc4OTEwMzQ1Ng==','06/26','345');

-- Table structure for table `BoxedSets`
DROP TABLE IF EXISTS `BoxedSets`;
CREATE TABLE `BoxedSets` (
  `BoxSetId` varchar(45) NOT NULL,
  `ProductId` varchar(45) NOT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`BoxSetId`,`ProductId`),
  KEY `ProductId_idx` (`ProductId`),
  CONSTRAINT `BoxSetId` FOREIGN KEY (`BoxSetId`) REFERENCES `Products` (`product_id`),
  CONSTRAINT `ProductId` FOREIGN KEY (`ProductId`) REFERENCES `Products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `BoxedSets` VALUES ('T001','TP',5),('TS001','C002',1),('TS001','L934',1),('TS001','T001',2);

-- Table structure for table `OrderDetails`
DROP TABLE IF EXISTS `OrderDetails`;
CREATE TABLE `OrderDetails` (
  `index_id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(45) NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`index_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `Products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `OrderDetails` VALUES (3,'C002',58395,2),(4,'T001',58395,2),(5,'TP',58395,1),(6,'P001',27366,3),(7,'L934',18029,3),(8,'T001',8929,2),(9,'C002',18223,4),(10,'L934',18223,1),(11,'T001',18223,2),(12,'L934',56882,1),(13,'L934',82268,1),(14,'C001',31386,5),(15,'C001',95154,1),(16,'P002',38922,2);

-- Table structure for table `Orders`
DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders` (
  `order_id` int NOT NULL,
  `user_id` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `total_cost` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Orders` VALUES (8929,30884,'FULFILLED','2023-11-30 00:00:00','79.98'),(18223,54307,'CONFIRMED','2023-11-30 00:00:00','273.93'),(27366,30884,'FULFILLED','2023-11-30 00:00:00','299.96999999999997'),(31386,30884,'CONFIRMED','2023-12-01 00:00:00','249.95000000000002'),(38922,30884,'CONFIRMED','2023-12-01 00:00:00','259.98'),(56882,30884,'CONFIRMED','2023-11-30 00:00:00','99.99'),(58395,30884,'CONFIRMED','2023-11-30 00:00:00','136.95000000000002'),(82268,30884,'CONFIRMED','2023-11-30 00:00:00','99.99'),(95154,30884,'CONFIRMED','2023-12-01 00:00:00','49.99');

-- Table structure for table `Products`
DROP TABLE IF EXISTS `Products`;
CREATE TABLE `Products` (
  `product_id` varchar(45) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `era_code` varchar(45) DEFAULT NULL,
  `dcc_code` varchar(45) DEFAULT NULL,
  `gauge` varchar(45) DEFAULT NULL,
  `digital` tinyint DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Products` VALUES ('C001','TrainTech','Digital Controller','controller',12,49.99,NULL,'','','OO Gauge',1),('C002','TrainTech','Digital Controller TT','controller',4,23.49,NULL,'','','TT Gauge',1),('L934','Hogwarts','Hogwarts Express','locomotive',1,99.99,NULL,'Era 4-5','ANALOGUE','TT Gauge',0),('P001','ModelTrainCo','Express Locomotive','locomotive',2,99.99,NULL,'ERA-III','ANALOGUE','N',NULL),('P002','RailwayModels','Freight Locomotive','locomotive',8,129.99,NULL,'ERA-IV','DCC_READY','HO',NULL),('T001','TrainTech','Single Straight Pack','track pack',2,39.99,NULL,'','','TT Gauge',0),('T002','ModelTrainCo','Curved Track','track piece',9,7.99,NULL,'','','OO Gauge',0),('TP','TrainTech','Single Straight','track piece',1,9.99,NULL,'','','TT Gauge',0),('TS001','Hogwarts','Hogwarts Train Set','train set',7,149.99,NULL,'Era 4-5','','TT Gauge',0);

-- Table structure for table `Users`
DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `user_id` int NOT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `user_role` int DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Users` VALUES (1,'Mr','Manager','manager@trainsheffield.com',2,'9b50bb25814ff13d1a38c5ec2393bceb'),(2,'Staff','Member','staff@gmail.com',1,'b99165cd2609bbb891390120ed2df1cb'),(30884,'Muhammad Ahsan','Adnan','ahsan.1156@hotmail.com',1,'bba8992b1f4c57cbba0b2a78ab28c890'),(54189,'muskan','sharma','msharma4@sheffield.ac.uk',0,'91ec1f9324753048c0096d036a694f86'),(54307,'Joel','Foster','jfoster9@sheffield.ac.uk',0,'098f6bcd4621d373cade4e832627b4f6');
