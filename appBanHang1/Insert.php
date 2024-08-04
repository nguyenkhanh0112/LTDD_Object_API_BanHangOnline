<?php
	include "_dbConnect.php";
	$tensanpham = $_POST['tensanpham'];
	$giasanpham = $_POST['giasanpham'];
	$motasanpham = $_POST['motasanpham'];
	$hinhanhsanpham = $_POST['hinhanhsanpham'];
	$idloaisanpham = $_POST['idloaisanpham'];

	$query = "insert into sanpham values (null,N'$tensanpham','$giasanpham','$hinhanhsanpham',N'$motasanpham','$idloaisanpham')";

	$data = mysqli_query($connect,$query);
	if($data==true){
		$arr = [
			'succes' => true,
			'message' => "thanh cong"
		];
	}else{
			$arr = [
			'succes' => false,
			'message' => "khong thanh cong"
		];
	}
	print_r(json_encode($arr));

?>