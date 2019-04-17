<?php
	include('connection.php');
	$userName = $_POST['employee_username'];
	$password = $_POST['employee_pass'];
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM employee WHERE employee_username='$userName' AND employee_pass='$password'";
	$data=mysqli_query($connect,$query);
	


	class Employee{
	function Employee($employee_id,$employee_username, $employee_pass, $employee_name, $employee_image,$employee_phone,$employee_address,$employee_email, $employee_dateofbirth, $employee_position_id, $employee_salary, $employee_timestart, $employee_timefinish){
		$this->MaNhanVien=$employee_id;
		$this->TaiKhoan=$employee_username;
		$this->Pass=$employee_pass;
		$this->TenNhanVien=$employee_name;
		$this->Anh=$employee_image;
		$this->SoDienThoai=$employee_phone;
		$this->DiaChi=$employee_address;
		$this->Email=$employee_email;
		$this->NgaySinh=$employee_dateofbirth;
		$this->ChucVu=$employee_position_id;
		$this->MucLuong=$employee_salary;
		$this->ThoiGianBatDau=$employee_timestart;
		$this->ThoiGianKetThuc=$employee_timefinish;
		

	}
}
$mangEmployee=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangEmployee, new Employee($row['employee_id'],$row['employee_username'], $row['employee_pass'], $row['employee_name'], $row['employee_image'], $row['employee_phone'], $row['employee_address'], $row['employee_email'],$row['employee_dateofbirth'], $row['employee_position_id'], $row['employee_salary'], $row['employee_timestart'], $row['employee_timefinish']));
	}

//chuyển định dạng về json
echo json_encode($mangEmployee);
?>