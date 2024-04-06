package web.chat.resource;

public class MemberVO {
	private String userId;
	private String pw;
	private String name;
	private String email;
	private String phone;
	
	public MemberVO() {}
	public MemberVO(String userId, String pw, String name, String email, String phone) {
		this.userId = userId;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", pw=" + pw + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ "]";
	}
	
	
}
