<?php
	include "_dbConnect.php";
	$id = $_POST['id'];
	$email = $_POST['email'];
	$username = $_POST['username'];
	$pass = $_POST['pass'];
	$sodienthoai =$_POST['sodienthoai'];

	$query = "update user set email = '$email',username = '$username',pass = '$pass',sodienthoa = '$sodienthoai' where id = '$id'";

	$data = mysqli_query($connect,$query);
	if($data==true){
		$arr = [
			'succes' => true,
			'message' => "thanh cong"
		];
	}else{
			$arr = [
			'succes' => false,
			'message' => " sua khong thanh cong"
		];
	}
	print_r(json_encode($arr));
?>