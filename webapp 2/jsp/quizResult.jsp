<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>HUNTER×HUNTERクイズ - 結果</title>
		<link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
	</head>
	
	<body>
		<h1>HUNTER×HUNTERクイズ</h1>
		
		<!-- 正解/不正解表示 -->
		<c:if test="${isCorrect}">
			<h2>正解！</h2>
		</c:if>
		<c:if test="${!isCorrect}">
			<h2>不正解</h2>
		</c:if>
		
		<!-- 問題結果テーブル -->
		<table border="1">
			<tr>
				<th>問題</th>
				<td>${question.kanjiText}</td>
			</tr>
			<tr>
				<th>あなたの回答</th>
				<td>${userAnswer}</td>
			</tr>
			<tr>
				<th>正解</th>
				<td>${correctAnswer}</td>
			</tr>
			<c:if test="${question.hint != null}">
				<tr>
					<th>ヒント</th>
					<td>${question.hint}</td>
				</tr>
			</c:if>
		</table>
		
		<!-- 次の問題がある場合 -->
		<c:if test="${hasNextQuestion}">
			<h2>次の問題に進みますか？</h2>
			<form action="/miniApp/quiz" method="get">
				<input type="hidden" name="action" value="next">
				<input type="submit" value="問題${nextQuestionNumber}へ">
			</form>
			<p><a href="/miniApp/quiz">終了する</a></p>
		</c:if>
		
		<!-- 最終結果の場合 -->
		<c:if test="${!hasNextQuestion}">
			<h2>最終結果</h2>
			<p>${finalScore} / ${totalQuestions} 問正解！</p>
			
			<!-- スコア評価 -->
			<c:if test="${finalScore == totalQuestions}">
				<p>パーフェクト！</p>
			</c:if>
			<c:if test="${finalScore >= totalQuestions * 0.8 && finalScore < totalQuestions}">
				<p>素晴らしい！</p>
			</c:if>
			<c:if test="${finalScore >= totalQuestions * 0.6 && finalScore < totalQuestions * 0.8}">
				<p>よくできました！</p>
			</c:if>
			<c:if test="${finalScore < totalQuestions * 0.6}">
				<p>復習して再挑戦！</p>
			</c:if>
			
			<p><a href="/miniApp/quiz">もう一度挑戦</a></p>
			<p><a href="/miniApp/mangaList">漫画一覧に戻る</a></p>
			<p><a href="/miniApp/jsp/home.jsp">ホーム画面へ</a></p>
		</c:if>
		
	</body>
	
</html>