<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>変更確認画面</title>
		<link rel="stylesheet" type="text/css"href="/miniApp/css/stylesheet.css"/>
	</head>
	
	<body>
		<h1>メンバー情報編集画面</h1>
		<p style="text-align: right">${user.name} 様ログイン中</p>
		<p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
		
		<h2>変更内容入力</h2>
		<p>下記の内容でよろしければ、変更ボタンを押してください。</p>
		<form action="/miniApp/editConfirm" method= "post">
			<table border="1">
				<tr>
					<th>お名前</th>
					<td><input type="hidden" name="editName" value="${editName}">${editName}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="hidden" name="editPassword" value="${editPassword}">${editPassword}</td>
				</tr>
				<input type="hidden" name="loginId" value="${user.loginId}"/>
			</table>
			<input type="Submit" value="変更する">
		</form>
		<p>><a href="/miniApp/jsp/login.jsp">トップ画面に戻る</a></p>	
	</body>
	
	
</html>