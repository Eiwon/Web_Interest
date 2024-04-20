package web.chat.resource;

public class MessageVO {
	
	private String roomId;
	private String writerId;
	private String content;
	
	public MessageVO() {}

	public MessageVO(String roomId, String writerId, String content) {
		this.roomId = roomId;
		this.writerId = writerId;
		this.content = content;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "MessageVO [roomId=" + roomId + ", writerId=" + writerId + ", content=" + content + "]";
	}
	
	
}
