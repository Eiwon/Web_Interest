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
	<form action="Sign.do" method="POST">
		<p>ID</p>
		<input type="text" name="userId" required>
		<p>��й�ȣ</p>
		<input type="password" name="pw" required>
		<input type="submit" name="type" value="logIn">
	</form>
	<a href="Sign.do?type=signUp">ȸ������</a>
	
</body>
</html>