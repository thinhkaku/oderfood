<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$my_month=$_POST['month'];
	$my_year=$_POST['year'];
	$query="SELECT SUM(`bill`.`bill_number_people`) as total_people from `bill` WHERE MONTH(`bill`.`bill_create_date`)=$my_month AND YEAR(`bill`.`bill_create_date`)=$my_year";
	$data=mysqli_query($connect,$query);
	


	class Employee{
	function Employee($total_people){
		$this->total_people=$total_people;
	}
}
$mangEmployee=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangEmployee, new Employee($row['total_people']));
	}

//chuyển định dạng về json
echo json_encode($mangEmployee);
?>