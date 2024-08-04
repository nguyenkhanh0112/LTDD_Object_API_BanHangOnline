<?php
	include "_dbConnect.php";
	$iduser = $_POST['iduser'];
	$diachi =$_POST['diachi'];
	$sdt = $_POST['sdt'];
	$email = $_POST['email'];
	$soluong = $_POST['soluong'];
	$tongtien = $_POST['tongtien'];
	$chitiet = $_POST['chitiet'];
	$thoigian = $_POST['thoigian'];

	$query = "insert into donhang values(null,'$iduser',N'$diachi','$sdt','$email','$soluong','$tongtien','$thoigian')";

	$data = mysqli_query($connect,$query);

if($data ==true){
	$query = "select id AS iddonhang from donhang where iduser = '$iduser' order by id desc limit 1";
	$data = mysqli_query($connect,$query);

	while($row = mysqli_fetch_assoc($data)){
		$iddonhang = ($row);
	}
	if(!empty($iddonhang)){
		$chitiet = json_decode($chitiet,true);
		foreach ($chitiet as $key => $value){
			$query = "insert into chitietdonhang values('{$iddonhang["iddonhang"]}','{$value["idsp"]}','{$value["soluong"]}','{$value["giasp"]}','{$value["tensp"]}')";
			$data = mysqli_query($connect,$query);
		}
		if($data==true){
			$arr=[
				'succes' => true,
				'message'=>"thanh cong"	
			];
		}else{
				$arr=[
				'succes' => false,
				'message'=>"khong thanh cong"
			];
		}
		print_r(json_encode($arr));
	}else{
			$arr=[
				'succes' => false,
				'message'=>"khong thanh cong"
			];
		print_r(json_encode($arr));
	}
}
?>