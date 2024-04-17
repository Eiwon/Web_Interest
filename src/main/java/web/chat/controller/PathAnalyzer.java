package web.chat.controller;

public class PathAnalyzer {

	private String controller = "";
	private String action = "";
	private String method = "";
	
	private static PathAnalyzer instance = null;
	
	private PathAnalyzer() {}
	
	public static PathAnalyzer getInstance() {
		if(instance == null)
			instance = new PathAnalyzer();
		return instance;
	}
	// /WebChat/login/signIn.do
	public void analyze(String requestURI, String requestMethod) {
		System.out.println("호출 경로 : " + requestURI);
		String[] uri = requestURI.split("[/.]");
		this.controller = uri[2];
		this.action = uri[3];
		this.method = requestMethod;
	}
	
	public String getController() {
		return controller;
	}
	
	public String getAction() {
		return action;
	}
	
	public String getMethod() {
		return method;
	}
}
