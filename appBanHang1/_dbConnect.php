<?php 
$host = "localhost";
$user = "root";
$password = "";
$database = "banhangonline";

$connect = mysqli_connect($host,$user,$password,$database);
mysqli_set_charset($connect,"utf8");
?>