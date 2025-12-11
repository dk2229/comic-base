package dto;

import java.sql.Timestamp;

public class MangaDTO {
		
		private int mangaId;
		private String title;
		private String author;
		private String number;
		private String publisher;
		private String genre;
		private Timestamp createdAt;
		
		public MangaDTO() {}
		
		public MangaDTO(String title, String author, String number, String publisher, String genre) {
			this.title = title;
			this.author = author;
			this.number = number;
			this.publisher = publisher;
			this.genre = genre;
		}

		public int getMangaId() {
			return mangaId;
		}

		public void setMangaId(int mangaId) {
			this.mangaId = mangaId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public Timestamp getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}
	}

	