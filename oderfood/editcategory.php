<?php
include('connection.php');
$type_id= $_POST['type_id'];
$type_name= $_POST['type_name'];




$sql="UPDATE `type` SET  `type_name` =N'$type_name' WHERE `type`.`type_id` = $type_id";
	if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    
    $connect->close();
?>