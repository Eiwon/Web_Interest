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
	<div>
		<div id="output" style="width: 300px; height: 500px; border-color: black;">
		</div>
		<div>
			<input type="text" id="input">
		</div>
	</div>
	
	<script type="text/javascript">
		let output = $('#output');
		let input = $('#input');
		
		let webSocket = new WebSocket("ws://localhost:8080/WebChat/chatSpace/chatServer");
		
		webSocket.onmessage = function(e){
			console.log("receive message : " + e.data);
			output.append(e.data + "<br>");
		};
		
		webSocket.onopen = function(e){
			console.log("webSocket open");
		};
		
		webSocket.onclose = function(e){
			console.log("webSocket close");
		};
		
		webSocket.onerror = function(e){
			console.log("webSocket error");
		};
		
		
		input.keypress(function(e){
			console.log(e.keyCode);
			if(e.keyCode == 13){
				let content = input.val();
				console.log("key input : " + content);
				webSocket.send(content);
				input.val("");
			}
		});
			
	
	
	</script>
</body>
</html>