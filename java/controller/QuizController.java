package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.QuizQuestion;
import service.QuizService;

@WebServlet("/quiz")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if("start".equals(action)) {
			startQuiz(request, response);
		} else if("next".equals(action)) {
	        showNextQuestion(request, response);
		} else {
			showQuizMenu(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if("answer".equals(action)) {
			processAnswer(request, response);
		} else {
			showQuizMenu(request, response);
		}
	}
	
	private void showQuizMenu(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("quizQuestions");
		session.removeAttribute("currentQuestionIndex");
		session.removeAttribute("quizScore");
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/quizMenu.jsp");
		rd.forward(request, response);
	}
	
	private void startQuiz(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		QuizService quizService = new QuizService();
		
		int mangaId = 2;
		int questionCount = 10;
		
		List<QuizQuestion> questions = quizService.getQuizQuestions(mangaId, questionCount);
		
		if(questions.isEmpty()) {
			request.setAttribute("errorMessage", "クイズ問題が見つかりませんでした。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/quizMenu.jsp");
			rd.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("quizQuestions", questions);
		session.setAttribute("currentQuestionIndex", 0);
		session.setAttribute("quizScore", 0);
		
		QuizQuestion currentQuestion = questions.get(0);
		request.setAttribute("question", currentQuestion);
		request.setAttribute("questionNumber", 1);
		request.setAttribute("totalQuestions", questions.size());
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/quiz.jsp");
		rd.forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void processAnswer(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<QuizQuestion> questions = (List<QuizQuestion>) session.getAttribute("quizQuestions");
		Integer currentIndex = (Integer) session.getAttribute("currentQuestionIndex");
		Integer score = (Integer) session.getAttribute("quizScore");
		
		if(questions == null || currentIndex == null || score == null) {
			request.setAttribute("errorMessage", "セッションが無効です。最初からやり直してください。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/quizMenu.jsp");
			rd.forward(request, response);
			return;
		}
		
		String userAnswer = request.getParameter("userAnswer");
		QuizQuestion currentQuestion = questions.get(currentIndex);
		
		QuizService quizService = new QuizService();
		boolean isCorrect = quizService.checkAnswer(userAnswer, currentQuestion.getCorrectAnswer());
		
		if(isCorrect) {
			score++;
			session.setAttribute("quizScore", score);
		}
		
		request.setAttribute("question", currentQuestion);
		request.setAttribute("userAnswer", userAnswer != null ? userAnswer : "");
		request.setAttribute("correctAnswer", currentQuestion.getCorrectAnswer());
		request.setAttribute("isCorrect", isCorrect);
		
		int nextIndex = currentIndex + 1;
		boolean hasNextQuestion = nextIndex < questions.size();
		
		request.setAttribute("hasNextQuestion", hasNextQuestion);
		
		if(hasNextQuestion) {
			session.setAttribute("currentQuestionIndex", nextIndex);
			request.setAttribute("nextQuestionNumber", nextIndex + 1);
		} else {
			request.setAttribute("finalScore", score);
			request.setAttribute("totalQuestions", questions.size());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/quizResult.jsp");
		rd.forward(request, response);
		}
		
	@SuppressWarnings("unchecked")
		private void showNextQuestion(HttpServletRequest request, HttpServletResponse response) 
		        throws ServletException, IOException {
		    
		    HttpSession session = request.getSession();
		    List<QuizQuestion> questions = (List<QuizQuestion>) session.getAttribute("quizQuestions");
		    Integer currentIndex = (Integer) session.getAttribute("currentQuestionIndex");
		    
		    if(questions == null || currentIndex == null) {
		        showQuizMenu(request, response);
		        return;
		    }
		    
		    QuizQuestion currentQuestion = questions.get(currentIndex);
		    request.setAttribute("question", currentQuestion);
		    request.setAttribute("questionNumber", currentIndex + 1);
		    request.setAttribute("totalQuestions", questions.size());
		    
		    RequestDispatcher rd = request.getRequestDispatcher("/jsp/quiz.jsp");
		    rd.forward(request, response);
		
		
	}
}