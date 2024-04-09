package web.chat.resource;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatSpace/webSocket")
public class WebSocket{
	
	@OnOpen
	private void handleOpen() {
		// TODO Auto-generated method stub

	}
	
	@OnMessage
	private void handleMessage() {
		// TODO Auto-generated method stub

	}
	
	@OnClose
	private void handleClose() {
		// TODO Auto-generated method stub

	}
	
	@OnError
	private void handleError() {
		// TODO Auto-generated method stub

	}
}
