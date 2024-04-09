package web.chat.resource;

public interface MemberDAO {
	
	String haveUserId(String userId);
	
	int insertMember(MemberVO member);
	
	String checkUserId(String userId, String pw);
	
	int updateMember(MemberVO member);
	
	int deleteMember(String userId);
	
}
