<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>HUNTER×HUNTERクイズ - 問題${questionNumber}</title>
		<link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
	</head>
	
	<body>
		<h1>HUNTER×HUNTERクイズ</h1>
		<p>問題 ${questionNumber} / ${totalQuestions}</p>
		<p>難易度: ${question.difficultyStars}</p>
		
		<h2>この念能力の読み方は？</h2>
		
		<table border="1">
			<tr>
				<th>念能力</th>
				<td>${question.kanjiText}</td>
			</tr>
		</table>
		
		<form action="/miniApp/quiz" method="post">
			<input type="hidden" name="action" value="answer">
			
			<table border="1">
				<tr>
					<th>読み方</th>
					<td><input type="text" name="userAnswer" placeholder="読み方を入力してください"autocomplete="off"></td>
				</tr>
			</table>
			
			<c:if test="${question.hint != null && !question.hint.isEmpty()}">
				<p><strong>ヒント:</strong> ${question.hint}</p>
			</c:if>
			
			<input type="submit" value="回答する">
		</form>
		
		<p><a href="/miniApp/quiz">クイズメニューに戻る</a></p>
		<p><a href="/miniApp/mangaList">漫画一覧に戻る</a></p>
		
	</body>
	