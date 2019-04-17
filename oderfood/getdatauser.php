<?php
	$connect=mysqli_connect("localhost","root","","orderfood");
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM user";
	$data=mysqli_query($connect,$query);
	


	class user{
	function user($user_id, $user_username, $user_password,$user_name,$user_address,$user_phone,$user_email,$user_image,$user_title,$user_role,$user_order_count,$user_date_creat){
		$this->IdNguoiDung=$user_id;
		$this->TaiKhoanNguoiDung=$user_username;
		$this->MatKhauNguoiDung=$user_password;
		$this->TenNguoiDung=$user_name;
		$this->DiaChi=$user_address;
		$this->SoDienThoai=$user_phone;
		$this->DiaChiEmail=$user_email;
		$this->AnhDaiDien=$user_image;
		$this->GhiChu=$user_title;
		$this->ChucVu=$user_role;
		$this->SoLanDatHang=$user_order_count;
		$this->NgayTao=$user_date_creat;
		

	}
}
$manguser=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($manguser, new user($row['user_id'], $row['user_username'], $row['user_password'], $row['user_name'], $row['user_address'], $row['user_phone'], $row['user_email'], $row['user_image'], $row['user_title'], $row['user_role'], $row['user_order_count'], $row['user_date_creat']));
	}

//chuyển định dạng về json
echo json_encode($manguser);

?>