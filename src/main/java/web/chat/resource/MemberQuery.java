package web.chat.resource;

public interface MemberQuery {
	
	public static final String TABLE_MEMBER = "MEMBER";
	
	public static final String MEMBERID = "MEMBER_ID";
	public static final String PW = "PW";
	public static final String NAME = "NAME";
	public static final String EMAIL = "EMAIL";
	public static final String PHONE = "PHONE";
	
	// 아이디 중복 체크
	public static final String SELECT_HAVE_USERID
		= "SELECT " + MEMBERID + " FROM " + TABLE_MEMBER 
		+ " WHERE " + MEMBERID + " = ?";
	
	// 멤버 등록
	public static final String INSERT_MEMBER
		= "INSERT INTO " + TABLE_MEMBER + " VALUES (?, ?, ?, ?, ?)";

	// 패스워드 확인
	public static final String SELECT_CHECK_USERID
		= "SELECT " + MEMBERID + " FROM " + TABLE_MEMBER
		+ " WHERE " + MEMBERID + " = ? AND " + PW + " = ?";
	
	// 회원 정보 변경
	public static final String UPDATE_MEMBER
		= "UPDATE " + TABLE_MEMBER + " SET " + PW + " = ?, " + NAME
		+ " = ?, " + EMAIL + " = ?, " + PHONE + " = ? WHERE "
		+ MEMBERID + " = ?";
	
	// 회원 탈퇴
	public static final String DELETE_MEMBER
		= "DELETE " + TABLE_MEMBER + " WHERE " + MEMBERID + " = ?";
	
}
