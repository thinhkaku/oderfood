<?php
	$connect=mysqli_connect("localhost","root","","orderfood");
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM myorder";
	$data=mysqli_query($connect,$query);
	


	class MyOrder{
	function MyOrder($order_id, $order_user_id, $order_menu_id,$order_number,$order_datetime,$order_status){
		$this->IDMon=$order_id;
		$this->IDKhachHang=$order_user_id;
		$this->IDMenu=$order_menu_id;
		$this->SoLuongMon=$order_number;
		$this->ThoiGian=$order_datetime;
		$this->TrangThai=$order_status;
		
		
		

	}
}
$mangMyOrder=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangMyOrder, new MyOrder($row['order_id'], $row['order_user_id'], $row['order_menu_id'], $row['order_number'], $row['order_datetime'], $row['order_status']));
	}

//chuyển định dạng về json
echo json_encode($mangMyOrder);

?>