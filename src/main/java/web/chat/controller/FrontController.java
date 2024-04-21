package web.chat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.chat.resource.Controller;
import web.chat.resource.RequestProperty;
import web.chat.resource.Router;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static HashMap<String, Controller> ctrMap = null;
	
    public FrontController() {
    	ctrMap = new HashMap<>();
    	ctrMap.put("login", LoginController.getInstance());
    	ctrMap.put("room", RoomController.getInstance());
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestProperty rp = new RequestProperty(request);
		System.out.println("호출 controller : " + rp.getController());
		System.out.println("호출 action : " + rp.getAction());
		System.out.println("호출 method : " + rp.getMethod());
		
		Controller ctr = ctrMap.get(rp.getController());
		
		Map<String, Object> attr = new HashMap<>();
		Router router = ctr.action(rp, attr);
		router.route(request, response, attr);

	} // end service

	
}
