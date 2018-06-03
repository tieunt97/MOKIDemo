<?php 
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		require "config.php";
		$ham = $_POST["ham"];
		switch ($ham) {
			case 'dangNhap':
				$ham();
				break;
			case 'dangKy':
				$ham();
				break;
			case 'xacNhanDangKy':
				$ham();
				break;
		}
	}

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		require "config.php";
		$ham = $_GET["ham"];
		switch ($ham) {
			case 'dangNhap':
				$ham();
				break;
			case 'dangKy':
				$ham();
				break;
			case 'xacNhanDangKy':
				$ham();
				break;
		}
	}

	function dangNhap(){
		global $conn;
		if(isset($_POST["soDT"]))
			$soDT = $_POST["soDT"];
		if(isset($_POST["matKhau"]))
			$matKhau = $_POST["matKhau"];
		if(isset($_GET["soDT"]))
			$soDT = $_GET["soDT"];
		if(isset($_GET["matKhau"]))
			$matKhau = $_GET["matKhau"];
		
		$truyVan = "SELECT soDT, matKhau, trangThai, ngayKichHoat FROM khachhang WHERE soDT like '$soDT' && matKhau like '$matKhau'";
		$result = mysqli_query($conn, $truyVan);

		$jsontaikhoan = array();

		echo "{";
		echo "\"taikhoan\":";

		if(mysqli_num_rows($result) > 0){
			$rows = mysqli_fetch_array($result);
			array_push($jsontaikhoan, array("soDT" => $rows["soDT"], "matKhau" => $rows["matKhau"], "trangThai" => $rows["trangThai"], "ngayKichHoat" => $rows["ngayKichHoat"]));
		}else{
			array_push($jsontaikhoan, array("soDT" => "null", "matKhau" => "null", "trangThai" => "null", "ngayKichHoat" => "null"));
		}

		echo json_encode($jsontaikhoan, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function dangKy(){
		global $conn;
		if(isset($_POST["soDT"]))
			$soDT = $_POST["soDT"];
		if(isset($_POST["matKhau"]))
			$matKhau = $_POST["matKhau"];
		if(isset($_GET["soDT"]))
			$soDT = $_GET["soDT"];
		if(isset($_GET["matKhau"]))
			$matKhau = $_GET["matKhau"];

		$truyvantaikhoan = "SELECT idKhachHang, soDT, trangThai FROM khachhang WHERE soDT LIKE '$soDT'";
		$ketqua = mysqli_query($conn, $truyvantaikhoan);
		echo "{";
		echo "\"maxacnhan\":";
		$jsonxacnhan = array();
		if(mysqli_num_rows($ketqua) > 0){
			$rows = mysqli_fetch_array($ketqua);
			$trangThai = $rows["trangThai"];
			if($trangThai == 1)
				array_push($jsonxacnhan, array("maxacnhan" => -1));
			else if ($trangThai == -1) {
				$maXacNhan = rand(1000, 3000);
				$truyvancapnhat = "UPDATE khachhang SET matKhau = '$matKhau', maKichHoat = '$maXacNhan' WHERE idKhachHang = ".$rows["idKhachHang"];
				$ketquacapnhat = mysqli_query($conn, $truyvancapnhat);
				if ($ketquacapnhat) {
					array_push($jsonxacnhan, array("maxacnhan" => $maXacNhan));
				}
			}
		}else{
			$maXacNhan = rand(1000, 3000);
			$sqlthemtaikhoan = "INSERT INTO khachhang(soDT, matKhau, trangThai, maKichHoat) VALUES('$soDT', '$matKhau', -1, '$maXacNhan')";
			$ketquathemtaikhoan = mysqli_query($conn, $sqlthemtaikhoan);
			if ($ketquathemtaikhoan) {
				array_push($jsonxacnhan, array("maxacnhan" => $maXacNhan));
			}
		}

		echo json_encode($jsonxacnhan, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function xacNhanDangKy(){
		global $conn;
		if(isset($_POST["soDT"]))
			$soDT = $_POST["soDT"];
		if(isset($_GET["soDT"]))
			$soDT = $_GET["soDT"];

		$sql = "UPDATE khachhang SET trangThai = 1 WHERE soDT = '$soDT'";
		$ketqua = mysqli_query($conn, $sql);
		$jsonxacnhan = array();

		echo "{";
		echo "\"xacNhan\":";
		if($ketqua){
			array_push($jsonxacnhan, array("xacNhan" => 1));
		}
		echo json_encode($jsonxacnhan, JSON_UNESCAPED_UNICODE);
		echo "}";
	}
?>