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
	<form action="signIn.do" method="POST">
		<p>ID</p>
		<input type="text" name="userId" required>
		<p>비밀번호</p>
		<input type="password" name="pw" required>
		<input type="submit">
	</form>
	<a href="signUp.do">회원가입</a>
	
</body>
</html>