<?php 
include "_dbConnect.php";
// $page = $_POST['page'];
// $total = 5;
// $pos = ($page - 1) * $total;

$loai = $_POST['idloaisanpham'];
// $query = "SELECT * FROM sanpham WHERE idloaisanpham = '$loai' LIMIT $pos, $total";
 $query = "SELECT * FROM sanpham WHERE idloaisanpham = '$loai'";


$data = mysqli_query($connect,$query);
$result =array(); 
while($row = mysqli_fetch_assoc($data)){ 
	$result[] = ($row);
}
if(!empty($result)){
	$arr = [
		'succes' => true,
		'message'=> "thanh cong",
		'result' => $result
	];
}else{
	$arr = [
		'succes' => false,
		'message'=> "khong thanh cong",
		'result' => $result	
	];
}
print_r(json_encode($arr));
?>