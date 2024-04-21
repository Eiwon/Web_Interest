package web.chat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import web.chat.resource.Controller;
import web.chat.resource.MemberDAO;
import web.chat.resource.MemberDAOImple;
import web.chat.resource.MemberVO;
import web.chat.resource.RequestProperty;
import web.chat.resource.Router;

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
	public Router action(RequestProperty rp, Map<String, Object> attr) {
		String path = null;
		String action = rp.getAction();
		String method = rp.getMethod();
		Map<String, Object> paraMap = rp.getParaMap();
		HttpSession session = rp.getSession();
		
		if(action.equals(SIGN_UP)) {
    		if(method.equals("GET")) {
    			System.out.println("get");
    			path = SIGN_URL + SIGN_UP + EXT;
    			return new Router(path, "forward");
    		}else if(method.equals("POST")) {
    			System.out.println("post");
    			return registerMember(paraMap);
    		}
    	}else if(action.equals(SIGN_IN)) {
    		if(method.equals("GET")) {
    			path = SIGN_URL + SIGN_IN + EXT;
    			return new Router(path, "forward");
    		}else if(method.equals("POST")) {
    			return signIn(paraMap, session);
    		}
    	}else if(action.equals(CHK_DUP)) {
    		return idDupChk(paraMap, attr);
    	}
		return null;
	}
	private Router idDupChk(Map<String, Object> paraMap, Map<String, Object> attr) {
    	String userId = (String)paraMap.get("userId");
    	String checkedId = memberDao.haveUserId(userId);
    	attr.put("checkedId", checkedId);
    	return new Router(null, "write");
	} // end idDupChk

	private Router signIn(Map<String, Object> paraMap, HttpSession session) {
    	String userId = (String)paraMap.get("userId");
    	String pw = (String)paraMap.get("pw");
    	String checked = memberDao.checkUserId(userId, pw);
		String path;
		
		if(checked != null) {
			// 로그인 성공
			session.setAttribute("userId", userId);
			session.setMaxInactiveInterval(600);
			path = "../room/lobby.do";
			return new Router(path, "send");
		}else {
			// 로그인 실패
			path = SIGN_URL + SIGN_IN + EXT;
			return new Router(path, "forward");
		}
	} // end signIn

	private Router registerMember(Map<String, Object> paraMap) {
    	System.out.println("registerMember()");
    	String path = null;
    	// 회원 등록
		MemberVO member = new MemberVO(
				(String)paraMap.get("userId"), 
				(String)paraMap.get("pw"), 
				(String)paraMap.get("name"), 
				(String)paraMap.get("email"), 
				(String)paraMap.get("phone"));
		int res = memberDao.insertMember(member);
		
		path = (res == 1) ? (SIGN_URL + SIGN_IN + EXT) : (SIGN_URL + SIGN_UP + EXT);
		return new Router(path, "forward");
    } // end registerMember
}
