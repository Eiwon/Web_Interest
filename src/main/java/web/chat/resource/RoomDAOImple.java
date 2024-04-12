package web.chat.resource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomDAOImple implements RoomDAO, RoomQuery{

	@Override
	public int insertRoom(RoomVO vo) {
		System.out.println("insertRoom()");
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(INSERT_ROOM);
			pstmt.setString(1, vo.getRoomName());
			pstmt.setString(2, vo.getCreatorId());
			
			res = pstmt.executeUpdate();
			System.out.println(res + "행 추가 성공");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConManager.close(pstmt);
		}
		
		return res;
	}

	@Override
	public List<RoomVO> selectAllRoom(int page) {
		System.out.println("selectAllRoom()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomVO> result = new ArrayList<>();
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(SELECT_ALL_ROOM);
			pstmt.setInt(1, (page-1) * 10 + 1);
			pstmt.setInt(2, page * 10);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result.add(new RoomVO(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getDate(4)));
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConManager.close(pstmt, rs);
		}
		
		return result;
	}

	@Override
	public int updateRoom(String roomName, int roomId) {
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(UPDATE_ROOM_NAME);
			pstmt.setString(1, roomName);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			ConManager.close(pstmt);
		}
		
		return res;
	}

	@Override
	public int deleteRoom(String roomId) {
		return 0;
	}
	
}
