<?php
include('connection.php');

$menu_name= $_POST['menu_name'];
$menu_image= $_POST['menu_image'];
$menu_describle= $_POST['menu_describle'];
$menu_type_id= $_POST['menu_type_id'];
$menu_price	= $_POST['menu_price'];




			$sql="INSERT INTO `menu` (`menu_id`,`menu_name`, `menu_image`, `menu_describle`,`menu_type_id`,`menu_price`) VALUES (NULL, N'$menu_name',
			 '$menu_image','$menu_describle','$menu_type_id','$menu_price')";
			if ($connect->query($sql) === TRUE) {
    $last_id = $connect->insert_id;
    echo $last_id;
} else {
    echo "0";
}

?>