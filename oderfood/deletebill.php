<?php
    include('connection.php');
    $id_bill=$_POST['id_bill'];
    $sql="delete from `bill` where `bill_id` =$id_bill";
    if($connect->query($sql)===true)
    {
    	echo "1";
    }
    else{
    	echo "0";
    }
    $connect->close();
?>