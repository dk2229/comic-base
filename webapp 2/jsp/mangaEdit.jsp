<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comic Base-漫画編集</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>Comic Base</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <h2>漫画情報編集</h2>
    
    <c:if test="${errorMsg.size() > 0}">
        <ul>
            <c:forEach var="msg" items="${errorMsg}">
                <li style="color:red;">${msg}</li>
            </c:forEach>
        </ul>
    </c:if>
    
    <c:if test="${editError != null}">
        <div style="color:red;">
            <p>${editError}</p>
        </div>
    </c:if>
    
    <form action="/miniApp/mangaEdit" method="post">
        <input type="hidden" name="mangaId" value="${manga.mangaId}">
        <table border="1">
            <tr>
                <th>タイトル</th>
                <td><input type="text" name="title" value="${manga.title}" placeholder="タイトルを入力"></td>
            </tr>
            <tr>
                <th>作者</th>
                <td><input type="text" name="author" value="${manga.author}" placeholder="作者名を入力"></td>
            </tr>
            <tr>
                <th>巻数</th>
                <td><input type="text" name="number" value="${manga.number}" placeholder="巻数を入力"></td>
            </tr>
            <tr>
                <th>出版社</th>
                <td><input type="text" name="publisher" value="${manga.publisher}" placeholder="出版社を入力"></td>
            </tr>
            <tr>
                <th>ジャンル</th>
                <td><input type="text" name="genre" value="${manga.genre}" placeholder="ジャンルを入力"></td>
            </tr>
        </table>
        <input type="submit" value="更新">
    </form>
    
    <p><a href="/miniApp/mangaList">漫画一覧に戻る</a></p>
</body>
</html>