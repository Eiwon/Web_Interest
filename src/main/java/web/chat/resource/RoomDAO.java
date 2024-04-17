package web.chat.resource;

import java.util.List;

public interface RoomDAO {
	
	public int insertRoom(RoomVO vo);
	
	public List<RoomVO> selectAllRoom(int page);
	
	public int updateRoomName(String roomName, int roomId);
	
	public int deleteRoom(int roomId);
}
