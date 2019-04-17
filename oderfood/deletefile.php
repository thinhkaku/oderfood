<?php
$name=$_POST['name_image'];
$path="forderimage/$name";
if (file_exists($path)) {
        unlink($path);
        echo "1";
    } else {
        echo "0";
    }
?>