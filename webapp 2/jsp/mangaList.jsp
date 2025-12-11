<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comic Base-漫画一覧</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>Comic Base</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <h2>登録されている漫画</h2>
    <p><a href="/miniApp/mangaAdd">新しい漫画を登録する</a></p>
    
    <c:if test="${mangaList.size() > 0}">
        <table border="1">
            <tr>
                <th>タイトル</th>
                <th>作者</th>
                <th>巻数</th>
                <th>出版社</th>
                <th>ジャンル</th>
                <th>レビュー</th>
                <th>キャラクター</th>
                <th>ゲーム</th>
                <th>編集</th>
            </tr>
            <c:forEach var="manga" items="${mangaList}">
                <tr>
                    <td>${manga.title}</td>
                    <td>${manga.author}</td>
                    <td>${manga.number}</td>
                    <td>${manga.publisher}</td>
                    <td>${manga.genre}</td>
                    <td><a href="/miniApp/reviewList?mangaId=${manga.mangaId}">レビュー一覧</a></td>
                    <td><a href="/miniApp/characterDictionaryNew?mangaId=${manga.mangaId}">キャラクター図鑑</a></td>
                    
                    <td>
                    	<!-- ハンター×ハンター（manga_id=2）の場合のみクイズリンク表示 -->
                    	<c:if test="${manga.mangaId == 2}">
                        	<a href="/miniApp/quiz">念能力クイズ</a>
                    	</c:if>
                    	<c:if test="${manga.mangaId != 2}">
                        	準備中
                    	</c:if>
                	</td>
                	<td><a href="/miniApp/mangaEdit?mangaId=${manga.mangaId}">編集</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    
    <c:if test="${mangaList.size() == 0}">
        <p>まだ漫画が登録されていません。</p>
    </c:if>
    
    <p><a href="/miniApp/jsp/home.jsp">ホーム画面に戻る</a></p>
</body>
</html>