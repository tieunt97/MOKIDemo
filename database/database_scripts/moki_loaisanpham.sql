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
-- Table structure for table `loaisanpham`
--

DROP TABLE IF EXISTS `loaisanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaisanpham` (
  `idLoaiSP` int(11) NOT NULL AUTO_INCREMENT,
  `tenLoaiSP` varchar(45) NOT NULL,
  `idLoaiSPCha` int(11) NOT NULL,
  PRIMARY KEY (`idLoaiSP`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaisanpham`
--

LOCK TABLES `loaisanpham` WRITE;
/*!40000 ALTER TABLE `loaisanpham` DISABLE KEYS */;
INSERT INTO `loaisanpham` VALUES (1,'Miễn phí',0),(2,'Bé ăn',0),(3,'Bé mặc',0),(4,'Bé ngủ',0),(5,'Bé tắm',0),(6,'Bé vệ sinh',0),(7,'Bé khỏe-an toàn',0),(8,'Bé đi ra ngoài',0),(9,'Bé chơi mà học',0),(10,'Dành cho mẹ',0),(11,'Đồ dùng gia đinh',0),(12,'Sản phẩm khác',0),(13,'Sữa bột các loại',2),(14,'Bình sữa và phụ kiện',2),(15,'Bột, Cháo, Bánh ăn dặm',2),(16,'Đồ dùng phục vụ ăn uống',2),(17,'Ghế ăn bột cho bé',2),(18,'Thực phẩm đóng hộp cho bé',2),(19,'Bột pha sữa',13),(20,'Sữa NK từ NewZealand',13),(21,'Các loại sữa khác',13),(22,'Sữa Việt Nam',13),(23,'Sữa Bột Nan',13),(24,'Sữa Bột Pediasure',13),(25,'Sữa Bột Aptamil',13),(26,'Sữa Bột Enfagrow',13),(27,'Sữa Bột Ensure',13),(28,'Sữa Bột Frisov',13),(29,'Sữa Bột Physiolac',13),(30,'Sữa Bột Similac',13),(31,'Sữa Bột Bellamy',13),(32,'Sữa Dutch Lady',13),(33,'Sữa Bột Hipp',13),(34,'Sữa Bột Star Gold',13),(35,'Sữa Bột Celia',13),(36,'Sữa Dê Nga',13),(37,'Sữa Bột Gallia',13),(38,'Sữa Bột Goodhealth',13),(39,'Sữa Bột GrandNoble',13),(40,'Sữa Bột lam Mother',13),(41,'Sữa Bột Meiji',13),(42,'Sữa Bột Morinaga',13),(43,'Sữa Bột Nuti IQ',13),(44,'Sữa Bột Wakodo',13),(45,'Sữa Bột XO',13),(46,'Sữa S26',13),(47,'Sữa NK từ Mỹ',13),(48,'Sữa NK từ Nga',13),(49,'Sữa NK từ Hà Lan',13),(50,'Sữa NK từ Đức',13),(51,'Sữa NK từ Hàn Quốc',13),(52,'Sữa NK từ Pháp',13),(53,'Sữa NK từ Úc',13),(54,'Sữa NK từ Nhật',13),(55,'Sữa non các loại',13),(56,'Sữa NK từ Singapore',13),(57,'Sữa NK từ Anh',13),(58,'Sữa NK từ Thái Lan',13),(59,'Núm ty cho bé',14),(60,'Bình sữa cho bé',14),(61,'Mỳ, Bột và Cháo ăn dặm',15),(62,'Bánh ăn dặm',15),(63,'Nước mắm/dầu gấc/dầu oliu',15),(64,'Giá - Kệ bình sữa, thức ăn',16),(65,'Bát, thìa, yếm ăn dặm',16),(66,'Dụng cụ chế biến thức ăn',16),(67,'Cốc - Bình cho bé uống',16),(68,'Dự trữ/cất giữ thức ăn',16),(69,'Dụng cụ giữ nhiệt',16),(70,'Dụng cụ tiệt trùng bình sữa',16),(71,'Đồ sơ sinh',3),(72,'Thời trang cho bé',3),(73,'Mũ thời trang',3),(74,'Giày dép cho bé',3),(75,'Phụ kiện thời trang khác',3),(76,'Quần áo sơ sinh',71),(77,'Mũ sơ sinh - che thóp',71),(78,'Khăn cho bé',71),(79,'Quần đóng tã, tã chéo, tã vuông',71),(80,'Túi ngủ, choàng bế cho bé',71),(81,'Bao, tất chân tay cho bé',71),(82,'Thời trang bé trai',72),(83,'Thời trang bé gái',72),(84,'Chăn đệm chiếu gối',4),(85,'Giường ngủ, giường tầng',4),(86,'Cũi cho bé',4),(87,'Bộ quây cũi',4),(88,'Nôi xách em bé',4),(89,'Nôi điện tự động',4),(90,'Nôi ngủ chung giường',4),(91,'Ghế rung cho bé',4),(92,'Chặn giường',4),(93,'Đèn ngủ, máy báo khóc, ru ngủ',4),(94,'Chăn cho bé',84),(95,'Chiếu - Đệm, Màn cho bé',84),(96,'Gối đỡ đầu cho bé',84),(97,'Gối ôm',84),(98,'Sữa tắm, dầu gội',5),(99,'Chậu tắm và phụ kiện',5),(100,'Khăn mặt, Khăn tắm cho bé',5),(101,'Đo nhiệt độ tắm cho bé',5),(102,'Đồ chơi khi tắm',5),(103,'Mũ chắn nước',5),(104,'Bỉm và tã giấy cho bé',6),(105,'Giấy ướt và máy ủ giấy ướt',6),(106,'Tấm lót chống thấm',6),(107,'Bô vệ sinh, nắp toilet',6),(108,'Kem hăm, dưỡng da, phấn rôm',6),(109,'Vệ sinh tóc, tai, tay, chân, răng, miệng',6),(110,'Dầu massage các loại',108),(111,'Kem trị hăm cho bé',108),(112,'Kem dưỡng da, chống nẻ',108),(113,'Phấn rôm, nước hoa',108),(114,'Bông tai, bông y tế',109),(115,'Rơ lưỡi, Băng rốn',109),(116,'Dụng cụ soi lấy ráy tai cho bé',109),(117,'Nước nhỏ mắt cho bé',109),(118,'Tông đơ, kéo, lược chải tóc',109),(119,'Bấm móng chân tay cho bé',109),(120,'Bàn chải, kem đánh răng, nước súc miệng',109),(121,'Dụng cụ hút mũi',7),(122,'Cao dán hạ sốt, dầu bôi',7),(123,'Cho bé uống thuốc',7),(124,'Nhiệt kế cho bé',7),(125,'Kem chống côn trùng',7),(126,'Cân sức khỏe cho bé',7),(127,'Bịt ổ điện, Bịt góc bàn và chặn cửa',7),(128,'Trà, vitamin và thức phẩm chức năng cho bé',7),(129,'Ô, áo mưa, kem chống nắng cho bé',8),(130,'Xe đẩy cho bé',8),(131,'Địu, đai các loại',8),(132,'Ba lô cặp túi cho bé',8),(133,'Ghế ngồi xe hơi',8),(134,'Ghế ngồi xe đạp, xe máy',8),(135,'Đồ bơi, kính bơi của bé',8),(136,'Kính thời trang',8),(137,'Đồ chơi bé sơ sinh',9),(138,'Đồ chơi bé trai',9),(139,'Đồ chơi bé gái',9),(140,'Đồ chơi thông minh',9),(141,'Đồ chơi vận động',9),(142,'Đồ chơi gỗ',9),(143,'Sách, truyện cho bé',9),(144,'Đồ treo nôi cũi, xe đẩy',137),(145,'Đồ chơi bóp chíp - xúc xắc',137),(146,'Kệ chữ A',137),(147,'Thảm chơi cho bé',137),(148,'Đồ chơi an toàn khác',137),(149,'Đồ chơi xếp hình, mô hình',138),(150,'Xe mô hình',138),(151,'Đồ chơi điều khiên, chạy pin/cót',138),(152,'Đồ chơi búp bê, lật đật, đồ hàng',139),(153,'Đồ chơi âm nhạc',139),(154,'Đồ chơi giáo dục / Hướng nghiệp',140),(155,'Đất nặn / Sáp màu / Bộ dụng cụ học tập',140),(156,'Xe tập đi cho bé',141),(157,'Xe đạp 3 bánh cho bé',141),(158,'Xe lắc nhạc cho bé',141),(159,'Đồ chơi ngoài trời',141),(160,'Xe trượt cho bé',141),(161,'Xe chòi chân',141),(162,'Xe điện cho bé',141),(163,'Nhà bóng, các loại bóng',141),(164,'Xe đạp thăng bằng',141),(165,'Ghế tập ngồi',141),(166,'Mẹ mang thai',10),(167,'Mẹ sau khi sinh',10),(168,'Sữa bà bầu',10),(169,'Hút sữa và phụ kiện',10),(170,'Dầu gội, Mỹ phẩm',10),(171,'Giày dép cho mẹ',10),(172,'Thời trang cho mẹ',10),(173,'Sách cho mẹ',10),(174,'Trà, vitamin và thực phẩm chức năng',10),(175,'Dầu massages cho mẹ',166),(176,'Gối đa năng cho mẹ và bé',166),(177,'Muối tắm/ngâm chân khoáng chất cho mẹ',166),(178,'Đai đỡ bụng cho bà bầu',166),(179,'Kem chống rạn da',166),(180,'Tai nghe bà bầu',166),(181,'Bánh dành cho bà bầu',166),(182,'Túi đựng đồ cho bé',167),(183,'Bỉm/ BVS/ Dung dịch vệ sinh',167),(184,'Thấm sữa/ Trợ ty/ Thuốc bôi',167),(185,'Áo cho con bú',167),(186,'Gen bụng',167),(187,'Ghế ngồi cho con bú',167),(188,'Sữa tắm, dầu gội',170),(189,'Mỹ phẩm cho bà bầu',170),(190,'Đầm bầu, váy bầu',172),(191,'Thời trang công sở',172),(192,'Phụ kiện',172),(193,'Phòng bếp',11),(194,'Phòng ngủ',11),(195,'Phòng khách',11),(196,'Phòng tắm, giặt',11),(197,'Máy xay các loại',193),(198,'Bếp các loại',193),(199,'Lò nướng, lò hấp',193),(200,'Khay đựng thức ăn, dụng cụ làm bánh',193),(201,'Tủ đựng đồ cho bé',194),(202,'Máy tạo độ ẩm',194),(203,'Chăn ga gối đêm',194),(204,'Bàn, ghế ngồi cho bé',195),(205,'Đồng hồ - Khung ảnh',195),(206,'Đồ trang trí nhà',195),(207,'Bột giặt, Dung dịch giặt xả',196),(208,'Mắc phơi quần áo',196),(209,'Đèn sưởi',196),(210,'Máy sấy quần áo',196);
/*!40000 ALTER TABLE `loaisanpham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-03 16:42:20
