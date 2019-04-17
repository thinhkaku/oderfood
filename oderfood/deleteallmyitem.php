<?php
    include('connection.php');
    $id=$_POST['id'];
    $sql="delete from `myitem` where `numberTable` =$id";
    if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    $connect->close();
?>