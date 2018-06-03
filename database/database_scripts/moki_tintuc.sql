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
-- Table structure for table `tintuc`
--

DROP TABLE IF EXISTS `tintuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tintuc` (
  `idTinTuc` int(11) NOT NULL AUTO_INCREMENT,
  `tieuDe` text,
  `noiDung` text,
  `ngayDang` datetime DEFAULT NULL,
  PRIMARY KEY (`idTinTuc`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tintuc`
--

LOCK TABLES `tintuc` WRITE;
/*!40000 ALTER TABLE `tintuc` DISABLE KEYS */;
INSERT INTO `tintuc` VALUES (1,'THÔNG BÁO NGHỈ LỄ GIỖ TỔ HÙNG VƯƠNG VÀ LỄ 30/4 - 1/5','MOKI - Ứng dụng giúp các mẹ trao đổi đồ trân trọng thông báo lịch nghỉ lễ như sau:\r\n\r\n- Ngày 25/4 nghỉ.\r\n- Ngày 26/4 - 29/4 theo lịch làm việc giờ hành chính.\r\n- Ngày 30/4 - 1/5 nghỉ.\r\n- Từ ngày 2/5 MOKI trở lại hoạt động như bình thường.\r\n\r\nCác đơn hàng phát sinh tỏng ngày nghỉ sẽ được MOKI giải quyết vào ngày làm việc đầu tiên sau nghỉ lễ. Các đơn hàng đã được MOKI tiếp nhận sẽ được vận chuyển theo lịch của bên giao hang (Giao hàng nhanh, Giao hàng tiết kiệm, Viettel Post).\r\n\r\nKính chúc quý khách một kỳ nghỉ lễ vui vẻ, an toàn.','2018-04-26 08:16:45');
/*!40000 ALTER TABLE `tintuc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-03 16:42:18
