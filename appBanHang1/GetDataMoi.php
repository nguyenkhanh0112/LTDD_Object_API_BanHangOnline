<?php 
include "_dbConnect.php";
$query = "SELECT * from sanpham order by id desc";
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