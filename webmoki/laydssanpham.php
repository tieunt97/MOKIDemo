<?php
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		require "config.php";
		$ham = $_POST["ham"];
		// $ham = $_GET["ham"];

		switch ($ham) {
			case 'layDanhSachSanPhamTheoLoaiSP':
				$ham();
				break;
			case 'layDanhSachSanPham':
				$ham();
				break;
			case 'layDanhSachBinhLuan':
				$ham();
				break;
			case 'layDSDanhMucCon':
				$ham();
				break;
			case 'layDSSanPhamTimKiem':
				$ham();
				break;
		}
	}

	function layDSSanPhamTimKiem(){
		global $conn;

		$idKhachHang = 0;
		if (isset($_POST["idKhachHang"])) {
			$idKhachHang = $_POST["idKhachHang"];
		}

		if(isset($_POST["limit"]))
			$limit = $_POST["limit"];

		$dem = 0;

		$sql = "SElECT sp2.*, COUNT(DISTINCT yt.idYeuThich) AS soLuotThich FROM (SELECT sp1.*, COUNT(DISTINCT bl.idBinhLuan) AS soBinhLuan  
FROM sanpham sp1 LEFT JOIN binhluan bl ON sp1.idSanPham = bl.idSanPham
GROUP BY sp1.idSanPham) sp2 LEFT JOIN yeuthich yt ON sp2.idSanPham = yt.idSanPham
GROUP BY sp2.idSanPham";

		if(isset($_POST["tenSP"])){
			$tenSanPham = $_POST["tenSP"];
			$sql = "SElECT sp2.*, COUNT(DISTINCT yt.idYeuThich) AS soLuotThich FROM (SELECT sp1.*, COUNT(DISTINCT bl.idBinhLuan) AS soBinhLuan  
FROM (select * from sanpham where match(tenSanPham) against('$tenSanPham')) sp1 LEFT JOIN binhluan bl ON sp1.idSanPham = bl.idSanPham
GROUP BY sp1.idSanPham) sp2 LEFT JOIN yeuthich yt ON sp2.idSanPham = yt.idSanPham
GROUP BY sp2.idSanPham";
		}
		if(isset($_POST["idLoaiSP"])){
			$idLoaiSP = $_POST["idLoaiSP"];
			if($dem == 0){
				$sql .= " having (idLoaiSP = '$idLoaiSP' OR idLoaiSP IN(SELECT idLoaiSP FROM loaisanpham lsp WHERE lsp.idLoaiSPCha = '$idLoaiSP' OR lsp.idLoaiSPCha IN(SELECT idLoaiSP FROM loaisanpham lsp1 WHERE lsp1.idLoaiSPCha = '$idLoaiSP')))";
			}else{
				$sql .= " and (idLoaiSP = '$idLoaiSP' OR idLoaiSP IN(SELECT idLoaiSP FROM loaisanpham lsp WHERE lsp.idLoaiSPCha = '$idLoaiSP' OR lsp.idLoaiSPCha IN(SELECT idLoaiSP FROM loaisanpham lsp1 WHERE lsp1.idLoaiSPCha = '$idLoaiSP')))";
			}
			
			$dem += 1;
		}

		if(isset($_POST["trangThai"])){
			$trangThai = $_POST["trangThai"];

			if($dem == 0){
				$sql .= " having trangThai like \"%".$trangThai."%\"";
				$dem += 1;
			}else{
				$sql .= " and trangThai like \"%".$trangThai."%\"";
			}
		}

		if (isset($_POST["giaThap"]) && isset($_POST["giaCao"])) {
			$giaThap = $_POST["giaThap"];
			$giaCao = $_POST["giaCao"];
			
			if($dem == 0){
				$sql .= " having (giaChuan between '$giaThap' and '$giaCao')";
				$dem += 1;
			}else{
				$sql .= " and (giaChuan between '$giaThap' and '$giaCao')";
			}
		}

		$sql .= " limit ".$limit.", 10";

		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();

		echo "{";
    	echo "\"danhsachsanpham\":";
    	
		if($ketqua){
			while ($dong = mysqli_fetch_array($ketqua)) {

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

				if($idKhachHang != 0){
					$yeuthich =  "false";
					$sqlSanPhamYeuThich = "SELECT *  FROM yeuthich WHERE idKhachHang = '$idKhachHang' AND idSanPham = ".$dong["idSanPham"];
					$ketQuaYT = mysqli_query($conn, $sqlSanPhamYeuThich);
					if(mysqli_num_rows($ketQuaYT) > 0) 
						$yeuthich = "true";
					array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dong["soBinhLuan"], "soLuotThich" => $dong["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"], "yeuThich" => $yeuthich));
				}else{
					array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dong["soBinhLuan"], "soLuotThich" => $dong["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"]));
				}
			}
			
		}

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function layDSDanhMucCon(){
		global $conn;
		$idLoaiSPCha = $_POST["idLoaiSPCha"];
		$sql = "SELECT lsp.*, COUNT(DISTINCT lsp1.idLoaiSP) as soLoaiCon FROM loaisanpham lsp LEFT JOIN loaisanpham lsp1 ON lsp.idLoaiSP = lsp1.idLoaiSPCha GROUP BY lsp.idLoaiSP having idLoaiSPCha = '$idLoaiSPCha'";
		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();

		echo "{";
    	echo "\"danhmuc\":";
    	if ($ketqua) {
			while ($dong = mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("idLoaiSP" => $dong["idLoaiSP"], "tenLoaiSP" => $dong["tenLoaiSP"], "soLoaiCon" => $dong["soLoaiCon"]));
			}
		}
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function layDanhSachSanPhamTheoLoaiSP(){
		global $conn;
		$idKhachHang = 0;

		if (isset($_POST["idKhachHang"])) {
			$idKhachHang = $_POST["idKhachHang"];
		}
		if (isset($_POST["idLoaiSP"])) {
			$idLoaiSP = $_POST["idLoaiSP"];
		}

		if (isset($_POST["idLoaiSP"])) {
			$idLoaiSP = $_POST["idLoaiSP"];
		}

		if(isset($_POST["limit"]))
			$limit = $_POST["limit"];

		if(isset($_GET["limit"]))
			$limit = $_GET["limit"];
		
		$giaTri = "idSanPham";
		if(isset($_POST["giaTri"]))
			$giaTri = $_POST["giaTri"];

		$sapXep = "ASC";
		if (isset($_POST["sapXep"])) {
			$sapXep = $_POST["sapXep"];
		}
		layDanhSachSanPhamTheoDanhMuc($conn, $idLoaiSP, $limit, $idKhachHang, $giaTri, $sapXep);
	}

