<?php
	include "_dbConnect.php";
	$id = $_POST['id'];

	$query = "delete from sanpham where id = '$id'";

	$data = mysqli_query($connect,$query);
	if($data==true){
		$arr = [
			'succes' => true,
			'message' => "thanh cong"
		];
	}else{
			$arr = [
			'succes' => false,
			'message' => " xoa khong thanh cong"
		];
	}
	print_r(json_encode($arr));

?>