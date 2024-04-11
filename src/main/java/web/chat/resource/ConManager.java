package web.chat.resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ConManager {
	
	public static Connection getConnection() throws Exception{
		Connection conn = null;
		
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:comp/env");
		DataSource ds = (DataSource) envContext.lookup("dbcp/orcl");
		conn = ds.getConnection();
		System.out.println("연결 성공");
		
		return conn;
	}

	public static void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
