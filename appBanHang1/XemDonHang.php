<?php
	include "_dbConnect.php";

	 $iduser = $_POST['iduser'];
	//$iduser = '10';

	$query = "select * from donhang where iduser = '$iduser' order by id desc ";

	$data = mysqli_query($connect,$query);

	$result = array();

	while($row = mysqli_fetch_assoc($data)){
		$truyvan = "select * from chitietdonhang inner join sanpham on chitietdonhang.idsp = sanpham.id where chitietdonhang.iddonhang = '$row[id]'";
		$data1 = mysqli_query($connect,$truyvan);
		$item =array();
		while($row1 = mysqli_fetch_assoc($data1)){
			$item[] = $row1;
		}
		$row['item'] = $item;
		$result[] = ($row);

	}
	if(!empty($result)){
		$arr = [
			'succes' =>true,
			'message'=> "thanh cong",
			'result' => $result
		];
	}else{
		$arr = [
			'succes' =>false,
			'message'=> "khong thanh cong",
			'result' => $result
		];
	}
	print_r(json_encode($arr));
?>