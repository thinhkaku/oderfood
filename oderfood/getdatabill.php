<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM bill";
	$data=mysqli_query($connect,$query);
	


	class Employee{
	function Employee($bill_id,$bill_number_tb, $bill_number_people, $employee_id, $bill_create_date){
		$this->bill_id=$bill_id;
		$this->bill_number_tb=$bill_number_tb;
		$this->bill_number_people=$bill_number_people;
		$this->employee_id=$employee_id;
		$this->bill_create_date=$bill_create_date;
	
		

	}
}
$mangEmployee=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangEmployee, new Employee($row['bill_id'],$row['bill_number_tb'], $row['bill_number_people'], $row['employee_id'], $row['bill_create_date']));
	}

//chuyển định dạng về json
echo json_encode($mangEmployee);
?>