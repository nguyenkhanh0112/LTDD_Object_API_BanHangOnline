<?php
	include "_dbConnect.php";
	$id = $_POST['id'];
	$tensanpham = $_POST['tensanpham'];
	$giasanpham = $_POST['giasanpham'];
	$motasanpham = $_POST['motasanpham'];
	$hinhanhsanpham = $_POST['hinhanhsanpham'];
	$idloaisanpham = $_POST['idloaisanpham'];

	$query = "update sanpham set tensanpham = '$tensanpham',giasanpham = '$giasanpham',motasanpham = '$motasanpham',hinhanhsanpham = '$hinhanhsanpham', idloaisanpham = '$idloaisanpham' where id = '$id'";

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