<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SignUp</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form action="Sign.do" method="POST">
		<div>아이디
			<input type="text" name="userId" id="userId" required>
			<button onclick="checkDup()">중복 체크</button>
		</div>
		<div>비밀번호
			<input type="password" name="pw" id="pw" required>
		</div>
		<div>이름
			<input type="text" name="name" id="name" required>
		</div>
		<div>이메일
			<input type="text" name="email" id="email" required>
		</div>
		<div>전화번호
			<input type="text" name="phone" id="phone" required>
		</div>
		
		<input type="submit" name="type" value="join">
	</form>

	<script type="text/javascript">
		let userId = document.getElementById("userId");
		
		function checkDup(){
			let xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					let jsonRes = JSON.parse(this.responseText);
					console.log(jsonRes);
					if(jsonRes.userId == 'null'){
						alert("사용 가능한 아이디입니다.");
					}else{
						alert("사용할 수 없는 아이디입니다.");
					}
				}
			};
			
			xhttp.open("POST", "Sign.do", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("type=checkDup&userId=" + userId.value);
		}
	
	</script>
</body>
</html>