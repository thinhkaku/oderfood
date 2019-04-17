<?php
	$connect=mysqli_connect("localhost","root","","orderfood");
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT * FROM finance";
	$data=mysqli_query($connect,$query);
	


	class Finance{
	function Finance($finance_id, $finance_note, $finance_order_id,$finance_pay,$finance_status,$finance_date){
		$this->MaTaiChinh=$finance_id;
		$this->GhiChu=$finance_note;
		$this->IDDonHang=$finance_order_id;
		$this->ChiTra=$finance_pay;
		$this->TrangThai=$finance_status;
		$this->NgayTao=$finance_date;
		
		

	}
}
$mangFinance=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangFinance, new Finance($row['finance_id'], $row['finance_note'], $row['finance_order_id'], $row['finance_pay'], $row['finance_status'], $row['finance_date']));
	}

//chuyển định dạng về json
echo json_encode($mangFinance);

?>