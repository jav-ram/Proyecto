-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Dinero`
--

DROP TABLE IF EXISTS `Dinero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Dinero` (
  `idDinero` int(11) NOT NULL AUTO_INCREMENT,
  `CantDinero` int(11) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT '',
  `Tipo` char(50) DEFAULT NULL,
  `dia` int(11) DEFAULT NULL,
  `mes` char(50) DEFAULT NULL,
  PRIMARY KEY (`idDinero`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dinero`
--

LOCK TABLES `Dinero` WRITE;
/*!40000 ALTER TABLE `Dinero` DISABLE KEYS */;
INSERT INTO `Dinero` VALUES (1,250,'Libros','Estudios',3,'Enero'),(2,1000,'Tele','Osio',7,'Enero'),(3,150,'pizza','Comida',7,'Enero'),(4,5,'lavanderia ','Servicios',16,'Enero'),(5,100,'Parque','Osio',28,'Enero'),(6,50,'hamburguesa','Comida',13,'Agosto'),(7,50,'pizza','Servicios',1,'Febrero,0'),(8,1,'nada','Ocio',1,'Enero'),(9,1,'1','Ocio',1,'Enero'),(10,9,'sadas','Ocio',1,'Enero');
/*!40000 ALTER TABLE `Dinero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Monto`
--

DROP TABLE IF EXISTS `Monto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Monto` (
  `idMonto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `monto` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMonto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Monto`
--

LOCK TABLES `Monto` WRITE;
/*!40000 ALTER TABLE `Monto` DISABLE KEYS */;
INSERT INTO `Monto` VALUES (1,90);
/*!40000 ALTER TABLE `Monto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-29 17:24:10
