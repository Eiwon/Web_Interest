package web.chat.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.chat.resource.MemberDAO;
import web.chat.resource.MemberDAOImple;
import web.chat.resource.MemberVO;
import web.chat.resource.MemberVO;

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
		
		String userId = request.getParameter("userId");
		String pw = request.getParameter("pw");
		
		if(type.equals("logIn")) {
			
			String checked = memberDao.checkUserId(userId, pw);
			
			if(checked != null) {
				// 로그인 성공
				HttpSession sess = request.getSession();
				sess.setAttribute("userId", userId);
				sess.setMaxInactiveInterval(600);
				response.sendRedirect("../chatSpace/lobby.jsp");
				
			}else {
				// 로그인 실패
				response.sendRedirect("signIn.jsp");
			}
			
		}else if(type.equals("join")) {
			MemberVO member = new MemberVO(userId, pw, request.getParameter("name"), 
					request.getParameter("email"), request.getParameter("phone"));
			int res = memberDao.insertMember(member);
			if(res == 1) {
				response.sendRedirect("signIn.jsp");
			}else {
				response.sendRedirect("signUp.jsp");
			}
			
		}else if(type.equals("checkDup")) {
			String checkedId = memberDao.haveUserId(userId);
			response.getWriter().write("{\"userId\":\"" + checkedId + "\"}");
			
		}
	
	}

}
