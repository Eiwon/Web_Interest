package web.chat.resource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImple implements RoomDAO, RoomQuery{

	private static RoomDAOImple instance = null;
	
	private RoomDAOImple() {};
	
	public static RoomDAOImple getInstance() {
		if(instance == null) {
			instance = new RoomDAOImple();
		}
		return instance;
	} // end getInstance

	@Override
	public int insertRoom(RoomVO room) {
		System.out.println("insertRoom()");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(INSERT_ROOM);
			pstmt.setString(1, room.getRoomName());
			pstmt.setString(2, room.getCreatorId());
			
			result = pstmt.executeUpdate();
			
			System.out.println(result + "행 추가 성공");
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConManager.close(pstmt);
		}

		return result;
	} // end insertRoom

	@Override
	public ArrayList<RoomVO> selectRoom() {
		System.out.println("selectRoom()");
		ArrayList<RoomVO> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(SELECT_ROOM);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result.add(new RoomVO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4)));
			}
			
			System.out.println(result.size() + "행 검색 성공");
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConManager.close(pstmt);
		}
		
		return result;
	} // end selectRoom

	@Override
	public int updateRoomName(String roomName) {
		System.out.println("updateRoom()");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(UPDATE_ROOM);
			pstmt.setString(1, roomName);
			
			result = pstmt.executeUpdate();
			
			System.out.println(result + "행 수정 성공");
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConManager.close(pstmt);
		}

		return result;
	}

	@Override
	public int deleteRoom(int roomId) {
		System.out.println("deleteRoom()");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(DELETE_ROOM);
			pstmt.setInt(1, roomId);
			
			result = pstmt.executeUpdate();
			
			System.out.println(result + "행 삭제 성공");
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			ConManager.close(pstmt);
		}

		return result;
	}
	
	
	
}
