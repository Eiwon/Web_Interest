package web.chat.resource;

public interface MemberQuery {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String CON_ID = "SCOTT";
	public static final String CON_PW = "TIGER";
	
	public static final String TABLE_MEMBER = "MEMBER";
	
	public static final String COL_USERID = "USERID";
	public static final String COL_PW = "PW";
	public static final String COL_NAME = "NAME";
	public static final String COL_EMAIL = "EMAIL";
	public static final String COL_PHONE = "PHONE";
	
	// 
	
}
