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

@WebServlet({"/common/signUp", "/common/signIn", "/common/idDupChk"})
public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SIGN_URL = "/WebChat/common/";
	private static final String SIGN_UP = "signUp";
	private static final String SIGN_IN = "signIn";
	private static final String CHK_DUP = "idDupChk";
	private static final String EXT = ".jsp";
	
	private MemberDAO memberDao = null;
       
    public SignServlet() {
    	memberDao = MemberDAOImple.getInstance();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String requestMethod = request.getMethod();
    	System.out.println("SignServlet service() " + requestURI + ", " + requestMethod);
    	
    	if(requestURI.contains(SIGN_UP)) {
    		if(requestMethod.equals("GET")) {
    			// 회원가입 페이지로 이동
    			response.sendRedirect(SIGN_UP + EXT);
    		}else if(requestMethod.equals("POST")) {
    			registerMember(request, response);
    		} // end signUp post
    	}else if(requestURI.contains(SIGN_IN)) {
    		if(requestMethod.equals("GET")) {
    			//로그인 페이지로 이동
    			response.sendRedirect(SIGN_UP + EXT);
    		}else if(requestMethod.equals("POST")) {
    			signIn(request, response);
    		} // end signIn post
    	}else if(requestURI.contains(CHK_DUP)) {
    		idDupChk(request, response);
    	}
    	
    } // end service
    
    private void idDupChk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String userId = request.getParameter("userId");
    	String checkedId = memberDao.haveUserId(userId);
		response.getWriter().write(checkedId);
	}

	private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String userId = request.getParameter("userId");
    	String pw = request.getParameter("pw");
    	String checked = memberDao.checkUserId(userId, pw);
		
		if(checked != null) {
			// 로그인 성공
			HttpSession sess = request.getSession();
			sess.setAttribute("userId", userId);
			sess.setMaxInactiveInterval(600);
			response.sendRedirect("../chatSpace/room");
		}else {
			// 로그인 실패
			response.sendRedirect(SIGN_IN + EXT);
		}
	}

	private void registerMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	System.out.println("registerMember()");
    	String userId = request.getParameter("userId");
    	String pw = request.getParameter("pw");
    	
    	// 회원 등록
		MemberVO member = new MemberVO(userId, pw, request.getParameter("name"), 
				request.getParameter("email"), request.getParameter("phone"));
		int res = memberDao.insertMember(member);
		if(res == 1) {
			response.sendRedirect(SIGN_IN + EXT);
		}else {
			response.sendRedirect(SIGN_UP + EXT);
		}
    }
}
