package web.chat.controller;

import web.chat.resource.Controller;

public class RoomController implements Controller{
	
	private static RoomController instance = null;
	
	private RoomController() {}
	
	public static RoomController getInstance() {
		if(instance == null) {
			instance = new RoomController();
		}
		return instance;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
}
