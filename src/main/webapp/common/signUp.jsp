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
		</div>
		<div>비밀번호
			<input type="password" name="pw" id="pw" required>
		</div>
		<div>비밀번호 확인
			<input type="password" name="pwChk" id="pwChk" required> 
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
		
		<input type="submit" name="type" value="회원가입">
	</form>

</body>
</html>