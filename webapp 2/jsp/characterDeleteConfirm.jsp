<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>キャラクター削除確認</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>キャラクター削除確認</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <p>下記のキャラクターを削除してもよろしいですか？</p>
    
    <c:if test="${deleteError != null}">
        <div style="color:red;">
            <p>${deleteError}</p>
        </div>
    </c:if>
    
    <h2>キャラクター情報</h2>
    <form action="/miniApp/characterDelete" method="post">
        <input type="hidden" name="charaId" value="${character.charaId}">
        <table border="1">
            <tr>
                <th>キャラクター名</th>
                <td>${character.charaName}</td>
            </tr>
            <tr>
                <th>キャラタイプ</th>
                <td>${character.charaType}</td>
            </tr>
            <tr>
                <th>説明</th>
                <td>${character.explanation}</td>
            </tr>
            <tr>
                <th>初登場</th>
                <td>${character.first}</td>
            </tr>
        </table>
        <input type="submit" value="削除する">
    </form>
    
    <p><a href="/miniApp/characterDictionaryNew?mangaId=${character.mangaId}">キャラクター図鑑に戻る</a></p>
</body>
</html>