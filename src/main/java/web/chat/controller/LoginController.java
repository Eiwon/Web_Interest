package web.chat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.chat.resource.Controller;
import web.chat.resource.MemberDAO;
import web.chat.resource.MemberDAOImple;
import web.chat.resource.MemberVO;

public class LoginController implements Controller{

	private static final String SIGN_URL = "/WEB-INF/common/";
	private static final String SIGN_UP = "signUp";
	private static final String SIGN_IN = "signIn";
	private static final String CHK_DUP = "idDupChk";
	private static final String EXT = ".jsp";
	
	private static LoginController instance = null;
	private MemberDAO memberDao = null;
	
	private LoginController() {
		memberDao = MemberDAOImple.getInstance();
	}
	
	public static LoginController getInstance() {
		if(instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	
	@Override
	public void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("action : " + action);
		String path = null;
		String method = request.getMethod();
		
		if(action.equals(SIGN_UP)) {
    		if(method.equals("GET")) {
    			// 회원가입 페이지로 이동
    			System.out.println("get");
    			path = SIGN_URL + SIGN_UP + EXT;
    			request.getRequestDispatcher(path).forward(request, response);
    			
    		}else if(method.equals("POST")) {
    			System.out.println("post");
    			registerMember(request, response);
    		} // end signUp post
    	}else if(action.equals(SIGN_IN)) {
    		if(method.equals("GET")) {
    			//로그인 페이지로 이동
    			path = SIGN_URL + SIGN_IN + EXT;
    			request.getRequestDispatcher(path).forward(request, response);
    		}else if(method.equals("POST")) {
    			signIn(request,response);
    		} // end signIn post
    	}else if(action.equals(CHK_DUP)) {
    		idDupChk(request, response);
    	}
	}
	private void idDupChk(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String userId = request.getParameter("userId");
    	String checkedId = memberDao.haveUserId(userId);
    	
		response.getWriter().write(checkedId);
	} // end idDupChk

	private void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String userId = request.getParameter("userId");
    	String pw = request.getParameter("pw");
    	String checked = memberDao.checkUserId(userId, pw);
		String path;
		
		if(checked != null) {
			// 로그인 성공
			HttpSession sess = request.getSession();
			sess.setAttribute("userId", userId);
			sess.setMaxInactiveInterval(600);
			path = "../room/lobby.do";
			response.sendRedirect(path);
		}else {
			// 로그인 실패
			path = SIGN_URL + SIGN_IN + EXT;
			request.getRequestDispatcher(path).forward(request, response);
		}
	} // end signIn

	private void registerMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	System.out.println("registerMember()");
    	String userId = request.getParameter("userId");
    	String pw = request.getParameter("pw");
    	String name = request.getParameter("name");
    	String email = request.getParameter("email");
    	String phone = request.getParameter("phone");
    	String path = null;
    	// 회원 등록
		MemberVO member = new MemberVO(userId, pw, name, email, phone);
		int res = memberDao.insertMember(member);
		
		if(res == 1) {
			path = SIGN_URL + SIGN_IN + EXT;
		}else {
			path = SIGN_URL + SIGN_UP + EXT;
		}
		request.getRequestDispatcher(path).forward(request, response);
    } // end registerMember
}
