<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>レビュー投稿</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>レビュー投稿</h1>
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
    
    <h2>レビュー投稿フォーム</h2>
    <form action="/miniApp/reviewAdd" method="post">
        <input type="hidden" name="mangaId" value="${mangaId}">
        <table border="1">
            <tr>
                <th>お名前</th>
                <td><input type="text" name="name" placeholder="表示される名前"></td>
            </tr>
            <tr>
                <th>評価</th>
                <td>
                    <select name="evaluation">
                        <option value="">評価を選択</option>
                        <option value="5">★★★★★（5点）</option>
                        <option value="4">★★★★☆（4点）</option>
                        <option value="3">★★★☆☆（3点）</option>
                        <option value="2">★★☆☆☆（2点）</option>
                        <option value="1">★☆☆☆☆（1点）</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>感想</th>
                <td><textarea name="impressions" rows="5" cols="50" placeholder="感想を入力してください"></textarea></td>
            </tr>
            <tr>
                <th>読んだ巻数</th>
                <td><input type="text" name="number" placeholder="例：1-10巻、全巻"></td>
            </tr>
        </table>
        <input type="submit" value="投稿">
    </form>
    
    <p><a href="/miniApp/reviewList?mangaId=${mangaId}">レビュー一覧に戻る</a></p>
</body>
</html>