//lấy danh sách sản phẩm theo idLoaiSP với tối đa 3 mức idLoaiSP
	function layDanhSachSanPhamTheoDanhMuc($conn, $idLoaiSP, $limit, $idKhachHang, $giaTri, $sapXep){
		if($idLoaiSP == 0){
			if(isset($_POST["giaThap"])){
				$giaThap = $_POST["giaThap"];
				$giaCao = $_POST["giaCao"];
				$sql = "SElECT sp2.*, COUNT(DISTINCT yt.idYeuThich) AS soLuotThich FROM (SELECT sp1.*, COUNT(DISTINCT bl.idBinhLuan) AS soBinhLuan  
			FROM sanpham sp1 LEFT JOIN binhluan bl ON sp1.idSanPham = bl.idSanPham
			GROUP BY sp1.idSanPham) sp2 LEFT JOIN yeuthich yt ON sp2.idSanPham = yt.idSanPham
			GROUP BY sp2.idSanPham HAVING giaChuan BETWEEN '$giaThap' AND '$giaCao ORDER BY ".$giaTri." ".$sapXep." LIMIT ".$limit.", 10";
			}else{
				$sql = "SElECT sp2.*, COUNT(DISTINCT yt.idYeuThich) AS soLuotThich FROM (SELECT sp1.*, COUNT(DISTINCT bl.idBinhLuan) AS soBinhLuan  
			FROM sanpham sp1 LEFT JOIN binhluan bl ON sp1.idSanPham = bl.idSanPham
			GROUP BY sp1.idSanPham) sp2 LEFT JOIN yeuthich yt ON sp2.idSanPham = yt.idSanPham
			GROUP BY sp2.idSanPham ORDER BY ".$giaTri." ".$sapXep." LIMIT ".$limit.", 10";
			}
		}else{
			if(isset($_POST["giaThap"])){
				$giaThap = $_POST["giaThap"];
				$giaCao = $_POST["giaCao"];
				$sql = "SElECT sp2.*, COUNT(DISTINCT yt.idYeuThich) AS soLuotThich FROM (SELECT sp1.*, COUNT(DISTINCT bl.idBinhLuan) AS soBinhLuan  
			FROM sanpham sp1 LEFT JOIN binhluan bl ON sp1.idSanPham = bl.idSanPham
			GROUP BY sp1.idSanPham) sp2 LEFT JOIN yeuthich yt ON sp2.idSanPham = yt.idSanPham
			GROUP BY sp2.idSanPham having (idLoaiSP = '$idLoaiSP' OR idLoaiSP 
			IN(SELECT idLoaiSP FROM loaisanpham lsp WHERE lsp.idLoaiSPCha = '$idLoaiSP' OR lsp.idLoaiSPCha 
			IN(SELECT idLoaiSP FROM loaisanpham lsp1 WHERE lsp1.idLoaiSPCha = '$idLoaiSP'))) AND (giaChuan BETWEEN '$giaThap' AND '$giaCao') ORDER BY ".$giaTri." ".$sapXep." LIMIT ".$limit.", 10";
			}else{
				$sql = "SElECT sp2.*, COUNT(DISTINCT yt.idYeuThich) AS soLuotThich FROM (SELECT sp1.*, COUNT(DISTINCT bl.idBinhLuan) AS soBinhLuan  
			FROM sanpham sp1 LEFT JOIN binhluan bl ON sp1.idSanPham = bl.idSanPham
			GROUP BY sp1.idSanPham) sp2 LEFT JOIN yeuthich yt ON sp2.idSanPham = yt.idSanPham
			GROUP BY sp2.idSanPham having (idLoaiSP = '$idLoaiSP' OR idLoaiSP 
			IN(SELECT idLoaiSP FROM loaisanpham lsp WHERE lsp.idLoaiSPCha = '$idLoaiSP' OR lsp.idLoaiSPCha 
			IN(SELECT idLoaiSP FROM loaisanpham lsp1 WHERE lsp1.idLoaiSPCha = '$idLoaiSP'))) ORDER BY ".$giaTri." ".$sapXep." LIMIT ".$limit.", 10";
			}
		}
		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();


		echo "{";
    	echo "\"danhsachsanpham\":";
		if($ketqua){
			while ($dong = mysqli_fetch_array($ketqua)) {

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

				if($idKhachHang != 0){
					$yeuthich =  "false";
					$sqlSanPhamYeuThich = "SELECT *  FROM yeuthich WHERE idKhachHang = '$idKhachHang' AND idSanPham = ".$dong["idSanPham"];
					$ketQuaYT = mysqli_query($conn, $sqlSanPhamYeuThich);
					if(mysqli_num_rows($ketQuaYT) > 0) 
						$yeuthich = "true";
					array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dong["soBinhLuan"], "soLuotThich" => $dong["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"], "yeuThich" => $yeuthich));
				}else{
					array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dong["soBinhLuan"], "soLuotThich" => $dong["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"]));
				}
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

