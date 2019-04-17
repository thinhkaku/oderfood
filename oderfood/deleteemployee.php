<?php
    include('connection.php');
    $id=$_POST['employee_id'];
    $sql="delete from `employee` where `employee_id` =$id";
    if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    $connect->close();
?>