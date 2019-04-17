<?php
include('connection.php');
$bill_id= $_POST['bill_id'];
$menu_id= $_POST['menu_id'];
$count= $_POST['count'];
$bill_detail_create_date= $_POST['bill_detail_create_date'];



			$sql="INSERT INTO `bill_detail` (`bill_detail_id`, `bill_id`, `menu_id`, `count`, `bill_detail_create_date`) VALUES (NULL, '$bill_id', '$menu_id', '$count', '$bill_detail_create_date')";
	$resultt= $connect->query($sql);
	echo $resultt;

?>