<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style type="text/css">
.room{
	border: 1px solid black;
}
</style>
<title>Lobby</title>
</head>
<body>
	<h2>Lobby</h2>
	<div id="roomList" style="height: 500px;"></div>
	<input type="button" value="채팅방 생성" onclick="createRoom()">
	
	<script type="text/javascript">
		let roomList = $('#roomList');
		
		$(document).ready(function(){
			$.ajax({
				type : "GET",
				url : "selectAll.do",
				data : {},
				success : function(result){
					refreshRoomList(result);	
				}
				
			}); // end ajax
			
		}); // end document.ready
		
		function refreshRoomList(result){
			let rooms = JSON.parse(result).roomList;
			let roomsBlock = "";
			for(x in rooms){
				roomsBlock += '<div class="room"><div> ID : ' + 
				'<span class="roomId">' + rooms[x].roomId + '</span></div>' +
				'<div>' + rooms[x].roomName + '</div>' +
				'<div>owner : ' + '<span class="memberId">' + rooms[x].creatorId + '</span></div>' +
				'<div>' + rooms[x].createdAt + '</div></div>';
			}
			roomList.html(roomsBlock);
			$('.room').click(function(){
				let roomId = $(this).find('.roomId').text();
				console.log(roomId);
				
				enterRoom(roomId);
				
			}); // end room click
			
		} // end refreshRoomList
	
		function scanRoomName(){
			let roomName = prompt("방 이름 입력");
			
			if(roomName != null){
				return roomName;
			}else return null;
		} // end scanRoomName
		
		function createRoom(){
			
			let roomName = scanRoomName();
			
			if(roomName == null)
				return;
			
			$.ajax({
				type : "POST",
				url : "createRoom.do",
				data : {
					"roomName" : roomName,
					"creatorId" : '<%=session.getAttribute("userId")%>'
				},
				success : function(result){
					let roomId = JSON.parse(result).roomId;
					enterRoom(roomId);
				}
			}); // end ajax
			
		} // end createRoom
		
		function enterRoom(roomId){
			location.href = 'room.do?roomId=' + roomId;
		} // end enterRoom
		
	</script>
	
</body>
</html>