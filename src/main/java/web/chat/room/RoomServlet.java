package web.chat.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import web.chat.resource.RoomDAOImple;
import web.chat.resource.RoomVO;

public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RoomDAOImple roomDao = null;
	
    public RoomServlet() {
    	roomDao = RoomDAOImple.getInstance();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURL = request.getRequestURI();
    	String requestMethod = request.getMethod();
    	System.out.println("roomServ : " + requestURL + ", " + requestMethod);
    	
    	if(requestMethod.equals("GET")) {
    		selectGET(request, response);
    	}
    	
    	
    }

    // 비동기 : 모든 room 검색 후 반환
	private void selectGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
	
	private void createRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("createRoom()");
		
		RoomVO room = new RoomVO(0, request.getParameter("roomName"), request.getParameter("creatorId"), null);
		
	}
}
