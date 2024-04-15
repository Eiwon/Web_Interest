package web.chat.resource;

import java.util.ArrayList;

public interface RoomDAO {
	
	int insertRoom(RoomVO room);
	
	ArrayList<RoomVO> selectRoom();
	
	int updateRoomName(String roomName);
	
	int deleteRoom(int roomId);
}
