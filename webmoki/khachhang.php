<?php  
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		require "config.php";
		$ham = $_POST["ham"];
		// $ham = $_GET["ham"];

		switch ($ham) {
			case 'layDSSanPhamMuaBan':
				$ham();
				break;
			case 'layThongTinKhachHang':
				$ham();
				break;
			case 'doiMatKhau':
				$ham();
				break;
			case 'capNhatThongTinKhachHang':
				$ham();
				break;
			case 'xoaDiaChi':
				$ham();
				break;
			case 'themDiaChi':
				$ham();
				break;
			case 'suaDiaChi':
				$ham();
				break;
			case 'datDiaChiMacDinh':
				$ham();
				break;
			case 'themSanPhamYeuThich':
				$ham();
				break;
			case 'xoaSanPhamYeuThich':
				$ham();
				break;
			case 'layDSSanPhamYeuThich':
				$ham();
				break;
			case 'layDSTinTuc':
				$ham();
				break;
			case 'guiBinhLuan':
				$ham();
				break;
			case 'themSanPham':
				$ham();
				break;
			case 'muaSanPham':
				$ham();
				break;
		}
	}

	function muaSanPham(){
		global $conn;
		if (isset($_POST["idKhachHang"])) {
			$idKhachHang = $_POST["idKhachHang"];
		}

		if (isset($_POST["idSanPham"])) {
			$idSanPham = $_POST["idSanPham"];
		}

		if (isset($_POST["thoiGian"])) {
			$thoiGian = $_POST["thoiGian"];
		}

		$sql = "INSERT INTO hoadon(idKhachHang, idSanPham, ngayDatHang, trangThai) VALUES('$idKhachHang', '$idSanPham', '$thoiGian', 1)";
		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function themSanPham(){
		global $conn;
		$idKhachHang = $_POST["idKhachHang"];
		$soAnh = $_POST["soAnh"];
		$thoiGian = $_POST["thoiGian"];

		$nameHinhLon = $idKhachHang."hinhLon".$thoiGian.".jpg";
		$upload_path_hinhLon = "hinhsanpham/$nameHinhLon";
		$nameHinhNho = "";
		$dem = 0;
		for($i = 0; $i < $soAnh; $i++){
			$image = $_POST["image".$i];
			if($i == 0) {
				themAnhSanPham($upload_path_hinhLon, $image);
			}else{
				$nameHinh = "hinhsanpham/".$idKhachHang."hinhNho".$thoiGian."".$i.".jpg";
				themAnhSanPham($nameHinh, $image);
				if($dem == 0){
					$nameHinhNho = "/$nameHinh";
					$dem++;
				}else{
					$nameHinhNho .=",/$nameHinh";
				}

			}
		}

		$tenSanPham = $_POST["tenSP"];
		$moTa = $_POST["moTa"];
		$giaChuan = $_POST["giaChuan"];
		$idLoaiSP = $_POST["idLoaiSP"];
		$trangThai = $_POST["trangThai"];
		$khoiLuong = $_POST["khoiLuong"];
		$kichThuoc = $_POST["kichThuoc"];

		$sql = "INSERT INTO sanpham(idNguoiBan, tenSanPham, giaChuan, moTa, hinhLon, hinhNho, idLoaiSP, khoiLuong, kichThuoc, trangThai) VALUES('$idKhachHang', '$tenSanPham', '$giaChuan', 'moTa', '/$upload_path_hinhLon', '$nameHinhNho', '$idLoaiSP', '$khoiLuong', '$kichThuoc', '$trangThai')";

		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function themAnhSanPham($upload_path, $image){
		file_put_contents($upload_path, base64_decode($image));
	}

	function guiBinhLuan(){
		global $conn;
		if (isset($_POST["idKhachHang"])) {
			$idKhachHang = $_POST["idKhachHang"];
		}

		if (isset($_POST["idSanPham"])) {
			$idSanPham = $_POST["idSanPham"];
		}


		if (isset($_POST["noiDung"])) {
			$noiDung = $_POST["noiDung"];
		}

		if (isset($_POST["thoiGian"])) {
			$thoiGian = $_POST["thoiGian"];
		}

		$sql = "INSERT INTO binhluan(idKhachHang, idSanPham, noiDung, thoiGian) VALUES('$idKhachHang', '$idSanPham', '$noiDung', '$thoiGian')";
		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function layDSTinTuc(){
		global $conn;
		if (isset($_POST["limit"])) {
			$limit = $_POST["limit"];
		}

		$sql = "SELECT * FROM tintuc LIMIT ".$limit.", 10";
		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();

		echo "{";
    	echo "\"danhsachtintuc\":";

		if ($ketqua) {
			while ($dong = mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("idTinTuc" => $dong["idTinTuc"], "tieuDe" => $dong["tieuDe"], "noiDung" => $dong["noiDung"], "ngayDang" => $dong["ngayDang"]));
			}
		}

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function layDSSanPhamYeuThich(){
		global $conn;

		if (isset($_POST["idKhachHang"])) {
			$idKhachHang = $_POST["idKhachHang"];
		}

		if(isset($_POST["limit"]))
			$limit = $_POST["limit"];

		$sql = "SELECT * FROM sanpham WHERE idSanPham IN(
	SELECT idSanPham FROM yeuthich WHERE idKhachHang = '$idKhachHang') LIMIT ".$limit.", 10";
		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();

		echo "{";
    	echo "\"danhsachsanpham\":";
		if($ketqua){
			while ($dong = mysqli_fetch_array($ketqua)) {
				$sqlYeuThich = "SELECT COUNT(idYeuThich) soLuotThich FROM yeuthich WHERE idSanPham = ".$dong["idSanPham"];
				$ketQuaThich = mysqli_query($conn, $sqlYeuThich);
				$dongThich = mysqli_fetch_array($ketQuaThich);

				$sqlBinhLuan = "SELECT COUNT(idBinhLuan) soBinhLuan FROM binhluan WHERE idSanPham = ".$dong["idSanPham"];
				$ketQuaBinhLuan = mysqli_query($conn, $sqlBinhLuan);
				$dongBinhLuan = mysqli_fetch_array($ketQuaBinhLuan);

				$chuoijsonloaisp = array();
				$ketQua = layDanhSachLoaiSanPham($conn, $dong["idLoaiSP"]);
				if ($ketQua) {
					while ($dongLoaiSP  = mysqli_fetch_array($ketQua)) {
						array_push($chuoijsonloaisp, array("idLoaiSP" => $dongLoaiSP["idLoaiSP"], "tenLoaiSP" => $dongLoaiSP["tenLoaiSP"], "idLoaiSPCha" => $dongLoaiSP["idLoaiSPCha"]));
					}
				}

				$chuoijsonkhachhang = array();
				$ketQuaKH = layThongTinCoBanNguoiBan($conn, $dong["idNguoiBan"]);
				if ($ketQuaKH) {
					while ($dongKH = mysqli_fetch_array($ketQuaKH)) {
						array_push($chuoijsonkhachhang, array("idKhachHang" => $dongKH["idKhachHang"], "tenKhachHang" => $dongKH["tenKhachHang"], "anhInfoKH" => $dongKH["anhInfoKH"], "diemTinCay" => $dongKH["diemTinCay"], "soSanPham" => $dongKH["soSanPham"]));
					}
				}

				$sqlNoiBan = "SELECT diaChi FROM diachikhachhang WHERE idKhachHang = ".$dong["idNguoiBan"]." AND macDinh = 1";
				$ketQuaDC = mysqli_query($conn, $sqlNoiBan);
				$dongNoiBan = mysqli_fetch_array($ketQuaDC);
				array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dongBinhLuan["soBinhLuan"], "soLuotThich" => $dongThich["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"]));
			}
			
		}

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function layThongTinCoBanNguoiBan($conn, $idKhachHang){
		$sql = "SELECT kh.idKhachHang, kh.tenKhachHang, kh.anhInfoKH, kh.diemTinCay, COUNT(sp.idSanPham) soSanPham FROM khachhang kh, sanpham sp WHERE kh.idKhachHang = ".$idKhachHang." AND kh.idKhachHang = sp.idNguoiBan";
		$ketQua = mysqli_query($conn, $sql);
		return $ketQua;
	}


	function themSanPhamYeuThich(){
		global $conn;
		if (isset($_POST["idKhachHang"])) {
			$idKhachHang = $_POST["idKhachHang"];
		}

		if (isset($_POST["idSanPham"])) {
			$idSanPham = $_POST["idSanPham"];
		}

		$sql = "INSERT INTO yeuthich(idKhachHang, idSanPham) VALUES('$idKhachHang', '$idSanPham')";
		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function xoaSanPhamYeuThich(){
		global $conn;
		if (isset($_POST["idKhachHang"])) {
			$idKhachHang = $_POST["idKhachHang"];
		}

		if (isset($_POST["idSanPham"])) {
			$idSanPham = $_POST["idSanPham"];
		}

		$sql = "DELETE FROM yeuthich WHERE idKhachHang = '$idKhachHang' AND idSanPham = '$idSanPham'";
		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function xoaDiaChi(){
		global $conn;
		if(isset($_POST["idKhachHang"]))
			$idKhachHang = $_POST["idKhachHang"];
		if(isset($_POST["diaChi"]))
			$diaChi = $_POST["diaChi"];


		$sql = "DELETE FROM diachikhachhang WHERE diaChi LIKE '$diaChi' AND idKhachHang = '$idKhachHang'";
		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function themDiaChi(){
		global $conn;
		$macDinh = 0;
		if(isset($_POST["idKhachHang"]))
			$idKhachHang = $_POST["idKhachHang"];
		if(isset($_POST["diaChi"]))
			$diaChi = $_POST["diaChi"];
		if (isset($_POST["macDinh"]))
			$macDinh = $_POST["macDinh"];

		$sql = "SELECT * FROM diachikhachhang WHERE diaChi LIKE '$diaChi' AND idKhachHang = '$idKhachHang'";
		$ketqua2 = mysqli_query($conn, $sql);

		if (mysqli_num_rows($ketqua2) > 0) {
			echo json_encode(array('response' => 0));
		}else{
			if($macDinh == 1){
				$sql1 = "UPDATE diachikhachhang SET macDinh = 0 WHERE idKhachHang = '$idKhachHang'";
				$ketqua1 = mysqli_query($conn, $sql1);
			}

			$sql = "INSERT INTO diachikhachhang(idKhachHang, diaChi, macDinh) values('$idKhachHang', '$diaChi', '$macDinh')";
			$ketqua = mysqli_query($conn, $sql);
			if ($ketqua) {
				echo json_encode(array('response' => 1));
			}else{
				echo json_encode(array('response' => 0));
			}
		}
	}

	function suaDiaChi(){
		global $conn;
		$macDinh = 0;
		if(isset($_POST["idKhachHang"]))
			$idKhachHang = $_POST["idKhachHang"];
		if(isset($_POST["diaChiCu"]))
			$diaChiCu = $_POST["diaChiCu"];
		if(isset($_POST["diaChi"]))
			$diaChi = $_POST["diaChi"];
		if (isset($_POST["macDinh"]))
			$macDinh = $_POST["macDinh"];

		$sql = "SELECT * FROM diachikhachhang WHERE !(diaChi LIKE '$diaChiCu') AND diaChi LIKE '$diaChi' AND idKhachHang = '$idKhachHang'";
		$ketqua2 = mysqli_query($conn, $sql);

		if (mysqli_num_rows($ketqua2) > 0) {
			echo json_encode(array('response' => 0));
		}else{
			if($macDinh == 1){
				$sql1 = "UPDATE diachikhachhang SET macDinh = 0 WHERE idKhachHang = '$idKhachHang'";
				$ketqua1 = mysqli_query($conn, $sql1);
			}

			$sql = "UPDATE diachikhachhang SET diaChi = '$diaChi', macDinh = '$macDinh' WHERE diaChi LIKE '$diaChiCu' AND idKhachHang = '$idKhachHang'";
			$ketqua = mysqli_query($conn, $sql);
			if ($ketqua) {
				echo json_encode(array('response' => 1));
			}else{
				echo json_encode(array('response' => 0));
			}
		}
	}

	function datDiaChiMacDinh(){
		global $conn;
		if(isset($_POST["idKhachHang"]))
			$idKhachHang = $_POST["idKhachHang"];
		if(isset($_POST["diaChi"]))
			$diaChi = $_POST["diaChi"];
		$sql1 = "UPDATE diachikhachhang SET macDinh = 0 WHERE idKhachHang = '$idKhachHang'";
		$ketqua1 = mysqli_query($conn, $sql1);

		$sql = "UPDATE diachikhachhang SET macDinh = 1 WHERE diaChi LIKE '$diaChi' AND idKhachHang = '$idKhachHang'";
		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}


	function doiMatKhau(){
		global  $conn;
		$idKhachHang = $_POST["idKhachHang"];
		$matKhauMoi = $_POST["matKhau"];

		$sql = "UPDATE khachhang SET matKhau = '$matKhauMoi' WHERE idKhachHang = '$idKhachHang'";
		$ketqua = mysqli_query($conn, $sql);
		if ($ketqua) {
			echo json_encode(array('response' => 1));
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function capNhatThongTinKhachHang(){
		global  $conn;
		$dem = 0;
		$idKhachHang = $_POST["idKhachHang"];
		$sql = "UPDATE khachhang SET ";
		if (isset($_POST["name"])) {
			$name = $_POST["name"];
			$upload_path = "hinhinfo/$name.jpg";
			$sql.="anhInfoKH = '/$upload_path'";
			$dem += 1;
		}
		if (isset($_POST["nameAnhBia"])) {
			$nameAnhBia = $_POST["nameAnhBia"];
			$upload_path_anhbia = "hinhinfo/$nameAnhBia.jpg";
			if ($dem > 0) {
				$sql.=", anhBia = '/$upload_path_anhbia'";
			}else{
				$sql.="anhBia = '/$upload_path_anhbia'";
			}
			$dem += 1;
		}
		if (isset($_POST["moTa"])) {
			$moTa = $_POST["moTa"];
			if ($dem > 0) {
				$sql.=", moTa = '$moTa'";
			}else{
				$sql.="moTa = '$moTa'";
			}
			$dem += 1;
		}
		if ($dem > 0) {
			$sql.=" WHERE idKhachHang = '$idKhachHang'";
			$ketqua = mysqli_query($conn, $sql);
			if ($ketqua) {
				if(isset($_POST["name"])){
					$image = $_POST["image"];
					file_put_contents($upload_path, base64_decode($image));
				}
				if(isset($_POST["nameAnhBia"])){
					$imageAnhBia = $_POST["imageAnhBia"];
					file_put_contents($upload_path_anhbia, base64_decode($imageAnhBia));
				}
				echo json_encode(array('response' => 1));
			}else{
				echo mysqli_error($conn);
			}
		}else{
			echo json_encode(array('response' => 0));
		}
	}

	function layThongTinKhachHang(){
		global $conn;
		if(isset($_POST["soDT"]))
			$soDT = $_POST["soDT"];
		if(isset($_GET["soDT"]))
			$soDT = $_GET["soDT"];

		$sql = "SELECT idKhachHang, tenKhachHang, anhInfoKH, anhBia, moTa, diemTinCay, thoiGianOnline, thoiGianOffline FROM khachhang WHERE soDT = '$soDT'";
		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();

		echo "{";
    	echo "\"khachhang\":";

    	if ($ketqua) {
    		$dong = mysqli_fetch_array($ketqua);
    		$jsondiachi = array();
    		$sqldiachi = "SELECT * FROM diachikhachhang WHERE idKhachHang = ".$dong["idKhachHang"]." ORDER BY macDinh DESC";
    		$ketquadiachi = mysqli_query($conn, $sqldiachi);
    		if ($ketquadiachi) {
    			while ($dongdiachi = mysqli_fetch_array($ketquadiachi)) {
    				array_push($jsondiachi, array("idDiaChi" => $dongdiachi["idDiaChi"], "diaChi" => $dongdiachi["diaChi"], "macDinh" => $dongdiachi["macDinh"]));
    			}
    		}
    		array_push($chuoijson, array("idKhachHang" => $dong["idKhachHang"], "tenKhachHang" => $dong["tenKhachHang"], "anhInfoKH" => $dong["anhInfoKH"], "anhBia" => $dong["anhBia"], "diemTinCay" => $dong["diemTinCay"], "moTa" => $dong["moTa"], "thoiGianOnline" => $dong["thoiGianOnline"], "thoiGianOffline" => $dong["thoiGianOffline"], "diaChi" => $jsondiachi));
    	}

    	echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function layDSSanPhamMuaBan(){
		global $conn;
		if(isset($_POST["idKhachHang"]))
			$idKhachHang = $_POST["idKhachHang"];
		if(isset($_GET["idKhachHang"]))
			$idKhachHang = $_GET["idKhachHang"];
		
		if (isset($_POST["limit"])) {
			$limit = $_POST["limit"];
		}
		if(isset($_POST["loaiSanPham"])){
			$loaisanpham = $_POST["loaiSanPham"];
		}
		if(isset($_POST["trangThai"])){
			$trangThai = $_POST["trangThai"];
		}
		//lấy sản phẩm bán
		if($loaisanpham == 1){
			// 1 - đã đặt hàng, 2 - đang giao hàng, 3 - giao hàng hủy, 4 - giao hàng thành công
			if($trangThai == 0){
				//lấy sản phẩm bán
				$sql = "SELECT * FROM sanpham WHERE idNguoiBan = ".$idKhachHang." limit ".$limit.", 10";
			}else if($trangThai == 1){
				//lấy sản phẩm bán và trong trạng thái đang xử lý
				$sql = "SELECT sp.*  FROM sanpham sp, hoadon hd where sp.idSanPham = hd.idSanPham and idNguoiBan = '$idKhachHang' and (hd.trangThai = 1 or hd.trangThai = 2) group by idSanPham limit ".$limit.", 10";
			}else if($trangThai == 2){
				//lấy sản phẩm bán và đăng bán thành công
				$sql = "SELECT sp.*  FROM sanpham sp, hoadon hd where sp.idSanPham = hd.idSanPham and idNguoiBan = '$idKhachHang' and hd.trangThai = 4 group by idSanPham limit ".$limit.", 10";
			}
		}else{
			if($trangThai == 1){
				//lấy sản phẩm mua và trong trạng thái đang xử lý
				$sql = "SELECT sp.*  FROM sanpham sp, hoadon hd where sp.idSanPham = hd.idSanPham and idKhachHang = '$idKhachHang' and (hd.trangThai = 1 or hd.trangThai = 2) group by idSanPham limit ".$limit.", 10";
			}else if($trangThai == 2){
				//lấy sản phẩm mua và đăng bán thành công
				$sql = "SELECT sp.*  FROM sanpham sp, hoadon hd where sp.idSanPham = hd.idSanPham and idKhachHang = '$idKhachHang' and hd.trangThai = 4 group by idSanPham limit ".$limit.", 10";
			}
		}

		
		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();

		echo "{";
    	echo "\"danhsachsanpham\":";

		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
				$sqlYeuThich = "SELECT COUNT(idYeuThich) soLuotThich FROM yeuthich WHERE idSanPham = ".$dong["idSanPham"];
				$ketQuaThich = mysqli_query($conn, $sqlYeuThich);
				$dongThich = mysqli_fetch_array($ketQuaThich);

				$sqlBinhLuan = "SELECT COUNT(idBinhLuan) soBinhLuan FROM binhluan WHERE idSanPham = ".$dong["idSanPham"];
				$ketQuaBinhLuan = mysqli_query($conn, $sqlBinhLuan);
				$dongBinhLuan = mysqli_fetch_array($ketQuaBinhLuan);

				$chuoijsonkhachhang = array();

				$ketQuaKH = layThongTinCoBanNguoiBan($conn, $dong["idNguoiBan"]);
				if ($ketQuaKH) {
					while ($dongKH = mysqli_fetch_array($ketQuaKH)) {
						array_push($chuoijsonkhachhang, array("idKhachHang" => $dongKH["idKhachHang"], "tenKhachHang" => $dongKH["tenKhachHang"], "anhInfoKH" => $dongKH["anhInfoKH"], "diemTinCay" => $dongKH["diemTinCay"], "soSanPham" => $dongKH["soSanPham"]));
					}
				}

				$sqlNoiBan = "SELECT diaChi FROM diachikhachhang WHERE idKhachHang = ".$dong["idNguoiBan"]." AND macDinh = 1";
				$ketQuaDC = mysqli_query($conn, $sqlNoiBan);
				$dongNoiBan = mysqli_fetch_array($ketQuaDC);

				$chuoijsonloaisp = array();
				$ketQua = layDanhSachLoaiSanPham($conn, $dong["idLoaiSP"]);
				if ($ketQua) {
					while ($dongLoaiSP  = mysqli_fetch_array($ketQua)) {
						array_push($chuoijsonloaisp, array("idLoaiSP" => $dongLoaiSP["idLoaiSP"], "tenLoaiSP" => $dongLoaiSP["tenLoaiSP"], "idLoaiSPCha" => $dongLoaiSP["idLoaiSPCha"]));
					}
				}

				$yeuthich =  "false";
				$sqlSanPhamYeuThich = "SELECT *  FROM yeuthich WHERE idKhachHang = '$idKhachHang' AND idSanPham = ".$dong["idSanPham"];
				$ketQuaYT = mysqli_query($conn, $sqlSanPhamYeuThich);
				if(mysqli_num_rows($ketQuaYT) > 0) 
						$yeuthich = "true";
				array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dongBinhLuan["soBinhLuan"], "soLuotThich" => $dongThich["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"], "yeuThich" => $yeuthich));
			}
		}

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	// lấy danh sách loại sản phẩm từ loại cha lớn nhất đến loại con truyền vào
	function layDanhSachLoaiSanPham($conn, $idLoaiSPCon){
		$sql =  "SELECT * FROM loaisanpham lsp WHERE lsp.idLoaiSP = ".$idLoaiSPCon." OR lsp.idLoaiSP IN(
			SELECT lsp1.idLoaiSPCha FROM loaisanpham lsp1 WHERE lsp1.idLoaiSP = ".$idLoaiSPCon." OR lsp1.idLoaiSP IN(
			SELECT lsp2.idLoaiSPCha FROM loaisanpham lsp2 WHERE lsp2.idLoaiSP = ".$idLoaiSPCon."))";
		$ketQua = mysqli_query($conn, $sql);
		return $ketQua;
	}

?>