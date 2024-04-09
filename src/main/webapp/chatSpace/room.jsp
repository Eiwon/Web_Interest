<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Room</title>
</head>
<body>
	<div id="output" style="width: 300px; height: 500px;">
	</div>
	<div>
		<input type="text" id="input">
	</div>
	
	
	<script type="text/javascript">
		let output = $('#output');
		let input = $('#input');
		input.keypress(function(e){
			console.log(e.keyCode);
			if(e.keyCode == 13){
				let content = input.val();
				$.ajax({
					type : "post",
					url : "interpreter.do",
					data : {
						"content" : content
					},
					success : function(result){
						
					}
				});
				
				input.val("");
			}
		});
			
	
	
	</script>
	
</body>
</html>