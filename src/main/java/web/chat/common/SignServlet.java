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

@WebServlet({"/common/signUp.do", "/common/signIn.do", "/common/chkDup.do"})
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SIGN_URL = "/WebChat/common";
	private static final String SIGN_UP = "/signUp";
	private static final String SIGN_IN = "/signIn";
	private static final String CHK_DUP = "/chkDup";
	private static final String EXT = ".do";
	
	private MemberDAO memberDao = null;
       
    public SignServlet() {
    	memberDao = MemberDAOImple.getInstance();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestUri = request.getRequestURI();
    	String requestMethod = request.getMethod();
    	System.out.println("SignServlet service() " + requestUri + ", " + requestMethod);
    	
    	String userId = request.getParameter("userId");
    	String pw = request.getParameter("pw");
    	
    	if(requestUri.equals(SIGN_URL.concat(SIGN_UP).concat(EXT))) {
    		if(requestMethod.equals("GET")) {
    			// 회원가입 페이지로 이동
    			response.sendRedirect("signUp.jsp");
    		}else if(requestMethod.equals("POST")) {
    			// 회원 등록
    			MemberVO member = new MemberVO(userId, pw, request.getParameter("name"), 
    					request.getParameter("email"), request.getParameter("phone"));
    			int res = memberDao.insertMember(member);
    			if(res == 1) {
    				response.sendRedirect("signIn.jsp");
    			}else {
    				response.sendRedirect("signUp.jsp");
    			}
    		} // end signUp post
    	}else if(requestUri.equals(SIGN_URL.concat(SIGN_IN).concat(EXT))) {
    		if(requestMethod.equals("POST")) {
    			String checked = memberDao.checkUserId(userId, pw);
    			
    			if(checked != null) {
    				// 로그인 성공
    				HttpSession sess = request.getSession();
    				sess.setAttribute("userId", userId);
    				sess.setMaxInactiveInterval(600);
    				response.sendRedirect("../chatSpace/room.jsp");
    			}else {
    				// 로그인 실패
    				response.sendRedirect("signIn.jsp");
    			}
    		} // end signIn post
    	}else if(requestUri.equals(SIGN_URL.concat(CHK_DUP).concat(EXT))) {
    		String checkedId = memberDao.haveUserId(userId);
			response.getWriter().write("{\"userId\":\"" + checkedId + "\"}");
    	}
    	
    } // end service
}
