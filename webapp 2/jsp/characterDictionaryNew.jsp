<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>キャラクター図鑑</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>キャラクター図鑑</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <h2>${manga.title} のキャラクター</h2>
    <p>作者：${manga.author} | 出版社：${manga.publisher}</p>
    
    <p><a href="/miniApp/characterAdd?mangaId=${manga.mangaId}">キャラクターを追加する</a></p>
    
    <c:if test="${characterList.size() > 0}">
        <table border="1">
            <tr>
                <th>キャラクター名</th>
                <th>キャラタイプ</th>
                <th>説明</th>
                <th>初登場</th>
                <th>操作</th>
            </tr>
            <c:forEach var="character" items="${characterList}">
                <tr>
                    <td>${character.charaName}</td>
                    <td>${character.charaType}</td>
                    <td>${character.explanation}</td>
                    <td>${character.first}</td>
                    <td>
                        <a href="/miniApp/characterEdit?charaId=${character.charaId}">編集</a> |
                        <a href="/miniApp/characterDelete?charaId=${character.charaId}" 
                           onclick="return confirm('このキャラクターを削除してもよろしいですか？')">削除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    
    <c:if test="${characterList.size() == 0}">
        <p>まだキャラクターが登録されていません。</p>
    </c:if>
    
    <p><a href="/miniApp/mangaList">漫画一覧に戻る</a></p>
</body>
</html>