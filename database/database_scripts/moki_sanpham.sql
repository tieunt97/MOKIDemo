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
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sanpham` (
  `idSanPham` int(11) NOT NULL AUTO_INCREMENT,
  `idNguoiBan` int(11) NOT NULL,
  `tenSanPham` varchar(45) DEFAULT NULL,
  `giaChuan` int(11) DEFAULT NULL,
  `moTa` text,
  `hinhLon` text,
  `hinhNho` text,
  `idLoaiSP` int(11) DEFAULT NULL,
  `khoiLuong` varchar(45) DEFAULT NULL,
  `kichThuoc` varchar(45) DEFAULT NULL,
  `trangThai` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSanPham`),
  KEY `keySanPham_KhachHang_idx` (`idNguoiBan`),
  KEY `keySanPham_LoaiSP_idx` (`idLoaiSP`),
  FULLTEXT KEY `tenSanPham` (`tenSanPham`),
  CONSTRAINT `keySanPham_KhachHang` FOREIGN KEY (`idNguoiBan`) REFERENCES `khachhang` (`idKhachHang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `keySanPham_LoaiSP` FOREIGN KEY (`idLoaiSP`) REFERENCES `loaisanpham` (`idLoaiSP`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,2,'Áo thun',0,'áo cũng còn khá mới. bạn nào dưới 50kg liên hệ mình nha','/hinhsanpham/1lon.jpg','/hinhsanpham/1nho1.jpg,/hinhsanpham/1nho2.jpg,/hinhsanpham/1nho3.jpg',1,'0.5 - 1.0kg','12cmx12cmx12cm','Tốt'),(2,2,'bình sữa tommee tippee 260ml',250000,'Có màu xanh hồng tách set hoặc full set nhé các mom','/hinhsanpham/2lon.jpg','/hinhsanpham/2nho.jpg',60,NULL,NULL,'Mới'),(3,2,'Giày tập đi đế kết Uala',145000,'Giày tập đi đế kết Uala - UR6630\r\nThương hiệu: UALA & ROGO | Mã hàng: GDTE-788\r\n------\r\nThông tin nổi bật của giày tập đi đế kết Uala - UR6630\r\n- Chất vải mềm mại với lớp lót trong êm ái, không gây ảnh hưởng tới sự phát triển của xương bàn chân bé\r\n\r\n- Đế bằng cao su kết với các rãnh chống trơn trượt hiệu quả, an toàn cho con.\r\n\r\n- Lớp lót đế trong mềm, với thiết kế tiện dụng, có thể làm giảm áp lực vào bàn chân của bé hiệu quả, hấp thụ lực ở gót chân bảo vệ bé an toàn trẻ mới biết đi.\r\n\r\n- Khóa dính tiện lợi, mẹ dễ dàng đi giày cho bé đồng thời điều chỉnh cho giày ôm vừa chân trẻ\r\n\r\n- Kiểu giày tập đi giúp giữ ấm đôi chân cho bé, đặc biệt phù hợp để bé đi vào tiết trời mùa đông lạnh\r\n\r\n- Màu sắc, họa tiết ngộ nghĩnh\r\n\r\n- Size: 130, 140, 150 trong đó\r\n\r\n+ Size 130: chiều dài 11.5 cm, phù hợp cho bé từ 9 - 12 tháng\r\n\r\n+ Size 140: chiều dài 12 cm, phù hợp cho bé từ 13 - 15 tháng\r\n\r\n+ Size 150: chiều dài 12.5 cm, phù hợp cho bé từ 16 - 19 tháng\r\n\r\n- Xuất xứ: Việt Nam','/hinhsanpham/3lon.jpg','/hinhsanpham/3nho.jpg',74,NULL,NULL,'Mới'),(4,2,'Giường lưới Fancy 60 x 120 cm',380000,'Giường lưới Fancy 60 x 120 cm\r\nMã hàng: CUI-165\r\n--------\r\nKích thước: 120 x 60 x 10 (cm)\r\nMặt lưới ngoại nhập siêu bền, không co giãn\r\nChất liệu: inox\r\n\r\nThông tin chi tiết sản phẩm\r\n- Tên sản phẩm: Giường lưới Fancy 60 x 120 cm\r\n\r\n- Kích thước: 120 x 60 x 10 (cm)\r\n\r\n- Trọng lượng: 3kg\r\n\r\n- Thanh Inox chịu lực giúp kết cấu giường vững chắc trong suốt quá trình sử dụng.\r\n\r\n- Lưới: Lưới ngoại nhập siêu bền không thấm nước, không bị co giãn.\r\n\r\n- Chân góc bằng nhựa nguyên sinh an toàn, bền đẹp, chịu lực tốt, không thấm nước.\r\n\r\n- Mặt giường lưới trẻ em cách mặt đất một khoảng thích hợp nên rất êm và thoải mái.\r\n\r\n- Khung được sơn màu tĩnh điện – Chân đế nhựa chắc chắn, an toàn cho bé\r\n\r\n- Căng, chặt với thiết kế thông minh của 1 vòng bạt may kết nối bao quanh toàn bộ khung (không phải loại căng dây) nên đạt độ bền tối ưu nhất.\r\n\r\n- Giường lưới nhỏ gọn, dễ di chuyển nên rất phù hợp với những nhà có diện tích nhỏ, đặc biệt là nhà trọ.','/hinhsanpham/4lon.jpg','/hinhsanpham/4nho1.jpg,/hinhsanpham/4nho2.jpg,/hinhsanpham/4nho3.jpg',85,'Trên 9.5kg đến 10kg','1cm x 1cm x 1cm','Mới'),(5,1,'ĐỒ CHƠI SÂU 3 TRONG 1 MUNCHKIN',190000,'ĐỒ CHƠI SÂU 3 TRONG 1 MUNCHKIN MK11080\r\n\r\n\r\nĐồ chơi sâu 3 trong 1 Munchkin MK11080 là sản phẩm đồ chơi mang tính giáo dục giúp bé có thể tạo hình và sáng tạo với các miếng ghép có màu sắc khác nhau theo ý muốn. Bé có thể chơi bằng cách nối 7 chiếc cốc sặc sỡ thành một chú sâu ngộ nghĩnh, đáng yêu hay xếp chồng chúng lên nhau theo thứ tự từ lớn đến nhỏ. Bộ đồ chơi thích hợp chơi khi tắm hoặc những lúc vui chơi thông thường. Với màu sắc đa dạng, bé có thể làm quen và phân biệt màu sắc, số lượng, kích cỡ khi chơi. Bên cạnh đó, đồ chơi còn tăng cường khả năng sáng tạo và kỹ năng vận động cho bé trong khoảng thời gian đầu phát triển.','/hinhsanpham/5lon.jpg','/hinhsanpham/5nho1.jpg,/hinhsanpham/5nho2.jpg,/hinhsanpham/5nho3.jpg',102,NULL,NULL,'Mới'),(6,2,'tả vải Bambi Mio',100000,'Tả vải bambi Mio số 1 Việt Nam, cho bé năng động, tiết kiệm cho mẹ 90% chi phí, tả dùng như mặc quần vậy, mặc như mặc chiếc quần bình thường, sản phẩm chất lượng, chỉ còn size L, XL cho bé 9 kg đến 20 kg, có nút nới rộng chật theo cỡ của bé,','/hinhsanpham/6lon.jpg','/hinhsanpham/6nho.jpg',104,'Trên 12kg đến 12.5kg','1cm x 1cm x 1cm','Mới'),(7,1,'Dầu gió Khuynh Diệp',60000,'Mã sản phẩm: 43300018\r\n\r\nDầu gió khuynh diệp TS 24ml xoa bóp ngoài da tại chỗ đau. Cảm cúm, sổ mũi, nghẹt mũi : xoa dầu hai bên thái dương, cổ, sau gáy, mũi. Nhức đầu, chóng mặt, buồn nôn : xoa dầu hai bên thái dương, cổ, nhân trung, mũi.\r\n\r\nDùng được cho cả trẻ em và trẻ sơ sinh','/hinhsanpham/7lon.jpg','/hinhsanpham/7nho.jpg',7,NULL,NULL,'Mới'),(8,1,'Bộ túi cho mẹ và bé 5 chi tiết',350000,'Bộ túi cho mẹ và bé 5 chi tiết by Chính hãng\r\nMã sản phẩm: 32000065 (mã cũ: 2010001)\r\n------------\r\nBộ sản phẩm gồm:\r\n1 túi to đựng bỉm, quần áo cho bé (41 x 18 x 33cm)\r\n1 túi nhỏ đựng khăn, các vật dụng nhỏ cần thiết (24 x 10 x 22)\r\n1 tấm lót tiện dụng chống thấm 2 mặt (31 x 55cm)\r\n1 túi đựng bình sữa (20 x 8cm)\r\n1 túi đựng thức ăn (13 x 12 x 7cm)','/hinhsanpham/8lon.jpg','/hinhsanpham/8nho1.jpg,/hinhsanpham/8nho2.jpg,/hinhsanpham/8nho3.jpg',8,'Trên 1kg đến 1.5kg','1cm x 1cm x 1cm','Mới'),(9,1,'Đồ chơi gỗ GCB',115000,'Đồ chơi gỗ GCB - Đập cọc vui nhộn DB08\r\nThương hiệu: GCB | Mã hàng: DCG-69641\r\n--------------\r\nĐặc điểm nổi bật của đập cọc vui nhộn DB08\r\n\r\n- Bộ sản phẩm gồm: 1 cái búa để gõ + 4 chú chuột nhỏ có 4 màu khác nhau. \r\n\r\n- Bé dùng búa đập vào thanh gỗ phía dưới, chú chuột sẽ nhảy lên, khiến bế rất thích thú. Giúp bé vận động và rèn luyện đôi tay khéo léo.\r\n\r\n- Bộ đồ chơi đập chuột giúp bé luyện tập khả năng phản xạ tốt, tăng cường vận động, nhanh nhẹn.\r\n\r\n- Không chỉ là một trò giải trí, trò đập chuột vô cùng thú vị này sẽ giúp bé của bạn luyện được khả năng nhanh mắt, nhanh tay.\r\n\r\n- Giúp bé phát triển trí thông minh, khả năng sáng tạo và trí tưởng tượng phong phú\r\n\r\n- Chất liệu: Được làm từ gỗ an toàn cho bé\r\n\r\n- Dành cho bé từ 3 tuổi.\r\n\r\n- Xuất xứ: Việt Nam.','/hinhsanpham/9lon.jpg','/hinhsanpham/9nho1.jpg,/hinhsanpham/9nho2.jpg,/hinhsanpham/9nho3.jpg',9,'Trên 0.5kg đến 1kg',NULL,'Mới'),(10,2,'Búp bê LV mũi Sắt',270000,'size 35 - 39 2 màu','/hinhsanpham/10lon.jpg','/hinhsanpham/10nho.jpg',10,NULL,NULL,'Mới'),(11,1,'Gối Cao Su Siny A Kim Cương',350000,'THÔNG TIN SẢN PHẨM:\r\n- Gối cao su thiên nhiên Kim Cương Siny A (hình lượn sóng) \r\n- Kích thước: 44cm x 64cm \r\n- Được sản xuất từ 100% cao su thiên nhiên,được xử lý bằng công nghệ tiên tiến, tiệt trùng vi khuẩn tác động đến da. \r\n- Hình dạng cấu trúc hợp lý, độ đàn hồi tối ưu tạo sự dễ chịu cho người sử dụng. \r\n- Bề mặt gối cao su Kim Cương được thiết kế nhiều lỗ thoáng nhỏ tạo sự thông thoáng nên có thể sử dụng cho tất cả các mùa. \r\n- Mùi thơm tự nhiên, không gây khó chịu cho người sử dụng. \r\n- Chất lượng sản phẩm được kiểm soát bởi Hệ thống quản lý chất lượng đạt Tiêu chuẩn ISO 9001:2008.','/hinhsanpham/11lon.jpg','/hinhsanpham/11nho1.jpg,/hinhsanpham/11nho2.jpg',11,'Trên 1kg đến 1.5kg','45cm x 65cm x 13cm','Mới'),(12,2,'Dây dắt trẻ tập đi Royal',80000,'Dây dắt trẻ tập đi Royal - kiểu ngực\r\nThương hiệu: Royal | Mã hàng: DIU-171\r\n---------------\r\nĐặc điểm của dây dắt trẻ tập đi Royal - kiểu ngực\r\n- Xuất xứ: Việt Nam\r\n\r\n- Màu sắc: có nhiều màu sắc cho mẹ chọn lựa\r\n\r\n- Kích thước: 14cm x 57cm (không tính phần dây)\r\n\r\n- Kiểu dây dắt ôm ngực, dành cho bé từ 6 - 24 tháng tuổi\r\n\r\nNhiều tính năng\r\n\r\n- Giúp bé tập đi một cách thăng bằng và tự do, không cần tay vịn\r\n\r\n- Giữ bé tránh xa bề mặt có nhiều chướng ngại, không bằng phẳng.\r\n\r\n- Dây dắt trẻ tập đi Royal được thiết kế để giúp bé tập đi một cách an toàn và thoải mái.','/hinhsanpham/12lon.jpg','/hinhsanpham/12nho.jpg',12,'Trên 0.5kg đến 1kg','1cm x 1cm x 1cm','Mới'),(13,1,'Ghế ăn dặm Pouch',3500000,'Nhà mình hiện có một ghế ăn dặm của thương hiệu đồ trẻ em nổi tiếng Pouch. \r\nGhế được thiết kế hình quả trứng rất đẹp, độc đáo, sang trọng, có thể ngả 3 tư thế, điều chỉnh độ cao, rất an toàn với đai chốt 5 điểm, khung hình chữ Z tránh nghiêng đổ.\r\nGhế còn mới nguyên, nhà mình ko có nhu cầu sử dụng, muốn pass lại cho mẹ nào có nhu cầu.\r\nAi có nhu cầu ib mình ạ, giá hữu nghị bằng 70% giá sản phẩm đang bán tại việt nam ^_^ ^_^','/hinhsanpham/13lon.jpg','/hinhsanpham/13nho1.jpg,/hinhsanpham/13nho2.jpg,/hinhsanpham/13nho3.jpg',17,'Trên 9.5kg đến 10kg','20cm x 15cm x 60cm','Mới'),(14,2,'Sữa Enfamil PREMIUM',600000,'+ Sữa Enfamil Premium Infant về thêm dòng Non-Gmo dành cho bé từ 0-12 tháng mẫu mới nhất đã có mặt tại shop ^_^\r\n+Sữa Enfamil PREMIUM Infant Formula là loại sữa công thức duy nhất được chứng minh là có lợi cho sự phát triển trí não và đôi mắt của bé với công thức có chứa DHA và ARA, giàu chất sắt.\r\n+Sữa Enfamil PREMIUM Infant Formula với công thức duy nhất có chứa Natural Defense™ Dual Prebiotic Blend, tương tự như thành phần có trong sữa mẹ, thúc đẩy sự phát triển và tăng cường sức đề kháng cho bé trong năm đầu đời, thành phần protein dễ hấp thu giúp bé dễ tiêu hóa.\r\nHộp đựng không chứa BPA (BPA-free), có thể tái sử dụng để đựng sữa (dạng đóng hộp giấy).','/hinhsanpham/14lon.jpg','/hinhsanpham/14nho.jpg',47,'Trên 0.5kg đến 1kg',NULL,'Mới'),(23,1,'Demo',90000,'moTa','/hinhsanpham/1hinhLon20180512_202404.jpg','/hinhsanpham/1hinhNho20180512_2024041.jpg',19,'','','Tốt'),(24,1,'bé ăn',120000,'moTa','/hinhsanpham/1hinhLon20180514_230219.jpg','/hinhsanpham/1hinhNho20180514_2302191.jpg,/hinhsanpham/1hinhNho20180514_2302192.jpg',60,'','','Mới'),(25,1,'miễn phí',0,'moTa','/hinhsanpham/1hinhLon20180517_212228.jpg','',1,'','','Tốt'),(26,1,'hihi',185000,'moTa','/hinhsanpham/1hinhLon20180602_205252.jpg','/hinhsanpham/1hinhNho20180602_2052521.jpg',82,'','','Mới');
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
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
