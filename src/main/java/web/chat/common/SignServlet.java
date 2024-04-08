package web.chat.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.chat.resource.MemberDAO;
import web.chat.resource.MemberDAOImple;

@WebServlet("/common/Sign.do")
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDao = null;
       
    public SignServlet() {
    	memberDao = MemberDAOImple.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("SignServlet doGet() request type : " + type);
		
		if(type.equals("signUp")) {
			// 회원가입 페이지로 이동
			response.sendRedirect("signUp.jsp");
		}
		
	} // end doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("SignServlet doPost() request type : " + type);
		
		if(type.equals("로그인")) {
			String userId = request.getParameter("userId");
			String pw = request.getParameter("pw");
			
			String checked = memberDao.checkUserId(userId, pw);
			
			if(checked != null) {
				// 로그인 성공
			}else {
				// 로그인 실패
			}
			
		}else if(type.equals("회원가입")) {
			
		}
	
	}

}
