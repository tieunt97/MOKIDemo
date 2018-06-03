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
-- Table structure for table `nhanhieu`
--

DROP TABLE IF EXISTS `nhanhieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhanhieu` (
  `idNhanHieu` int(11) NOT NULL AUTO_INCREMENT,
  `tenNhanHieu` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idNhanHieu`)
) ENGINE=InnoDB AUTO_INCREMENT=374 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanhieu`
--

LOCK TABLES `nhanhieu` WRITE;
/*!40000 ALTER TABLE `nhanhieu` DISABLE KEYS */;
INSERT INTO `nhanhieu` VALUES (1,'Abbott'),(2,'ABONNE'),(3,'Adidas'),(4,'Agi'),(5,'Amando'),(6,'Ameda'),(7,'Ange'),(8,'Anmum Materna'),(9,'AnnaNina'),(10,'Anto'),(11,'Aprica'),(12,'Aptamil'),(13,'Arau baby'),(14,'Artemis'),(15,'Aveeno'),(16,'Avent'),(17,'B\'ledina'),(18,'Babiboo'),(19,'Baby Aqua Tisue'),(20,'Baby Dream'),(21,'Baby Leo'),(22,'Babybum'),(23,'Babymagic'),(24,'Bamboo'),(25,'Bamina'),(26,'Banamiza'),(27,'Basilic'),(28,'Battat'),(29,'BB'),(30,'Bebe'),(31,'Bebe Confort'),(32,'Beborn'),(33,'Beega\'s'),(34,'Beegadog'),(35,'Beiner'),(36,'Bellamy\'s'),(37,'Belli blossom'),(38,'Bepanthen'),(39,'Besins Healthcare'),(40,'Betadine'),(41,'Beurer'),(42,'Bibi'),(43,'Bibo'),(44,'Bibos'),(45,'Bieber'),(46,'Bimbosan'),(47,'Bio Oil Specialist'),(48,'Bkids'),(49,'Blackmores'),(50,'Bledina'),(51,'Bobby'),(52,'Borges'),(53,'Borges  Extra Light'),(54,'Born Free'),(55,'Bosbabi'),(56,'Bosch'),(57,'Braun'),(58,'Bremed'),(59,'Brevi'),(60,'Bright Starts'),(61,'Bubchen'),(62,'Camry'),(63,'Canada'),(64,'Candy'),(65,'Canifa'),(66,'Canpol'),(67,'Canpol Babies'),(68,'Care inflammation skin'),(69,'Carefree'),(70,'Carter'),(71,'CarTer\'s'),(72,'CelenaMom'),(73,'Celia'),(74,'Cetaphil'),(75,'Chicco'),(76,'Chuchu'),(77,'Cimilre'),(78,'Clarins'),(79,'Classic'),(80,'Coast'),(81,'Combi'),(82,'Comfort'),(83,'Comotomo'),(84,'Corine de Farme'),(85,'Cosco'),(86,'Crazy 8'),(87,'D-nee'),(88,'Dạ hương'),(89,'Daiwa'),(90,'Dantoy'),(91,'Daramin Mom'),(92,'Diana'),(93,'Dove'),(94,'Downy'),(95,'Dr.Spiller'),(96,'DrBrown\'s'),(97,'Dreamland'),(98,'Duraqua'),(99,'Dutch Lady'),(100,'Duy Tân'),(101,'Earthmama'),(102,'Elbak'),(103,'Electrolux'),(104,'Epsom'),(105,'Everon'),(106,'Everon Lite'),(107,'Fa'),(108,'Fany'),(109,'Farlin'),(110,'Fatzbaby'),(111,'First  Ware'),(112,'First wear'),(113,'Fisher Price'),(114,'Fisher-Price'),(115,'Forever 21'),(116,'Fox & Friends Summer'),(117,'Freshmore'),(118,'Friso'),(119,'Gallia'),(120,'Denie Hour Glass'),(121,'Gerber'),(122,'Glasslock'),(123,'Glico'),(124,'GlyDerm'),(125,'GoodHealth'),(126,'Doon'),(127,'Graco'),(128,'Gymboree'),(129,'H&M'),(130,'Hà An'),(131,'Hạnh phúc'),(132,'Hans'),(133,'Happybaby'),(134,'Heinz'),(135,'Heizen'),(136,'Hello Kitty'),(137,'Hipp'),(138,'Hoàng Thành'),(139,'Holtashi'),(140,'Honey'),(141,'Hotor'),(142,'Huggies'),(143,'Ichiban'),(144,'IKEA'),(145,'ikera'),(146,'Inomata'),(147,'Intex'),(148,'IQ'),(149,'Janod'),(150,'Jiading'),(151,'Jialeda'),(152,'Jie Ling'),(153,'Johnson\'s baby'),(154,'Just For Chef'),(155,'Kabrita'),(156,'Kangaroo'),(157,'KareFresh'),(158,'Kenjo'),(159,'Kerokid'),(160,'Kewpie'),(161,'Kiba'),(162,'Kiddy Clay'),(163,'Kiza'),(164,'Kodomo'),(165,'Kokubo'),(166,'Koostarz'),(167,'Kotex'),(168,'Kottmann'),(169,'Kuku'),(170,'Kunellla Feinkost WalnuBol'),(171,'Kyoryo'),(172,'L\'oocitance'),(173,'Lactacyd'),(174,'Ladysoft'),(175,'Laica'),(176,'Lamaze'),(177,'Lamha'),(178,'Lec'),(179,'Lego'),(180,'Lifebouy'),(181,'LifeSpring'),(182,'Lignum Vitae'),(183,'Lion'),(184,'Little Bear'),(185,'Lock&Lock'),(186,'Long Hưng'),(187,'Lotus'),(188,'Lovi'),(189,'Lucky Baby'),(190,'Luckystar'),(191,'Luki'),(192,'Lullaby'),(193,'Luvable'),(194,'Mabu Hikoji'),(195,'Made in Korean'),(196,'Made in Thailand'),(197,'Made in USA'),(198,'Made in VietNam'),(199,'Malizia'),(200,'Mama'),(201,'Mama sữa non'),(202,'Mamachi'),(203,'Mamago'),(204,'Mastela'),(205,'Max Biocare'),(206,'Maxcare'),(207,'Mead Johnson'),(208,'Medela'),(209,'Meiji'),(210,'Merries'),(211,'MerryMom'),(212,'Miki'),(213,'Milaganics'),(214,'Mio'),(215,'Miomio'),(216,'Modilac'),(217,'Mom\'s care'),(218,'Momcare'),(219,'Monny'),(220,'Morinaga'),(221,'Mother-V'),(222,'Mothercare'),(223,'Mugi'),(224,'Muhi'),(225,'Mumsure bibica'),(226,'Munchkin'),(227,'Naforye'),(228,'Nagakawa'),(229,'Nam Sơn'),(230,'Namyang'),(231,'Nana'),(232,'Nannys'),(233,'Nano'),(234,'Nano Silver'),(235,'Naris'),(236,'Natur'),(237,'Nature made'),(238,'Naweimei'),(239,'Nemo'),(240,'Nepia'),(241,'Nestle'),(242,'Newborn'),(243,'Ngư Nhi'),(244,'Nhãn hiệu khác'),(245,'Nivea'),(246,'Nona'),(247,'Novena'),(248,'Nuby'),(249,'Nuxk'),(250,'Nuna'),(251,'Nuti Food'),(252,'Nutra-Omega 3'),(253,'Nutriben'),(254,'O!baby'),(255,'OEM'),(256,'Ofukuro'),(257,'Olive Extra Virgin'),(258,'Olive Olympias'),(259,'Omo'),(260,'Omron'),(261,'Organic'),(262,'OTTO'),(263,'Palmer'),(264,'Pampers'),(265,'Panasonic'),(266,'Papa'),(267,'Panaten'),(268,'Perfect Purity'),(269,'Pfizer'),(270,'Philips'),(271,'Philips Avent'),(272,'Physiolac'),(273,'Pigeon'),(274,'Pipi'),(275,'Piyopiyo'),(276,'Play-Doh'),(277,'Playgro'),(278,'Poêmy'),(279,'Poko'),(280,'POLO'),(281,'Pororo'),(282,'Protex'),(283,'Pur'),(284,'Pureen'),(285,'Puritan\'s Pride'),(286,'Pusan'),(287,'Raiya'),(288,'Regenerating'),(289,'Relax'),(290,'Richell'),(291,'Totho'),(292,'Safety 1 st'),(293,'Safety First'),(294,'Saforel'),(295,'Saforelle'),(296,'Saikok'),(297,'Sanosan'),(298,'Sassy'),(299,'Scarlerr'),(300,'Seebaby'),(301,'Sensi'),(302,'Shachu'),(303,'Shiseido'),(304,'Dimba'),(305,'Simplisse'),(306,'Skincare'),(307,'Slimmer'),(308,'Smart-trike'),(309,'Smartlife'),(310,'Sông Hồng'),(311,'Song Long'),(312,'Sonosan'),(313,'Sophie'),(314,'Spawellness'),(315,'Spectra'),(316,'Spoiled Mama'),(317,'Stratamark'),(318,'Sudo Cream'),(319,'Summer Infant'),(320,'Sunhouse'),(321,'Sunplay'),(322,'Supor'),(323,'Tasuamum'),(324,'TC Pharma'),(325,'The Bodyshop'),(326,'The First Year'),(327,'The First Years'),(328,'Tika'),(329,'Tiko'),(330,'Tini Love'),(331,'Tiptop Kid'),(332,'Tiross'),(333,'Today'),(334,'Today Baby'),(335,'Tom Ani'),(336,'Tommee Tippee'),(337,'Tomtom'),(338,'Toyroyal'),(339,'Trường Thọ'),(340,'Tuấn Anh'),(341,'Tùng Anh'),(342,'Tường An'),(343,'Tuti'),(344,'TutiCare'),(345,'Uni'),(346,'Unimom'),(347,'Uniqlo'),(348,'Upass'),(349,'Vaseline'),(350,'VBCare'),(351,'Vichy'),(352,'Vikosan'),(353,'Vinaga'),(354,'Vinamilk'),(355,'Vitacare'),(356,'Vitafusion Prenatal'),(357,'VNPOFOOD'),(358,'VTech'),(359,'Wakame'),(360,'Wakodo'),(361,'Wesser'),(362,'Whirlpoo'),(363,'Winwintoys'),(364,'Winx'),(365,'X-men'),(366,'YaYa'),(367,'Yejimiin'),(368,'Yoko'),(369,'Yoomi'),(370,'Yoomi'),(371,'Silicone'),(372,'Zara'),(373,'ZARACOS');
/*!40000 ALTER TABLE `nhanhieu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-03 16:42:19
