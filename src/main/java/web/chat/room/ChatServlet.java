package web.chat.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import web.chat.resource.MessageVO;

@ServerEndpoint("/chatSpace/chatServer")
public class ChatServlet extends Endpoint{

	private static HashMap<String, List<Session>> roomMap = null;
	
	@Override
	public void onOpen(Session session, EndpointConfig config) {
		System.out.println("ChatServlet onOpen()");
		
		if(roomMap == null) {
			roomMap = new HashMap<>();
		}
		
        session.addMessageHandler(new MessageHandler.Whole<String>() {
             public void onMessage(String text) {
            	System.out.println("receive msg : " + text);
            	MessageVO msg = parseToVO(text);
				System.out.println(msg.getWriterId() + ", " + msg.getContent());
				
				connChk(session, msg);
				broadCast(msg);	
             }
        });

	} // end onOpen
	
	
	@Override
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("ChatServlet onClose()");
	}
	
	@Override
	public void onError(Session session, Throwable thr) {
		System.err.println("ChatServlet onError()");
	}
	
	private MessageVO parseToVO(String text) {
		System.out.println("parse to MessageVO()");
		JSONObject msgObj = null;
		MessageVO msg = null;
		try {
			msgObj = (JSONObject)new JSONParser().parse(text);
			msg = new MessageVO(
					(String) msgObj.get("roomId"),
					(String) msgObj.get("writerId"),
					(String) msgObj.get("content")
					);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return msg;
	} // end parseToVO
	
	private void connChk(Session session, MessageVO msg) {
		System.out.println("connection Check()");
		if(msg.getContent().equals("connection")) {
			System.out.println("first request");
			// roomId에 해당하는 방이 맵에 등록되어있지 않다면 추가
			if(!roomMap.containsKey(msg.getRoomId())) {
				roomMap.put(msg.getRoomId(), new ArrayList<>());
			}
			// 해당 세션을 등록
			roomMap.get(msg.getRoomId()).add(session);
			msg.setContent("님께서 입장하셨습니다.");
		}		
	} // end connChk
	
	private void broadCast(MessageVO msg) {
		System.out.println("broadCast()");
		JSONObject jsonMsg = new JSONObject();
		jsonMsg.put("writer", msg.getWriterId());
		jsonMsg.put("content", msg.getContent());
		
		for(Session client : roomMap.get(msg.getRoomId())) {
			if(client.isOpen()) {
				client.getAsyncRemote().sendText(jsonMsg.toJSONString());
			}
        }
	} // end broadCast
}
