<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SignIn</title>
</head>
<body>
	<!-- �α��� ȭ�� -->
	<h1>	�α���	</h1>
	<form action="signIn.do" method="POST">
		<p>ID</p>
		<input type="text" name="userId" required>
		<p>��й�ȣ</p>
		<input type="password" name="pw" required>
		<input type="submit">
	</form>
	<a href="signUp.do">ȸ������</a>
	
</body>
</html>