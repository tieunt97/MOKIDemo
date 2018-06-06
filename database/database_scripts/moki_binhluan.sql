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
-- Table structure for table `binhluan`
--

DROP TABLE IF EXISTS `binhluan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `binhluan` (
  `idBinhLuan` int(11) NOT NULL AUTO_INCREMENT,
  `idKhachHang` int(11) NOT NULL,
  `idSanPham` int(11) NOT NULL,
  `noiDung` text NOT NULL,
  `thoiGian` datetime DEFAULT NULL,
  PRIMARY KEY (`idBinhLuan`),
  KEY `keyBinhLuan_KhachHang_idx` (`idKhachHang`),
  KEY `keyBinhLuan_SanPham_idx` (`idSanPham`),
  CONSTRAINT `keyBinhLuan_KhachHang` FOREIGN KEY (`idKhachHang`) REFERENCES `khachhang` (`idKhachHang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `keyBinhLuan_SanPham` FOREIGN KEY (`idSanPham`) REFERENCES `sanpham` (`idSanPham`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `binhluan`
--

LOCK TABLES `binhluan` WRITE;
/*!40000 ALTER TABLE `binhluan` DISABLE KEYS */;
INSERT INTO `binhluan` VALUES (1,1,2,'Bạn giảm giá chút đc ko','2018-04-28 09:52:00'),(2,1,2,'hihi','2018-05-02 21:24:26'),(3,1,2,'haha','2018-05-02 21:31:26'),(5,1,2,'ahaha','2018-05-02 21:48:45'),(6,2,2,'.','2018-05-02 21:57:07'),(7,1,2,'ahaha','2018-05-02 21:48:45'),(8,1,2,'tbt','2018-05-02 22:06:22'),(9,1,2,'haha','2018-05-02 22:10:26'),(11,1,3,'haha','2018-05-03 07:22:43'),(12,1,1,'haha','2018-05-03 07:24:11'),(13,1,4,'haha','2018-05-03 07:28:44'),(14,1,1,'hihi','2018-05-03 07:34:47'),(15,1,5,'hihi','2018-05-03 07:35:19'),(16,1,4,'giảm giá chút bạn ơi','2018-05-03 07:38:50'),(17,1,6,'.','2018-05-03 07:41:35'),(18,1,2,'hello','2018-05-12 20:21:17'),(19,1,2,'hello','2018-05-12 20:21:18'),(20,1,2,'demo','2018-05-17 21:05:51'),(21,1,2,'hehe','2018-05-17 09:19:24'),(22,1,1,'mien phi ha ban','2018-06-06 12:06:34');
/*!40000 ALTER TABLE `binhluan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-06 13:59:24
