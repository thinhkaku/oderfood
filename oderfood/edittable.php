<?php
include('connection.php');
$number= $_POST['number'];
$numpeople= $_POST['numpeople'];
$check_tb= $_POST['check_tb'];
$note= $_POST['note'];
$time= $_POST['time_check'];



$sql="UPDATE `mytable` SET  `numpeople` = '$numpeople', `check_tb`='$check_tb', `note`='$note', `time_check`='$time' WHERE `mytable`.`number` = $number";
	if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    
    $connect->close();
?>