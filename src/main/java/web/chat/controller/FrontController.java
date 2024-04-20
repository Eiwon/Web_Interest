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
		String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();
		System.out.println(requestMethod);
		PathAnalyzer analyzer = PathAnalyzer.getInstance();
		analyzer.analyze(requestURI, requestMethod);
		System.out.println(analyzer.getController());
		System.out.println(analyzer.getAction());
		System.out.println(analyzer.getMethod());
		
		//Map<String, String[]> map = new HashMap<>(request.getParameterMap());
		
		Controller ctr = ctrMap.get(analyzer.getController());
		System.out.println(analyzer.getController() + " 컨트롤러 호출");
		
		Map<String, Object> attr = new HashMap<>();
		Router router = ctr.action(analyzer.getAction(), request, response, attr);
		router.route(request, response, attr);

		
	}

	
}
