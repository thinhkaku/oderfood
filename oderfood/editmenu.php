<?php
include('connection.php');
$menu_id= $_POST['menu_id'];
$menu_name= $_POST['menu_name'];
$menu_image= $_POST['menu_image'];
$menu_describle= $_POST['menu_describle'];
$menu_type_id= $_POST['menu_type_id'];
$menu_price= $_POST['menu_price'];




$sql="UPDATE `menu` SET  `menu_name` =N'$menu_name' ,`menu_image` ='$menu_image' ,`menu_describle` =N'$menu_describle' ,`menu_type_id` ='$menu_type_id' ,`menu_price` ='$menu_price' WHERE `menu`.`menu_id` = $menu_id";
	if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    
    $connect->close();
?>