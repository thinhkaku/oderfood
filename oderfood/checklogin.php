<?php
include("connection.php");
	$userName = $_GET['employee_username'];
	$password = $_GET['employee_pass'];

	
	// if ($userName != "" && $password != "") {
	// 	$sql = "INSERT INTO dangnhap`(userName`, password) VALUES ('$userName','$password')";
	// 	$result = $conn->query($sql);
	// 	echo $result;
	// }

	$sql = "select * from employee";
	$query = $connect -> query($sql);
	$arr = array();
	while ($row = mysqli_fetch_array($query)) {
		$arr[] = $row;
	}
	$result = 0;
	foreach ($arr as $key => $value) {
			if ($userName == $value['employee_username'] && $password == $value['employee_pass']) {
				$result = 1;
			}
		}
	if ($result == 1) {
		echo "1";
	}
	else{
		echo "0";
	}
?>