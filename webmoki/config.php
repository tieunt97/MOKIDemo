<?php 
	
	//khai bao hang so
	define("DBSERVER", "localhost");
	define("DBUSERNAME", "root");
	define("DBPASSWORD", "1234");
	define("DBNAME", "moki");

	//ket noi csdl
	$conn = mysqli_connect(DBSERVER, DBUSERNAME, DBPASSWORD, DBNAME);
	if (!$conn) { //kiem tra ket noi thanh cong
		die('Connect error: '.mysqli_connect_errno());
	};

?>