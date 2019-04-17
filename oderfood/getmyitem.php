<?php
	include('connection.php');
	$table=$_POST['number_table'];
	mysqli_query($connect, "SET NAMES 'utf8'");
	$query="SELECT `myitem`.`id`,`myitem`.`menu_id`,`myitem`.`numberTable`,`menu`.`menu_name`,`menu`.`menu_price`,`menu`.`menu_image`,`myitem`.`count`, `myitem`.`create_at`,`myitem`.`status` FROM `myitem`,`menu` WHERE `myitem`.`numberTable`=$table AND `myitem`.`menu_id`=`menu`.`menu_id`";
	$data=mysqli_query($connect,$query);
	


	class MyTable{
	function MyTable($id, $menu_id, $numberTable,$menu_name, $menu_price, $menu_image, $count,$create_at, $status){
		$this->id=$id;
		$this->menuId=$menu_id;
		$this->numberTable=$numberTable;
		$this->name=$menu_name;
		$this->price=$menu_price;
		$this->image=$menu_image;
		$this->count=$count;
		$this->create_at=$create_at;
		$this->status=$status;
	}
}
$mangTable=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangTable, new MyTable($row['id'], $row['menu_id'], $row['numberTable'], $row['menu_name'], $row['menu_price'], $row['menu_image'], $row['count'], $row['create_at'], $row['status']));
	}

//chuyển định dạng về json
echo json_encode($mangTable);
?>