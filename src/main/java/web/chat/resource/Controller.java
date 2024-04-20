package web.chat.resource;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public Router action(String action, HttpServletRequest request, 
			HttpServletResponse response, Map<String, Object> attr) 
					throws ServletException, IOException;
}
