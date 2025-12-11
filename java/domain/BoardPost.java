package domain;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BoardPost {
	
	private int postId;
	private int mangaId;
	private int postNumber;
	private String name;
	private String content;
	private Integer userId;
	private Date createdAt;
	private String createdAtStr;
	
	public BoardPost(int mangaId, int postNumber, String name, String content, Integer userId) {
		this.mangaId = mangaId;
		this.postNumber = postNumber;
		this.name = name;
		this.content = content;
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getMangaId() {
		return mangaId;
	}

	public void setMangaId(int mangaId) {
		this.mangaId = mangaId;
	}

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

