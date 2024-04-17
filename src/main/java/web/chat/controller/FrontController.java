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
		
		ctr.action(analyzer.getAction(), request, response);
		
//		System.out.println("return path : " + result.get("return")[0] + ", return type : " + result.get("return")[1]);
//		if(result.get("return")[1].equals("send")) {
//			request.getRequestDispatcher(result.get("return")[0]).forward(request, response);;
//		}else if(result.get("return")[1].equals("foward")){
//			request.getRequestDispatcher(result.get("return")[0]).forward(request, response);
//		}
		
		
		
		
		
	}

	
}