// lấy danh sách loại sản phẩm từ loại cha lớn nhất đến loại con truyền vào
	function layDanhSachLoaiSanPham($conn, $idLoaiSPCon){
		$sql =  "SELECT * FROM loaisanpham lsp WHERE lsp.idLoaiSP = ".$idLoaiSPCon." OR lsp.idLoaiSP IN(
SELECT lsp1.idLoaiSPCha FROM loaisanpham lsp1 WHERE lsp1.idLoaiSP = ".$idLoaiSPCon." OR lsp1.idLoaiSP IN(
SELECT lsp2.idLoaiSPCha FROM loaisanpham lsp2 WHERE lsp2.idLoaiSP = ".$idLoaiSPCon."))";
	$ketQua = mysqli_query($conn, $sql);
	return $ketQua;
	}

//lấy danh sách tất cả sản phẩm
// 	function layDanhSachSanPham(){
// 		global $conn;

// 		$idKhachHang = 0;
// 		if (isset($_POST["idKhachHang"])) {
// 			$idKhachHang = $_POST["idKhachHang"];
// 		}

// 		if(isset($_POST["limit"]))
// 			$limit = $_POST["limit"];

// 		if(isset($_GET["limit"]))
// 			$limit = $_GET["limit"];

// 		$giaTri = "idSanPham";
// 		if(isset($_POST["giaTri"]))
// 			$giaTri = $_POST["giaTri"];

// 		$sapXep = "ASC";
// 		if (isset($_POST["sapXep"])) {
// 			$sapXep = $_POST["sapXep"];
// 		}

// 		$sql = "SElECT sp2.*, COUNT(DISTINCT yt.idYeuThich) AS soLuotThich FROM (SELECT sp1.*, COUNT(DISTINCT bl.idBinhLuan) AS soBinhLuan  
// FROM sanpham sp1 LEFT JOIN binhluan bl ON sp1.idSanPham = bl.idSanPham
// GROUP BY sp1.idSanPham) sp2 LEFT JOIN yeuthich yt ON sp2.idSanPham = yt.idSanPham
// GROUP BY sp2.idSanPham ORDER BY ".$giaTri." ".$sapXep." LIMIT ".$limit.", 10";
// 		$ketqua = mysqli_query($conn, $sql);
// 		$chuoijson = array();

