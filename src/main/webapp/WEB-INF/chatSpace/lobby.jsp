<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Lobby</title>
</head>
<body>
	<h2>Lobby</h2>
	<div id="roomList" style="height: 500px;"></div>
	<input type="button" value="채팅방 생성" onclick="scanRoomName()">
	
	<script type="text/javascript">
		let roomList = $('#roomList');
		
		$(document).ready(function(){
			$.ajax({
				type : "GET",
				url : "room",
				data : {
					"type" ; "selectAll"
				},
				success : function(result){
					refreshRoomList(result);	
				}
				
			}); // end ajax
			
		}); // end document.ready
		
		function refreshRoomList(result){
			let rooms = JSON.parse(result);
			roomList.html("");
			
			for(x in rooms){
				let roomBlock = $('<div></div>');
				let idBlock = $('<div></div>').text('ID : ' + rooms[x].roomId);
				let nameBlock = $('<div></div>').text(rooms[x].roomName);
				let creatorBlock = $('<div></div>').text('owner : ' + rooms[x].creatorId);
				let createdBlock = $('<div></div>').text(rooms[x].createdAt);
				roomBlock.append(idBlock);
				roomBlock.append(nameBlock);
				roomBlock.append(creatorBlock);
				roomBlock.append(createdBlock);
				roomList.append(roomBlock);
			}
		} // end refreshRoomList
	
		function scanRoomName(){
			let roomName = confirm("방 이름 입력");
			
			if(roomName != null){
				createRoom(roomName);
			}
		} // end scanRoomName
		
		function createRoom(roomName){
			
			$.ajax({
				type : "POST",
				url : "room",
				data : {
					"type" : "insert",
					"roomName" : roomName,
					"creatorId" : '<%=session.getAttribute("userId")%>'
				},
				success : function(result){
					let roomId = result;
					location.href = 'room?roomId=' + roomId;
				}
			}); // end ajax
			
		} // end createRoom
		
	</script>
	
</body>
</html>