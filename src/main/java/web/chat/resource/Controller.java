package web.chat.resource;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public Router action(RequestProperty rp, Map<String, Object> attr) 
					throws ServletException, IOException;
}
