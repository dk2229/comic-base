package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
		
		private int reviewsId;
		private int userId;
		private int mangaId;
		private String name;
		private String evaluation;
		private String impressions;
		private String number;
		private Date createdAt;
		private String createdAtStr;
		
		public Review(int userId, int mangaId, String name, String evaluation, String impressions, String number) {
			this.userId = userId;
			this.mangaId = mangaId;
			this.name = name;
			this.evaluation = evaluation;
			this.impressions = impressions;
			this.number = number;
		}

		public int getReviewsId() {
			return reviewsId;
		}

		public void setReviewsId(int reviewsId) {
			this.reviewsId = reviewsId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getMangaId() {
			return mangaId;
		}

		public void setMangaId(int mangaId) {
			this.mangaId = mangaId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEvaluation() {
			return evaluation;
		}

		public void setEvaluation(String evaluation) {
			this.evaluation = evaluation;
		}

		public String getImpressions() {
			return impressions;
		}

		public void setImpressions(String impressions) {
			this.impressions = impressions;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
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
