<?php
	$connect=mysqli_connect("localhost","root","","orderfood");
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM position";
	$data=mysqli_query($connect,$query);
	


	class position{
	function position($position_id, $position_name){
		$this->IdChucVu=$position_id;
		$this->TenChucVu=$position_name;
	}
}
$mangposition=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangposition, new position($row['position_id'], $row['position_name']));
	}

//chuyển định dạng về json
echo json_encode($mangposition);

?>