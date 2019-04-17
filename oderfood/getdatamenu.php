<?php
	include('connection.php');
	mysqli_query($connect, "SET NAMES 'utf8'");
	$id_type_menu=$_POST['id_type_menu'];
	$query="SELECT * FROM menu where menu_type_id=$id_type_menu";
	$data=mysqli_query($connect,$query);
	


	class Menu{
	function Menu($menu_id, $menu_name, $menu_image,$menu_describle,$menu_type_id,$menu_price,$menu_count){
		$this->id=$menu_id;
		$this->name=$menu_name;
		$this->image=$menu_image;
		$this->describle=$menu_describle;
		$this->group=$menu_type_id;
		$this->price=$menu_price;
		$this->count=$menu_count;
		
		

	}
}
$mangMenu=array();

//thêm phần tử và mảng
while ($row=mysqli_fetch_assoc($data)) {

		array_push($mangMenu, new Menu($row['menu_id'], $row['menu_name'], $row['menu_image'], $row['menu_describle'], $row['menu_type_id'], $row['menu_price'], $row['menu_count'] ));
	}

//chuyển định dạng về json
echo json_encode($mangMenu);

?>