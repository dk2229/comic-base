<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>キャラクター追加</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>キャラクター追加</h1>
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
    
    <h2>キャラクター情報入力</h2>
    <form action="/miniApp/characterAdd" method="post">
        <input type="hidden" name="mangaId" value="${mangaId}">
        <table border="1">
            <tr>
                <th>キャラクター名</th>
                <td><input type="text" name="charaName" placeholder="キャラクター名"></td>
            </tr>
            <tr>
                <th>キャラタイプ</th>
                <td><input type="text" name="charaType" placeholder="例：主人公、仲間、敵"></td>
            </tr>
            <tr>
                <th>説明</th>
                <td><textarea name="explanation" rows="3" cols="50" placeholder="キャラクターの説明"></textarea></td>
            </tr>
            <tr>
                <th>初登場</th>
                <td><input type="text" name="first" placeholder="例：1巻、第3話"></td>
            </tr>
        </table>
        <input type="submit" value="追加">
    </form>
    
    <p><a href="/miniApp/characterDictionary?mangaId=${mangaId}">キャラクター図鑑に戻る</a></p>
</body>
</html>
