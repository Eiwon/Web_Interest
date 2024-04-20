<%@page import="web.chat.resource.RoomVO"%>
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
	<%
		RoomVO vo = (RoomVO)request.getAttribute("roomVO");
	%>
	<div>
		<div>
			<span>방 번호 : <%=vo.getRoomId() %></span>
		</div>
		<div>
			<h2><%=vo.getRoomName() %>  채팅방</h2>
		</div>
	</div>
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
		let userId = '<%=session.getAttribute("userId")%>';
		let roomId = '<%=vo.getRoomId()%>';
		
		let webSocket = new WebSocket("ws://localhost:8080/WebChat/chatSpace/chatServer");
		
		webSocket.onmessage = function(e){
			let msg = JSON.parse(e.data);
			console.log("receive message : " + e.data);
			output.append(msg.writer + " : " + msg.content + "<br>");
		};
		
		webSocket.onopen = function(e){
			console.log("webSocket open");
			sendMsg("connection");
		};
		
		webSocket.onclose = function(e){
			console.log("webSocket close : " + e);
		};
		
		webSocket.onerror = function(e){
			console.log("webSocket error : " + e);
		};
		
		input.keypress(function(e){
			if(e.keyCode == 13){
				sendMsg(input.val());
			}
		});
		
		function sendMsg(content){
			let msg = {
					"roomId" : roomId,
					"writerId" : userId,
					"content" : content
				};
				console.log("msg = " + msg.writer + " : " + msg.content);
				
				webSocket.send(JSON.stringify(msg));
				input.val("");
		} // end sendMsg
			
	</script>
</body>
</html>