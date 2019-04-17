<?php
include('connection.php');
$id= $_POST['id'];
$count= $_POST['count'];
$status= $_POST['status'];



$sql="UPDATE `myitem` SET  `count` = '$count', `status`='$status' WHERE `myitem`.`id` = $id";
	if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    
    $connect->close();
?>