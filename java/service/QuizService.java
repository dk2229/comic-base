package service;

import java.util.ArrayList;
import java.util.List;

import dao.QuizQuestionDAO;
import domain.QuizQuestion;
import dto.QuizQuestionDTO;


public class QuizService {
	
	
	public List<QuizQuestion> getQuizQuestions(int mangaId, int questionCount) {
		QuizQuestionDAO quizDAO = new QuizQuestionDAO();
		List<QuizQuestionDTO> dtoList = quizDAO.selectRandomQuestions(mangaId, questionCount);
		List<QuizQuestion> questionList = new ArrayList<>();
		
		for(QuizQuestionDTO dto : dtoList) {
			QuizQuestion question = new QuizQuestion(dto.getMangaId(), dto.getKanjiText(), dto.getCorrectAnswer(), dto.getHint(), dto.getDifficulty());
			question.setQuestionId(dto.getQuestionId());
			question.setCreatedAt(dto.getCreatedAt());
			questionList.add(question);
		}
		
		return questionList;
	}
	
	public boolean checkAnswer(String userAnswer, String correctAnswer) {
		if(userAnswer == null || userAnswer.trim().isEmpty()) {
			return false;
		}
		
		// 入力が問題文そのものかチェック（HUNTER×HUNTERクイズのmanga_id=2）
		QuizQuestionDAO quizDAO = new QuizQuestionDAO();
		boolean isQuestionText = quizDAO.isQuestionText(userAnswer.trim(), 2);
				
		if(isQuestionText == true) {
			return false;
		}
		
		String normalizedUser = normalizeAnswer(userAnswer.trim());
		String normalizedCorrect = normalizeAnswer(correctAnswer);
		
		return normalizedUser.equals(normalizedCorrect);
	}
	
	private String normalizeAnswer(String answer) {
		if(answer == null) return "";
		
		// まず大文字小文字を統一
		String normalized = answer.toLowerCase();
		
		// 【修正1】長音記号を最初に統一（「～」「〜」を「ー」に変換のみ）
		normalized = normalized.replace("～", "ー").replace("〜", "ー");
				
		// 全角英数字を半角に変換
		normalized = normalized.replace("０", "0").replace("１", "1").replace("２", "2").replace("３", "3").replace("４", "4")
				.replace("５", "5").replace("６", "6").replace("７", "7").replace("８", "8").replace("９", "9")
				.replace("Ａ", "a").replace("Ｂ", "b").replace("Ｃ", "c").replace("Ｄ", "d").replace("Ｅ", "e")
				.replace("Ｆ", "f").replace("Ｇ", "g").replace("Ｈ", "h").replace("Ｉ", "i").replace("Ｊ", "j")
				.replace("Ｋ", "k").replace("Ｌ", "l").replace("Ｍ", "m").replace("Ｎ", "n").replace("Ｏ", "o")
				.replace("Ｐ", "p").replace("Ｑ", "q").replace("Ｒ", "r").replace("Ｓ", "s").replace("Ｔ", "t")
				.replace("Ｕ", "u").replace("Ｖ", "v").replace("Ｗ", "w").replace("Ｘ", "x").replace("Ｙ", "y").replace("Ｚ", "z");
		
		normalized = normalized.replace("ア", "あ").replace("イ", "い").replace("ウ", "う").replace("エ", "え").replace("オ", "お")
				.replace("カ", "か").replace("キ", "き").replace("ク", "く").replace("ケ", "け").replace("コ", "こ")
				.replace("サ", "さ").replace("シ", "し").replace("ス", "す").replace("セ", "せ").replace("ソ", "そ")
				.replace("タ", "た").replace("チ", "ち").replace("ツ", "つ").replace("テ", "て").replace("ト", "と")
				.replace("ナ", "な").replace("ニ", "に").replace("ヌ", "ぬ").replace("ネ", "ね").replace("ノ", "の")
				.replace("ハ", "は").replace("ヒ", "ひ").replace("フ", "ふ").replace("ヘ", "へ").replace("ホ", "ほ")
				.replace("マ", "ま").replace("ミ", "み").replace("ム", "む").replace("メ", "め").replace("モ", "も")
				.replace("ヤ", "や").replace("ユ", "ゆ").replace("ヨ", "よ")
				.replace("ラ", "ら").replace("リ", "り").replace("ル", "る").replace("レ", "れ").replace("ロ", "ろ")
				.replace("ワ", "わ").replace("ヲ", "を").replace("ン", "ん")
				.replace("ガ", "が").replace("ギ", "ぎ").replace("グ", "ぐ").replace("ゲ", "げ").replace("ゴ", "ご")
				.replace("ザ", "ざ").replace("ジ", "じ").replace("ズ", "ず").replace("ゼ", "ぜ").replace("ゾ", "ぞ")
				.replace("ダ", "だ").replace("ヂ", "ぢ").replace("ヅ", "づ").replace("デ", "で").replace("ド", "ど")
				.replace("バ", "ば").replace("ビ", "び").replace("ブ", "ぶ").replace("ベ", "べ").replace("ボ", "ぼ")
				.replace("パ", "ぱ").replace("ピ", "ぴ").replace("プ", "ぷ").replace("ペ", "ぺ").replace("ポ", "ぽ")
				.replace("ッ", "っ").replace("ャ", "ゃ").replace("ュ", "ゅ").replace("ョ", "ょ");
		
		// カタカナをひらがなに変換（濁音・半濁音）
		normalized = normalized.replace("ガ", "が").replace("ギ", "ぎ").replace("グ", "ぐ").replace("ゲ", "げ").replace("ゴ", "ご")
				.replace("ザ", "ざ").replace("ジ", "じ").replace("ズ", "ず").replace("ゼ", "ぜ").replace("ゾ", "ぞ")
				.replace("ダ", "だ").replace("ヂ", "ぢ").replace("ヅ", "づ").replace("デ", "で").replace("ド", "ど")
				.replace("バ", "ば").replace("ビ", "び").replace("ブ", "ぶ").replace("ベ", "べ").replace("ボ", "ぼ")
				.replace("パ", "ぱ").replace("ピ", "ぴ").replace("プ", "ぷ").replace("ペ", "ぺ").replace("ポ", "ぽ");
				
		// カタカナをひらがなに変換（拗音・促音）
		normalized = normalized.replace("ッ", "っ").replace("ャ", "ゃ").replace("ュ", "ゅ").replace("ョ", "ょ")
				.replace("ァ", "ぁ").replace("ィ", "ぃ").replace("ゥ", "ぅ").replace("ェ", "ぇ").replace("ォ", "ぉ");
				
		// スペースや記号を除去
		normalized = normalized.replace(" ", "").replace("　", "").replace("・", "").replace("＝", "");
				
		return normalized;
	}
	
	public int getTotalQuestions(int mangaId) {
		QuizQuestionDAO quizDAO = new QuizQuestionDAO();
		return quizDAO.getTotalQuestions(mangaId);
	}
}
