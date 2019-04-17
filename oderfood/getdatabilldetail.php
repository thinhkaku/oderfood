<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT `bill_detail`.`bill_detail_id`, `menu`.`menu_name`, `menu`.`menu_image`, `menu`.`menu_price`, `bill_detail`.`count`, `bill_detail`.`bill_detail_create_date` FROM `bill_detail`, `menu` WHERE `bill_detail`.`menu_id`=`menu`.`menu_id`";
	$data=mysqli_query($connect,$query);
	


	class Employee{
	function Employee($bill_detail_id,$menu_name, $menu_image, $menu_price, $count, $bill_detail_create_date){
		$this->bill_detail_id=$bill_detail_id;
		$this->menu_name=$menu_name;
		$this->menu_image=$menu_image;
		$this->menu_price=$menu_price;
		$this->count=$count;
		$this->bill_detail_create_date=$bill_detail_create_date;
	
		

	}
}
$mangEmployee=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangEmployee, new Employee($row['bill_detail_id'],$row['menu_name'], $row['menu_image'], $row['menu_price'],$row['count'], $row['bill_detail_create_date']));
	}

//chuyển định dạng về json
echo json_encode($mangEmployee);
?>