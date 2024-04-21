<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="EUC-KR">
<title>SignUp</title>
</head>
<body>
	<h1>ȸ�� ����</h1>
	<form action="signUp.do" method="POST">
		<div>���̵�
			<input type="text" name="userId" id="userId" required>
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
		
		<input type="submit">
	</form>

	<script type="text/javascript">
		let userId = $('#userId');
		
		function checkDup(){
			console.log("checkDup() : " + userId.val());
			$.ajax({
				type : "POST",
				url : "idDupChk.do",
				data : {
					"userId" : userId.val()
				},
				success : function(result){
					console.log(result);
					let res = JSON.parse(result);
					if(res.checkedId == null){
						alert("��� ������ ���̵��Դϴ�.");
					}else{
						alert("����� �� ���� ���̵��Դϴ�.");
					}
				}
			});
		} // end checkDup
	
	</script>
</body>
</html>