// 		echo "{";
//     	echo "\"danhsachsanpham\":";
// 		if($ketqua){
// 			while ($dong = mysqli_fetch_array($ketqua)) {

// 				$chuoijsonloaisp = array();
// 				$ketQua = layDanhSachLoaiSanPham($conn, $dong["idLoaiSP"]);
// 				if ($ketQua) {
// 					while ($dongLoaiSP  = mysqli_fetch_array($ketQua)) {
// 						array_push($chuoijsonloaisp, array("idLoaiSP" => $dongLoaiSP["idLoaiSP"], "tenLoaiSP" => $dongLoaiSP["tenLoaiSP"], "idLoaiSPCha" => $dongLoaiSP["idLoaiSPCha"]));
// 					}
// 				}

// 				$chuoijsonkhachhang = array();
// 				$ketQuaKH = layThongTinCoBanNguoiBan($conn, $dong["idNguoiBan"]);
// 				if ($ketQuaKH) {
// 					while ($dongKH = mysqli_fetch_array($ketQuaKH)) {
// 						array_push($chuoijsonkhachhang, array("idKhachHang" => $dongKH["idKhachHang"], "tenKhachHang" => $dongKH["tenKhachHang"], "anhInfoKH" => $dongKH["anhInfoKH"], "diemTinCay" => $dongKH["diemTinCay"], "soSanPham" => $dongKH["soSanPham"]));
// 					}
// 				}

// 				$sqlNoiBan = "SELECT diaChi FROM diachikhachhang WHERE idKhachHang = ".$dong["idNguoiBan"]." AND macDinh = 1";
// 				$ketQuaDC = mysqli_query($conn, $sqlNoiBan);
// 				$dongNoiBan = mysqli_fetch_array($ketQuaDC);

// 				if($idKhachHang != 0){
// 					$yeuthich =  "false";
// 					$sqlSanPhamYeuThich = "SELECT *  FROM yeuthich WHERE idKhachHang = '$idKhachHang' AND idSanPham = ".$dong["idSanPham"];
// 					$ketQuaYT = mysqli_query($conn, $sqlSanPhamYeuThich);
// 					if(mysqli_num_rows($ketQuaYT) > 0) 
// 						$yeuthich = "true";
// 					array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dong["soBinhLuan"], "soLuotThich" => $dong["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"], "yeuThich" => $yeuthich));
// 				}else{
// 					array_push($chuoijson, array("idSanPham" => $dong["idSanPham"], "thongTinNguoiBan" => $chuoijsonkhachhang, "tenSanPham" => $dong["tenSanPham"], "giaChuan" => $dong["giaChuan"], "moTa" => $dong["moTa"], "hinhLon" => "http://".$_SERVER["SERVER_NAME"].":8080/webmoki".$dong["hinhLon"], "hinhNho" => $dong["hinhNho"], "soBinhLuan" => $dong["soBinhLuan"], "soLuotThich" => $dong["soLuotThich"], "khoiLuong" => $dong["khoiLuong"], "kichThuoc" => $dong["kichThuoc"], "trangThai" => $dong["trangThai"], "loaiSP" => $chuoijsonloaisp, "noiBan" => $dongNoiBan["diaChi"]));
// 				}
// 			}
			
// 		}

// 		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
// 		echo "}";
// 	}

	function layDanhSachBinhLuan(){
		global $conn;
		if(isset($_POST["idSanPham"]))
			$idSanPham = $_POST["idSanPham"];

		$sql = "SELECT bl.*, kh.anhInfoKH, kh.tenKhachHang FROM khachhang kh, binhluan bl WHERE kh.idKhachHang = bl.idKhachHang AND bl.idSanPham = '$idSanPham' ORDER BY bl.idBinhLuan DESC";
		$ketqua = mysqli_query($conn, $sql);
		$chuoijson = array();

		echo "{";
    	echo "\"binhluan\":";
		if ($ketqua) {
			while ($dong = mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("idBinhLuan" => $dong["idBinhLuan"], "idKhachHang" => $dong["idKhachHang"], "tenKhachHang" => $dong["tenKhachHang"], "anhInfoKH" => $dong["anhInfoKH"], "idSanPham" => $dong["idSanPham"], "noiDung" => $dong["noiDung"], "thoiGian" => $dong["thoiGian"]));
			}
		}
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

?>