<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$my_month=$_POST['month'];
	$my_year=$_POST['year'];
	$query="SELECT SUM(`menu`.`menu_price` * `bill_detail`.`count`) as total_money FROM `bill_detail`, `menu` WHERE `bill_detail`.`menu_id`=`menu`.`menu_id` AND MONTH(`bill_detail`.`bill_detail_create_date`)=$my_month and YEAR(`bill_detail`.`bill_detail_create_date`)=$my_year";
	$data=mysqli_query($connect,$query);
	


	class Employee{
	function Employee($total_money){
		$this->total_money=$total_money;
	}
}
$mangEmployee=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangEmployee, new Employee($row['total_money']));
	}

//chuyển định dạng về json
echo json_encode($mangEmployee);
?>

