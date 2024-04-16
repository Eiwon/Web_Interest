package web.chat.controller;

import java.io.IOException;
import java.util.HashMap;

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
		
		PathAnalyzer analyzer = PathAnalyzer.getInstance();
		analyzer.analyze(requestURI, requestMethod);
		System.out.println(analyzer.getController());
		System.out.println(analyzer.getAction());
		
		
		request.getRequestDispatcher("/WEB-INF/common/signIn.jsp").forward(request, response);
		
		
		
		
		
		
	}

	
}
