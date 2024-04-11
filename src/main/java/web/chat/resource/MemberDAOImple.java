package web.chat.resource;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class MemberDAOImple implements MemberDAO, MemberQuery{

	private static MemberDAOImple instance = null;
	
	private MemberDAOImple() {}
	
	public static MemberDAOImple getInstance() {
		if(instance == null)
			instance = new MemberDAOImple();
		return instance;
	}

	@Override
	public String haveUserId(String userId) {
		System.out.println("MemberDAOImple haveUserId()");
		String result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(SELECT_HAVE_USERID);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				result = rs.getString(1);
			System.out.println("MemberDAOImple haveUserId result : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConManager.close(pstmt, rs);
		}
		
		return result;
	}

	@Override
	public int insertMember(MemberVO member) {
		System.out.println("MemberDAOImple insertMember()");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(INSERT_MEMBER);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			
			result = pstmt.executeUpdate();
			
			System.out.println("MemberDAOImple insertMember result : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConManager.close(pstmt);
		}
		
		return result;
	}

	@Override
	public String checkUserId(String userId, String pw) {
		System.out.println("MemberDAOImple checkUserId()");
		String result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = ConManager.getConnection().prepareStatement(SELECT_CHECK_USERID);
			pstmt.setString(1, userId);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				result = rs.getString(1);
			System.out.println("MemberDAOImple checkUserId result : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConManager.close(pstmt, rs);
		}
		
		return result;
	}

	@Override
	public int updateMember(MemberVO member) {
		System.out.println("MemberDAOImple updateMember()");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(UPDATE_MEMBER);
			
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getUserId());
			
			result = pstmt.executeUpdate();
			
			System.out.println("MemberDAOImple updateMember result : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConManager.close(pstmt);
		}
		
		return result;
	}

	@Override
	public int deleteMember(String userId) {
		System.out.println("MemberDAOImple deleteMember()");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = ConManager.getConnection().prepareStatement(DELETE_MEMBER);

			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
			System.out.println("MemberDAOImple deleteMember result : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConManager.close(pstmt);
		}
		
		return result;
	}
	
}
