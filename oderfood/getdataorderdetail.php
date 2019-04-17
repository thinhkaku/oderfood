<?php
	$connect=mysqli_connect("localhost","root","","orderfood");
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM orderdetail";
	$data=mysqli_query($connect,$query);
	


	class orderdetail{
	function orderdetail($orderdetail_id, $orderdetail_numtable, $orderdetail_numpp,$orderdetail_datetime,$orderdetail_totalprice,$orderdetail_monname){
		$this->idhoadon=$orderdetail_id;
		$this->banso=$orderdetail_numtable;
		$this->songuoi=$orderdetail_numpp;
		$this->thoigian=$orderdetail_datetime;
		$this->tongtien=$orderdetail_totalprice;
		$this->tenmon=$orderdetail_monname;
		
		

	}
}
$mangorderdetail=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangorderdetail, new orderdetail($row['orderdetail_id'], $row['orderdetail_numtable'], $row['orderdetail_numpp'], $row['orderdetail_datetime'], $row['orderdetail_totalprice'], $row['orderdetail_monname']));
	}

//chuyển định dạng về json
echo json_encode($mangorderdetail);

?>