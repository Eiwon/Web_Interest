package web.chat.controller;

import web.chat.resource.Controller;

public class LoginController implements Controller{

	private static LoginController instance = null;
	
	private LoginController() {}
	
	public static LoginController getInstance() {
		if(instance == null) {
			instance = new LoginController();
		}
		return instance;
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
