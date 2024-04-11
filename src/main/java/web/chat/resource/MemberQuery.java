package web.chat.resource;

public interface MemberQuery {
	
	public static final String TABLE_MEMBER = "MEMBER";
	
	public static final String COL_USERID = "USERID";
	public static final String COL_PW = "PW";
	public static final String COL_NAME = "NAME";
	public static final String COL_EMAIL = "EMAIL";
	public static final String COL_PHONE = "PHONE";
	
	// 아이디 중복 체크
	public static final String SELECT_HAVE_USERID
		= "SELECT " + COL_USERID + " FROM " + TABLE_MEMBER 
		+ " WHERE " + COL_USERID + " = ?";
	
	// 멤버 등록
	public static final String INSERT_MEMBER
		= "INSERT INTO " + TABLE_MEMBER + " VALUES (?, ?, ?, ?, ?)";

	// 패스워드 확인
	public static final String SELECT_CHECK_USERID
		= "SELECT " + COL_USERID + " FROM " + TABLE_MEMBER
		+ " WHERE " + COL_USERID + " = ? AND " + COL_PW + " = ?";
	
	// 회원 정보 변경
	public static final String UPDATE_MEMBER
		= "UPDATE " + TABLE_MEMBER + " SET " + COL_PW + " = ?, " + COL_NAME
		+ " = ?, " + COL_EMAIL + " = ?, " + COL_PHONE + " = ? WHERE "
		+ COL_USERID + " = ?";
	
	// 회원 탈퇴
	public static final String DELETE_MEMBER
		= "DELETE " + TABLE_MEMBER + " WHERE " + COL_USERID + " = ?";
	
}
