<?php
include('connection.php');
$bill_number_tb= $_POST['bill_number_tb'];
$bill_number_people= $_POST['bill_number_people'];
$employee_id= $_POST['employee_id'];
$bill_create_date= $_POST['bill_create_date'];



			$sql="INSERT INTO `bill` (`bill_id`, `bill_number_tb`, `bill_number_people`, `employee_id`, `bill_create_date`) VALUES (NULL, '$bill_number_tb', '$bill_number_people', '$employee_id', '$bill_create_date')";
			if ($connect->query($sql) === TRUE) {
    $last_id = $connect->insert_id;
    echo $last_id;
} else {
    echo "0";
}

?>