<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM type";
	$data=mysqli_query($connect,$query);
	


	class Employee{
	function Employee($type_id,$type_name, $group_id){
		$this->type_id=$type_id;
		$this->type_name=$type_name;
		$this->group_id=$group_id;
	}
}
$mangEmployee=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangEmployee, new Employee($row['type_id'],$row['type_name'], $row['group_id']));
	}

//chuyển định dạng về json
echo json_encode($mangEmployee);
?>