package web.chat.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import web.chat.resource.Controller;
import web.chat.resource.RoomDAO;
import web.chat.resource.RoomDAOImple;
import web.chat.resource.RoomVO;

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
	public void action(String action, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(action.equals("selectAll")) {
			selectAll(request, response);
		}else if(action.equals("createRoom")) {
			createRoom(request, response);
		}else if(action.equals("updateName")) {
			updateName(request, response);
		}else if(action.equals("deleteRoom")) {
			deleteRoom(request, response);
		}else if(action.equals("lobby")) {
			toLobby(request, response);
		}
		
	} // end action
	
	private void toLobby(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("toLobby()");
		
		response.sendRedirect("lobby.jsp");
		
	} // end toLobby

	private void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("deleteRoom()");
		
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		int res = roomDao.deleteRoom(roomId);
		
		response.getWriter().write(String.valueOf(res));
		
	} // end deleteRoom

	private void updateName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("updateName()");
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		String roomName = request.getParameter("roomName");
		
		int res = roomDao.updateRoomName(roomName, roomId);
		response.getWriter().write(String.valueOf(res));
		
	} // end updateName

	private void createRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("createRoom()");
		
		RoomVO room = new RoomVO(0, request.getParameter("roomName"), request.getParameter("creatorId"), null);
		
		int res = roomDao.insertRoom(room);
		response.getWriter().write(String.valueOf(res));
	} // end createRoom

	private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("roomServ selectGet()");
		String pageStr = request.getParameter("page");
		int page = 1;
		if(pageStr != null)
			page = Integer.parseInt(pageStr);
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
		
		response.getWriter().write(jsonArray.toString());
		
	} // end selectGET
}
