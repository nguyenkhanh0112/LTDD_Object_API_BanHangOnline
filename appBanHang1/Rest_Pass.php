<?php
include "_dbConnect.php";
$email = $_POST['email'];
$pass = $_POST['pass'];

//check xem mail co trong csdl hay chua
$query = "select * from  user where email = '$email'";
$data = mysqli_query($connect,$query);
$numrow = mysqli_num_rows($data);
if($numrow>0){
	$query = "update user set pass = '$pass' where email = '$email'";
	$data = mysqli_query($connect,$query);
	if($data == true){
		$arr = [
				'succes' =>true,
				'message'=> "cap nhap thanh cong"
		];
	}else{
		$arr = [
			'succes' =>false,
			'message'=> "cap nhap khong thanh cong"
		];
	}
}else{
	$arr =[
			'succes' =>false,
			'message'=> "email đã tồn tại"	
		];
	
}
print_r(json_encode($arr));

?>