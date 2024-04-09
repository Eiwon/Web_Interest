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
			<input type="text" name="userId" id="userId" pattern="[a-zA-Z][a-zA-Z0-9]{3,20}" title="���̵�� 4~19��" required>
			<button onclick="checkDup()">�ߺ� üũ</button>
		</div>
		<div>��й�ȣ
			<input type="password" name="pw" id="pw" required>
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
						alert("����� �� �ִ� ���̵��Դϴ�.");
					}else{
						alert("����� �� ���� ���̵��Դϴ�.");
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