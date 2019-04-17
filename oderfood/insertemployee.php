<?php
include('connection.php');
$employee_username= $_POST['employee_username'];
$employee_pass= $_POST['employee_pass'];
$employee_name= $_POST['employee_name'];
$employee_image= $_POST['employee_image'];
$employee_phone= $_POST['employee_phone'];
$employee_address= $_POST['employee_address'];
$employee_email= $_POST['employee_email'];
$employee_dateofbirth= $_POST['employee_dateofbirth'];
$employee_position_id= $_POST['employee_position_id'];
$employee_salary= $_POST['employee_salary'];
$employee_timestart= $_POST['employee_timestart'];
$employee_timefinish= $_POST['employee_timefinish'];

$sql = "select * from employee";
	$query = $connect -> query($sql);
	$arr = array();
	while ($row = mysqli_fetch_array($query)) {
		$arr[] = $row;
	}
	$result = 0;
	foreach ($arr as $key => $value) {
			if ($employee_username == $value['employee_username']) {
				$result = 1;
			}
		}
	if ($result == 1) {
		echo "2";
	}
	else{
			$sql="INSERT INTO `employee` (`employee_id`, `employee_username`, `employee_pass`, `employee_name`, `employee_image`, `employee_phone`, `employee_address`, `employee_email`, `employee_dateofbirth`, `employee_position_id`, `employee_salary`, `employee_timestart`, `employee_timefinish`) VALUES (NULL, '$employee_username', '$employee_pass', N'$employee_name', '$employee_image', '$employee_phone', N'$employee_address', '$employee_email', '$employee_dateofbirth', '$employee_position_id', '$employee_salary', '$employee_timestart', '$employee_timefinish')";
	$resultt= $connect->query($sql);
	echo $resultt;
	}



?>