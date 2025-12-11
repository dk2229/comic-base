<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comic Base-レビュー削除確認</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>Comic Base</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <h2>レビュー削除確認</h2>
    <p>下記のレビューを削除してもよろしいですか？</p>
    
    <table border="1">
        <tr>
            <th>漫画名</th>
            <td>${review.name}</td>
        </tr>
        <tr>
            <th>評価</th>
            <td>
                <c:if test="${review.evaluation == '5'}">★★★★★</c:if>
                <c:if test="${review.evaluation == '4'}">★★★★☆</c:if>
                <c:if test="${review.evaluation == '3'}">★★★☆☆</c:if>
                <c:if test="${review.evaluation == '2'}">★★☆☆☆</c:if>
                <c:if test="${review.evaluation == '1'}">★☆☆☆☆</c:if>
                <c:if test="${review.evaluation != '1' && review.evaluation != '2' && review.evaluation != '3' && review.evaluation != '4' && review.evaluation != '5'}">
                    ${review.evaluation}
                </c:if>
            </td>
        </tr>
        <tr>
            <th>感想</th>
            <td>${review.impressions}</td>
        </tr>
        <tr>
            <th>巻数</th>
            <td>${review.number}</td>
        </tr>
        <tr>
            <th>投稿日時</th>
            <td>${review.createdAtStr}</td>
        </tr>
    </table>
    
    <form action="/miniApp/reviewDelete" method="post">
        <input type="hidden" name="reviewId" value="${review.reviewsId}">
        <input type="submit" value="削除する">
    </form>
    
    <p><a href="/miniApp/mypage">マイページに戻る</a></p>
</body>
</html>