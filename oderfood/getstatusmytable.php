<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT  `number`, `status` FROM mytable where `status`=1";
	$data=mysqli_query($connect,$query);
	


	class Employee{
	function Employee($number, $status){
		$this->number=$number;
		$this->status=$status;
	}
}
$mangEmployee=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangEmployee, new Employee($row['number'], $row['status']));
	}

//chuyển định dạng về json
echo json_encode($mangEmployee);
?>