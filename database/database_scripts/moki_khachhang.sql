-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: moki
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khachhang` (
  `idKhachHang` int(11) NOT NULL AUTO_INCREMENT,
  `tenKhachHang` varchar(45) DEFAULT NULL,
  `soDT` varchar(45) NOT NULL,
  `anhInfoKH` text,
  `anhBia` text,
  `moTa` text,
  `matKhau` varchar(45) NOT NULL,
  `trangThai` int(11) NOT NULL,
  `maKichHoat` int(11) DEFAULT NULL,
  `ngayKichHoat` varchar(15) DEFAULT NULL,
  `diemTinCay` int(11) DEFAULT '0',
  `thoiGianOnline` text,
  `thoiGianOffline` text,
  PRIMARY KEY (`idKhachHang`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
INSERT INTO `khachhang` VALUES (1,'Nguyễn Tài Tiêu','0123456789','/hinhinfo/1AnhKhachHang.jpg','/hinhinfo/1AnhBia.jpg','Nguyễn Tài Tiêu','tnt1027',1,1234,'27-10-2017',0,NULL,NULL),(2,'Bùi Thị Diệu','0174884710','/hinhinfo/2AnhKhachHang.jpg','/hinhinfo/2AnhBia.jpg','Bùi Thị Diệu','buidieu',1,1827,'18-01-2018',0,NULL,NULL),(3,NULL,'01643737426',NULL,NULL,NULL,'tieunt',-1,2084,NULL,0,NULL,NULL),(4,NULL,'0965677760',NULL,NULL,NULL,'ntn7195',1,1008,NULL,0,NULL,NULL),(5,NULL,'08888888888',NULL,NULL,NULL,'12345678',-1,2833,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-06 13:59:23
