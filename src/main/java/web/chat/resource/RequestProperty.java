package web.chat.resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestProperty {

	private String controller;
	private String action;
	private String method;
	private HttpSession session = null;
	private Map<String, Object> paraMap = null;
	
	public RequestProperty(HttpServletRequest request) {
		uriAnalyze(request.getRequestURI());
		this.method = request.getMethod();
		this.session = request.getSession();
		
		paraMap = new HashMap<>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		Iterator<String> keySet = request.getParameterNames().asIterator();
		while (keySet.hasNext()) {
			String key = keySet.next();
			paraMap.put(key, parameterMap.get(key));
		}
		
	}
	
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public Map<String, Object> getParaMap() {
		return paraMap;
	}
	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
	
	private void uriAnalyze(String requestURI) {
		String[] uri = requestURI.split("[/.]");
		this.controller = uri[2];
		this.action = uri[3];
	}
}
