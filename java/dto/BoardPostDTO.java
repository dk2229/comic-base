package dto;


import java.sql.Timestamp;

public class BoardPostDTO {
	
	private int postId;
	private int mangaId;
	private int postNumber;
	private String name;
	private String content;
	private Integer userId;
	private Timestamp createdAt;
	
	public BoardPostDTO() {}
	
	public BoardPostDTO(int mangaId, int postNumber, String name, String content, Integer userId) {
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}


