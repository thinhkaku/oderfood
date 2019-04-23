<?php
include('connection.php');
$number= $_POST['number'];
$status= $_POST['status'];



$sql="UPDATE `mytable` SET  `status` = '$status' where `number`=$number";
	if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    
    $connect->close();
?>