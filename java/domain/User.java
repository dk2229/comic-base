package domain;

import java.text.SimpleDateFormat;
import java.util.Date;



public class User {
	
	private int id;
	private String loginId;
	private String password;
	private String name;
	private Date createdAt;
	private String createdAtStr;
	
	public User(String loginId, String password, String name) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
			
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		this.createdAtStr = new SimpleDateFormat("yyyy年 MM月 dd日 hh時 mm分").format(createdAt);
	}

	public String getCreatedAtStr() {
		return createdAtStr;
	}

}
	



