package domain;

import java.text.SimpleDateFormat;
import java.util.Date;


public class QuizQuestion {
	

	private int questionId;
	private Integer mangaId;
	private String kanjiText;
	private String correctAnswer;
	private String hint;
	private int difficulty;
	private Date createdAt;
	private String createdAtStr;
	
	public QuizQuestion(Integer mangaId, String kanjiText, String correctAnswer, String hint, int difficulty) {
		this.mangaId = mangaId;
		this.kanjiText = kanjiText;
		this.correctAnswer = correctAnswer;
		this.hint = hint;
		this.difficulty = difficulty;
	}

	public String getDifficultyStars() {
		switch(difficulty) {
			case 1: return "★☆☆☆☆";
			case 2: return "★★☆☆☆";
			case 3: return "★★★☆☆";
			case 4: return "★★★★☆";
			case 5: return "★★★★★";
			default: return "★☆☆☆☆";
		}
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
