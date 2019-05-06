<?php
    include('connection.php');
    $type_id=$_POST['type_id'];
    $sql="delete from `type` where `type_id` =$type_id";
    if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    $connect->close();
?>