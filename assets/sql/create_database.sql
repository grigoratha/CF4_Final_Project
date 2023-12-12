-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cf4
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` bigint NOT NULL,
  `brand` varchar(255) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enginecc` bigint DEFAULT NULL,
  `enginehp` bigint DEFAULT NULL,
  `imageuri` varchar(255) DEFAULT NULL,
  `mileage` bigint DEFAULT NULL,
  `model` varchar(255) NOT NULL,
  `year` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Lancia','White','In the hands of privateer Italian racing team Jolly Club, the Delta HF Integrale Evo won its sixth and final consecutive WRC manufacturer\'s title',1600,200,'http://localhost:8080/img/cars/lancia_delta_integrale.png',34577,'Delta HF Integrale 16v',1989),(2,'Ford','Green','The Ford Model A was the Ford Motor Company\'s second market success, replacing the venerable Model T which had been produced for 18 years',3300,40,'http://localhost:8080/img/cars/ford_model_a.png',70220,'Model A',1929),(3,'Lotus','AliceBlue','Production of the original Lotus Europa S2 ceased in 1975, with a total of 9,230 cars of all models having been built',1500,82,'http://localhost:8080/img/cars/lotus_europa_s2.png',17134,'Europa S2',1970),(4,'Mercedes Benz','Green','Their bodywork featured distinctive tailfins that gave the models their Heckflosse nickname — German for fintail',2200,108,'http://localhost:8080/img/cars/mercedes_220sb.png',17134,'220 SB',1963),(5,'Chrysler','Gray','This 1935 Imperial C-2 Coupe is one of ten known to survive of just 200 built. The Airflow is perhaps the best example of Art Deco style in the American idiom',5300,138,'http://localhost:8080/img/cars/chrysler_imperial_airflow.png',21967,' Imperial Airflow Coupe',1935),(6,'Chevrolet','Black','1996 was a banner year for Corvette. A whole new car, the Sting Ray, debuted — the first all-new Corvette in a decade',7000,435,'http://localhost:8080/img/cars/chevrolet_corvette_stringray.png',30871,'Corvette Stingray',1966),(7,'Ferrari','Red','In 1985 the Mondial 3.2 Cabriolet was equipped with the new 3.2-litre engine. This increase in power made it an even more exclusive car than before',3000,270,'http://localhost:8080/img/cars/ferrari_mondial_cabriolet.png',14876,'Mondial Cabriolet',1985),(8,'Cadillac','Black','From the beginning, the Brougham was a pace-setting vehicle with styling and engineering features destined to be incorporated into lesser cars in future years',4400,335,'http://localhost:8080/img/cars/cadillac_eldorado_brougham.png',47125,'EL Dorado Brougham',1957),(9,'Rolls Royce','White','The size of the car may deceive about the performance which does justice to many cars of more sporting pretensions. The finish, both in detail and the broader sense of equipment and trim, is superb.',6223,200,'http://localhost:8080/img/cars/rollsroyce_silver_cloud.png',40377,'Silver Cloud III Coupe',1965),(10,'Ford','Yellow','All first-generation Mach 1\'s are distinguished by the body style code 63C on the door data plate',5800,250,'http://localhost:8080/img/cars/ford_mustang_mach1.png',64822,'Mustang Mach 1',1970),(302,'Fiat','Yellow','An  old school supermini hot-hatch produced by the Italian manufacturer Fiat from 1993. These Punto GT Turbo\'s are a rare sight on the roads today.',1400,134,'http://localhost:8080/img/cars/fiat_punto_gt.jpg',125874,'Punto GT',1993);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_seq`
--

DROP TABLE IF EXISTS `car_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_seq`
--

LOCK TABLES `car_seq` WRITE;
/*!40000 ALTER TABLE `car_seq` DISABLE KEYS */;
INSERT INTO `car_seq` VALUES (401);
/*!40000 ALTER TABLE `car_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `id` bigint NOT NULL,
  `item_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (402,'[302]');
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
  `id` bigint NOT NULL,
  `timestamp` varchar(255) DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=453 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (352,'admin@gmail.com','$2a$10$dGiSqUAibnkz2OfCN4vInO475t.Pb8t7PXInO9nSmIgHMr2YMVZPC','admin','Senna'),(354,'user@hotmail.com','$2a$10$QfjkLfHxYgQ5kCmKHuT/VOpgXXPPuRpCgYSlyLCntXIpt9MjiAfRi','user','Stig'),(402,'hamilton@gmail.com','$2a$10$oADFluacFL8L0u6PnFbPmevXYbBh6LI3sxVjPeVpulMRV.WUWKZk6','user','Hamilton');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (551);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-12 23:59:22
