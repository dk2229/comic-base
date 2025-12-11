package dto;

import java.sql.Timestamp;

public class CharacterDTO {
	
	private int charaId;
	private int mangaId;
	private String charaName;
	private String charaType;
	private String explanation;
	private String first;
	private Timestamp createdAt;
	
	public CharacterDTO() {}
	
	public CharacterDTO(int mangaId, String charaName, String charaType, String explanation, String first) {
		this.mangaId = mangaId;
		this.charaName = charaName;
		this.charaType = charaType;
		this.explanation = explanation;
		this.first = first;
	}

	public int getCharaId() {
		return charaId;
	}

	public void setCharaId(int charaId) {
		this.charaId = charaId;
	}

	public int getMangaId() {
		return mangaId;
	}

	public void setMangaId(int mangaId) {
		this.mangaId = mangaId;
	}

	public String getCharaName() {
		return charaName;
	}

	public void setCharaName(String charaName) {
		this.charaName = charaName;
	}

	public String getCharaType() {
		return charaType;
	}

	public void setCharaType(String charaType) {
		this.charaType = charaType;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
