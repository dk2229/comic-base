package dto;

import java.sql.Timestamp;

public class QuizQuestionDTO {
	
	private int questionId;
	private Integer mangaId;
	private String kanjiText;
	private String correctAnswer;
	private String hint;
	private int difficulty;
	private Timestamp createdAt;
	
	
public QuizQuestionDTO() {}
	
	public QuizQuestionDTO(Integer mangaId, String kanjiText, String correctAnswer, String hint, int difficulty) {
		this.mangaId = mangaId;
		this.kanjiText = kanjiText;
		this.correctAnswer = correctAnswer;
		this.hint = hint;
		this.difficulty = difficulty;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Integer getMangaId() {
		return mangaId;
	}

	public void setMangaId(Integer mangaId) {
		this.mangaId = mangaId;
	}

	public String getKanjiText() {
		return kanjiText;
	}

	public void setKanjiText(String kanjiText) {
		this.kanjiText = kanjiText;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}

