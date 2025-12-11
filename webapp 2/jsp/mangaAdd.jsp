<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>漫画登録</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>漫画登録</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <c:if test="${errorMsg.size() > 0}">
        <ul>
            <c:forEach var="msg" items="${errorMsg}">
                <li style="color:red;">${msg}</li>
            </c:forEach>
        </ul>
    </c:if>
    
    <c:if test="${registerError != null}">
        <div style="color:red;">
            <p>${registerError}</p>
        </div>
    </c:if>
    
    <h2>漫画情報入力</h2>
    <form action="/miniApp/mangaAdd" method="post">
        <table border="1">
            <tr>
                <th>タイトル</th>
                <td><input type="text" name="title" placeholder="漫画のタイトル"></td>
            </tr>
            <tr>
                <th>作者</th>
                <td><input type="text" name="author" placeholder="作者名"></td>
            </tr>
            <tr>
                <th>巻数</th>
                <td><input type="text" name="number" placeholder="例：34巻、連載中"></td>
            </tr>
            <tr>
                <th>出版社</th>
                <td><input type="text" name="publisher" placeholder="出版社名"></td>
            </tr>
            <tr>
                <th>ジャンル</th>
                <td><input type="text" name="genre" placeholder="例：少年漫画、青年漫画"></td>
            </tr>
        </table>
        <input type="submit" value="登録">
    </form>
    
    <p><a href="/miniApp/mangaList">漫画一覧に戻る</a></p>
</body>
</html>
