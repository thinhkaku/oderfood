<?php
    include('connection.php');
    $menu_id=$_POST['menu_id'];
    $sql="delete from `menu` where `menu_id` =$menu_id";
    if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    $connect->close();
?>