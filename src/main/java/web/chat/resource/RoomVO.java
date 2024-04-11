package web.chat.resource;

import java.util.Date;

public class RoomVO {
	private int roomId;
	private String roomName;
	private String creatorId;
	private Date createdAt;
	
	public RoomVO() {}

	public RoomVO(int roomId, String roomName, String creatorId, Date createdAt) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.creatorId = creatorId;
		this.createdAt = createdAt;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "RoomVO [roomId=" + roomId + ", roomName=" + roomName + ", creatorId=" + creatorId + ", createdAt="
				+ createdAt + "]";
	}
	
}
