<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>HUNTER×HUNTERクイズ</title>
		<link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
	</head>
	
	<body>
		<h1>HUNTER×HUNTERクイズ</h1>
		<c:if test="${user != null}">
			<p style="text-align: right">${user.name} 様ログイン中</p>
			<p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
		</c:if>
		
		<h2>念能力読み方クイズ</h2>
		<p>HUNTER×HUNTERの念能力名の読み方を答えるクイズです</p>
		<p>問題数: 10問</p>
		<p>難易度: ★☆☆☆☆ 〜 ★★★★★</p>
		<p>例: 「律する小指の鎖」→「ジャッジメントチェーン」</p>
		
		<c:if test="${errorMessage != null}">
			<div style="color: red;">
				<p>${errorMessage}</p>
			</div>
		</c:if>
		
		<form action="/miniApp/quiz" method="get">
			<input type="hidden" name="action" value="start">
			<input type="submit" value="クイズスタート">
		</form>
		
		<h2>ルール</h2>
		<p>・ひらがな、カタカナ、どちらでもOK</p>
		<p>・大文字小文字は区別しません</p>
		<p>・各問題にヒントがあります</p>
		
		<p><a href="/miniApp/jsp/home.jsp">ホーム画面に戻る</a></p>
		<p><a href="/miniApp/mangaList">漫画一覧に戻る</a></p>
		
	</body>
	
</html>