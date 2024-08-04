<?php
include "_dbConnect.php";
$email = $_POST['email'];
$pass = $_POST['pass'];

 $query = "SELECT * FROM user WHERE email = '$email' and pass ='$pass' ";

$data = mysqli_query($connect,$query);
$result =array(); 
while($row = mysqli_fetch_assoc($data)){ 
	$result[] = ($row);
}
if(empty($result)){
	$arr = [
		'succes' => false,
		'message'=> "tai khoan cua ban chua dang ki, hay dang ki tai khoan",
		'result' => $result
	];
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
		'message'=> "tai khoan hoac mat khau khong dung",
		'result' => $result	
	];
}
print_r(json_encode($arr));
?>
