<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


	<head>
		<meta charset="UTF-8">
		<title>ホーム画面</title>
		<link rel="stylesheet" type="text/css"href="/miniApp/css/stylesheet.css"/>
	</head>
	
	<body>
		<h1>ホーム画面</h1>
		<p style="text-align: right">${user.name} 様ログイン中</p>
		<p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
		<h1>Comic Base</h1>
		<p><a href="/miniApp/mypage">マイページ（投稿したレビュー）</a></p>
		<p><a href="/miniApp/edit">メンバー情報の編集</a></p>
		<p><a href="/miniApp/delete">退会する方はこちら</a></p>
		
		
		<p><a href="/miniApp/mangaList">漫画一覧・管理</a></p>
		
	</body>
	
</html>