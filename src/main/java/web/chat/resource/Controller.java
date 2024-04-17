package web.chat.resource;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	
	public void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
