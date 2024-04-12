package web.chat.resource;

public interface RoomQuery {
	
	public static final String TABLE_ROOM = "ROOM";
	
	public static final String ROOM_ID = "ROOD_ID";
	public static final String ROOM_NAME = "ROOM_NAME";
	public static final String CREATOR_ID = "CREATOR_ID";
	public static final String CREATED_AT = "CREATED_AT";
	
	// 방 생성
	public static final String INSERT_ROOM = "INSERT INTO " + TABLE_ROOM + 
			" VALUES(ROOM_SEQ.NEXTVAL, ?, ?, SYSDATE)";
	
	// 모든 방 조회
	public static final String SELECT_ALL_ROOM = "SELECT * FROM (SELECT " + TABLE_ROOM + 
			".*,  ROW_NUMBER() OVER (ORDER BY " + ROOM_ID + " DESC) AS RN FROM " + TABLE_ROOM + 
			") WHERE RN BETWEEN ? AND ?";
	
	// 방 이름 수정
	public static final String UPDATE_ROOM_NAME = "UPDATE " + TABLE_ROOM + " SET " + ROOM_NAME
			+ " = ? WHERE " + ROOM_ID + " = ?";
	
	// 방 삭제
	public static final String DELETE_ROOM = "DELETE " + TABLE_ROOM + " WHERE " + ROOM_ID 
			+ " = ?";
	
}
