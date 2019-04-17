<?php
include('connection.php');
$menu_id= $_POST['menu_id'];
$numberTable= $_POST['numberTable'];
$count= $_POST['count'];
$create_at= $_POST['create_at'];


			$sql="INSERT INTO `myitem` (`id`, `menu_id`, `numberTable`, `count`,`create_at`) VALUES (NULL, '$menu_id', '$numberTable', '$count','$create_at')";
	$resultt= $connect->query($sql);
	echo $resultt;

?>