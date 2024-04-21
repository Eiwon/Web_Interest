package web.chat.resource;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class Router {

	private String path;
	private String type;
	
	public Router(String path, String type) {
		this.path = path;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void route(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> attr) {
		if(type.equals("forward")) {
			forward(request, response, attr);
		}else if(type.equals("send")) {
			send(response);
		}else if(type.equals("write")) {
			write(response, attr);
		}
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> attr) {
		if (attr != null) {
			Iterator<String> keySet = attr.keySet().iterator();

			while (keySet.hasNext()) {
				String key = keySet.next();
				request.setAttribute(key, attr.get(key));
			}
		}
		
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end forward
	
	private void send(HttpServletResponse response) {
		try {
			response.sendRedirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end send
	
	private void write(HttpServletResponse response, Map<String, Object> attr) {
		JSONObject jsonObj = new JSONObject();
		if (attr != null) {
			Iterator<String> keySet = attr.keySet().iterator();
			while (keySet.hasNext()) {
				String key = keySet.next();
				jsonObj.put(key, attr.get(key));
			}
		}
		System.out.println(jsonObj.toJSONString());
		try {
			response.getWriter().write(jsonObj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end write
}
