<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SignIn</title>
</head>
<body>
	<!-- 로그인 화면 -->
	<h1>	로그인	</h1>
	<form action="Sign.do" method="POST">
		<input type="text" name="userId" required>
		<input type="password" name="pw" required>
		<input type="submit" name="type" value="로그인">
	</form>
	<a href="Sign.do?type=signUp">회원가입</a>
	
</body>
</html>