<?php
include "_dbConnect.php";
$email = $_POST['email'];
$pass =$_POST['pass'];
$dienthoai = $_POST['dienthoai'];
$username = $_POST['username'];
// check Data
	$query = "select * from  user where email = '$email'";
	// $query = "select * from  user where email = 'khanh@gmail.com'";
	$data = mysqli_query($connect,$query);
	$numrow = mysqli_num_rows($data);
	if($numrow>0){
		$arr =[
				'succes' =>false,
				'message'=> "email đã tồn tại"	
		];
	}else{
		// insert
	$query = "insert into user  values (null,'$email','$username',N'$pass','$dienthoai')";
	// $query = "insert into user  values (null,'khanh@gmail.com','1',N'khanh','123')";
	$data = mysqli_query($connect,$query);
		if($data==true){
			$arr = [
				'succes' =>true,
				'message'=> "thanh cong"
			];
		}else {
			$arr = [
				'succes' =>false,
				'message'=> "khong thanh cong"
			];
		}
	}
print_r(json_encode($arr));
?>