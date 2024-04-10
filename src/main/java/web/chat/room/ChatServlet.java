package web.chat.room;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatSpace/chatServer")
public class ChatServlet extends Endpoint{

	private static List<Session> clients = null;
	
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		System.out.println("ChatServlet onOpen()");
		if(clients == null) {
			clients = new ArrayList<>();
			System.out.println("client list init");
		}
		clients.add(session);
		
        session.addMessageHandler(new MessageHandler.Whole<String>() {
             public void onMessage(String text) {
            	try {
                	for(Session client : clients) {
                		client.getAsyncRemote().sendText("echo " + text);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
             }
        });

	}
	
	@Override
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("ChatServlet onClose()");
	}
	
	@Override
	public void onError(Session session, Throwable thr) {
		System.err.println("ChatServlet onError()");
	}
	
}
