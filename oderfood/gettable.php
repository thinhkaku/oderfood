<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM mytable";
	$data=mysqli_query($connect,$query);
	


	class MyTable{
	function MyTable($number, $numpeople, $check_tb,$note,$time_check){
		$this->number=$number;
		$this->numPeople=$numpeople;
		$this->check=$check_tb;
		$this->note=$note;
		$this->timeCheck=$time_check;
	}
}
$mangTable=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangTable, new MyTable($row['number'], $row['numpeople'], $row['check_tb'], $row['note'], $row['time_check']));
	}

//chuyển định dạng về json
echo json_encode($mangTable);
?>