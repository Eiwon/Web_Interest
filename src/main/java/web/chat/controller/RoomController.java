package web.chat.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import web.chat.resource.Controller;
import web.chat.resource.RequestProperty;
import web.chat.resource.RoomDAO;
import web.chat.resource.RoomDAOImple;
import web.chat.resource.RoomVO;
import web.chat.resource.Router;

public class RoomController implements Controller{
	
	private static RoomController instance = null;
	private RoomDAO roomDao = null;
	private RoomController() {
		roomDao = RoomDAOImple.getInstance();
	}
	
	public static RoomController getInstance() {
		if(instance == null) {
			instance = new RoomController();
		}
		return instance;
	}

	@Override
	public Router action(RequestProperty rp, Map<String, Object> attr) {
		System.out.println("enter RoomController");
		String action = rp.getAction();
		Map<String, Object> paraMap = rp.getParaMap();
		
		if(action.equals("selectAll")) {
			return selectAll(paraMap, attr);
		}else if(action.equals("createRoom")) {
			return createRoom(paraMap, attr);
		}else if(action.equals("updateName")) {
			return updateName(paraMap, attr);
		}else if(action.equals("deleteRoom")) {
			return deleteRoom(paraMap, attr);
		}else if(action.equals("lobby")) {
			return toLobby();
		}else if(action.equals("room")) {
			return toRoom(paraMap, attr);
		}
		
		return null;
	} // end action
	
	private Router toRoom(Map<String, Object> paraMap, Map<String, Object> attr) {
		System.out.println("toRoom()");
		int roomId = (int)paraMap.get("roomId");
		String path = "/WEB-INF/chatSpace/room.jsp";
		RoomVO target = roomDao.selectById(roomId);
		attr.put("roomVO", target);
		return new Router(path, "forward");
	} // end toRoom

	private Router toLobby(){
		System.out.println("toLobby()");
		String path = "/WEB-INF/chatSpace/lobby.jsp";
		return new Router(path, "forward");
	} // end toLobby

	private Router deleteRoom(Map<String, Object> paraMap, Map<String, Object> attr) {
		System.out.println("deleteRoom()");
		
		int roomId = (int)(paraMap.get("roomId"));
		int res = roomDao.deleteRoom(roomId);
		attr.put("result", res);
		return new Router(null, "write");
	} // end deleteRoom

	private Router updateName(Map<String, Object> paraMap, Map<String, Object> attr) {
		System.out.println("updateName()");
		int roomId = (int)(paraMap.get("roomId"));
		String roomName = (String)paraMap.get("roomName");
		
		int res = roomDao.updateRoomName(roomName, roomId);
		attr.put("result", res);
		return new Router(null, "write");
	} // end updateName

	private Router createRoom(Map<String, Object> paraMap, Map<String, Object> attr) {
		System.out.println("createRoom()");
		String creatorId = (String)paraMap.get("creatorId");
		RoomVO room = new RoomVO(0, (String)paraMap.get("roomName"), creatorId, null);
		
		int res = roomDao.insertRoom(room);
		int recentRoomId = (res == 1) ? roomDao.getRecentRoomId(creatorId) : 0;
		
		attr.put("result", res);
		attr.put("roomId", recentRoomId);
		return new Router(null, "write");
	} // end createRoom

	private Router selectAll(Map<String, Object> paraMap, Map<String, Object> attr) {
		System.out.println("roomServ selectGet()");
		String pageStr = (String)paraMap.get("page");
		int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);
		List<RoomVO> list = roomDao.selectAllRoom(page);
		
		JSONArray jsonArray = new JSONArray();
		
		for(RoomVO room : list) {
			JSONObject obj = new JSONObject();
			obj.put("roomId", room.getRoomId());
			obj.put("roomName", room.getRoomName());
			obj.put("creatorId", room.getCreatorId());
			obj.put("createdAt", room.getCreatedAt().toString());
			
			jsonArray.add(obj);
		}
		attr.put("roomList", jsonArray);
		return new Router(null, "write");
		
	} // end selectGET
}
