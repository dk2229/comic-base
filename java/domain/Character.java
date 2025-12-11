package domain;
	
	
import java.text.SimpleDateFormat;
import java.util.Date;

public class Character {

		
		private int charaId;
		private int mangaId;
		private String charaName;
		private String charaType;
		private String explanation;
		private String first;
		private Date createdAt;
		private String createdAtStr;
		
		public Character(int mangaId, String charaName, String charaType, String explanation, String first) {
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
