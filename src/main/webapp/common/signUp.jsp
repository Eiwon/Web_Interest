<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SignUp</title>
</head>
<body>
	<h1>ȸ�� ����</h1>
	<form action="Sign.do" method="POST">
		<div>���̵�
			<input type="text" name="userId" id="userId" required>
		</div>
		<div>��й�ȣ
			<input type="password" name="pw" id="pw" required>
		</div>
		<div>��й�ȣ Ȯ��
			<input type="password" name="pwChk" id="pwChk" required> 
		</div>
		<div>�̸�
			<input type="text" name="name" id="name" required>
		</div>
		<div>�̸���
			<input type="text" name="email" id="email" required>
		</div>
		<div>��ȭ��ȣ
			<input type="text" name="phone" id="phone" required>
		</div>
		
		<input type="submit" name="type" value="ȸ������">
	</form>

</body>
</html>