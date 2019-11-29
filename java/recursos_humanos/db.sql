-- MySQL dump 10.16  Distrib 10.1.41-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: recursosHumanos
-- ------------------------------------------------------
-- Server version	10.1.41-MariaDB-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `sexo` varchar(15) DEFAULT NULL,
  `nit` varchar(15) DEFAULT NULL,
  `dui` varchar(10) DEFAULT NULL,
  `cargo` varchar(25) DEFAULT NULL,
  `telefono` varchar(8) DEFAULT NULL,
  `fechaNac` varchar(25) DEFAULT NULL,
  `sueldo` decimal(6,2) DEFAULT NULL,
  `contrasenia` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (9,'josue','masculino','1115','12345','administrador','1123354','1999-02-08',570.00,'contra2'),(10,'Joselin','femenino','12354687','12345678','Cajera','12345678','2000-01-01',900.00,'12345'),(12,'Ivan ','masculino','123456799654','123456789','cargo','12695563','1999-01-12',3000.00,'12345');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impuestos`
--

DROP TABLE IF EXISTS `impuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(10) NOT NULL,
  `porcentaje` double(5,4) DEFAULT NULL,
  `cuota` double(5,2) DEFAULT NULL,
  `vigenciaInit` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impuestos`
--

LOCK TABLES `impuestos` WRITE;
/*!40000 ALTER TABLE `impuestos` DISABLE KEYS */;
INSERT INTO `impuestos` VALUES (10,'isss',0.0300,0.00,0.00),(11,'afp',0.0725,0.00,0.00),(12,'renta',0.0000,0.00,0.00),(13,'renta',0.1000,17.67,472.00),(14,'renta',0.2000,60.00,895.24),(15,'renta',0.3000,288.57,2038.10);
/*!40000 ALTER TABLE `impuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcaciones`
--

DROP TABLE IF EXISTS `marcaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` varchar(25) DEFAULT NULL,
  `idEmpleado` int(11) DEFAULT NULL,
  `hora` varchar(25) DEFAULT NULL,
  `tipo` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcaciones`
--

LOCK TABLES `marcaciones` WRITE;
/*!40000 ALTER TABLE `marcaciones` DISABLE KEYS */;
INSERT INTO `marcaciones` VALUES (5,'2019-11-22',9,'09:34:47 p. m.','entrada'),(6,'2019-11-22',9,'04:28:28 p. m.','salida'),(9,'2019-11-24',10,'08:45:11 p. m.','entrada'),(10,'2019-11-24',10,'08:45:16 p. m.','salida'),(12,'2019-11-25',12,'03:01:59 p. m.','entrada'),(13,'2019-11-25',12,'03:02:20 p. m.','salida'),(14,'2019-11-28',9,'08:49:35 a. m.','entrada'),(15,'2019-11-28',9,'05:00:39 p. m.','salida');
/*!40000 ALTER TABLE `marcaciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-28 20:17:18
