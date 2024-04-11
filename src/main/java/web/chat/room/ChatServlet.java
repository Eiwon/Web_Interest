package web.chat.room;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
            	System.out.println("receive msg : " + text);
            	JSONObject msg = null;
            	try {
					msg = (JSONObject)new JSONParser().parse(text);
					System.out.println(msg.get("writer") + ", " + msg.get("content"));
					for(Session client : clients) {
						if(client.isOpen()) {
							client.getAsyncRemote().sendText(text);
						}
                    }
            	} catch (Exception e1) {
					e1.printStackTrace();
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
