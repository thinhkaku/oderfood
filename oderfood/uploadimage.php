<?php
	include('connection.php');
	mysqli_set_charset($connect,'utf8');
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST') {
		$imageName = $_POST['imageName'];	
		
		$imageCode = $_POST['imageCode'];	
		
		// Tạo một thư mục chứa ảnh
		// imaName là tên ảnh, để không trùng các bạn có thể add thêm ngày tháng cho nó
		$path = "forderimage/$imageName";
		// Đường dẫn
		$actualpath = "http://dev.androidcoban.com/blog/$path";
		 	
		$query = "SELECT * FROM `employee`";	
		 if(mysqli_query($connect,$query)){
			// đẩy data vào path
			file_put_contents($path,base64_decode($imageCode));
			$response["message"] = "Success";
			}
			 mysqli_close($connect);
	}
	else{
		$response["message"] = "Failed";
		}
		// echoing JSON response
     echo json_encode($response);
?>