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
	<h1>회원 가입</h1>
	<form action="signUp.do" method="POST">
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
						alert("사용 가능한 아이디입니다.");
					}else{
						alert("사용할 수 없는 아이디입니다.");
					}
				}
			});
		} // end checkDup
	
	</script>
</body>
</html>