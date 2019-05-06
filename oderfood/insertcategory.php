<?php
include('connection.php');

$type_name= $_POST['type_name'];





			$sql="INSERT INTO `type` (`type_id`, `type_name`) VALUES (NULL, N'$type_name')";
			if ($connect->query($sql) === TRUE) {
    $last_id = $connect->insert_id;
    echo $last_id;
} else {
    echo "0";
}

?>