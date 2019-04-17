<?php
include('connection.php');
$employee_id= $_POST['employee_id'];
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



$sql="UPDATE `employee` SET  `employee_pass` = '$employee_pass', `employee_name` = N'$employee_name', `employee_image` = '$employee_image', `employee_phone` = '$employee_phone', `employee_address` = N'$employee_address', `employee_email` = '$employee_email', `employee_dateofbirth` = '$employee_dateofbirth', `employee_position_id` = '$employee_position_id', `employee_salary` = '$employee_salary', `employee_timestart` = '$employee_timestart', `employee_timefinish` = '$employee_timefinish' WHERE `employee`.`employee_id` = $employee_id";
	if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    
    $connect->close();
?>