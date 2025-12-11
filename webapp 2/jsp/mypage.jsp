<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comic base-マイページ</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>Comic base</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <h2>📝 マイページ - ${user.name}さんのレビュー</h2>
    
    <c:if test="${userReviews.size() > 0}">
        <p>投稿したレビュー数: ${userReviews.size()}件</p>
        
        <table border="1">
            <tr>
                <th>漫画</th>
                <th>評価</th>
                <th>感想</th>
                <th>巻数</th>
                <th>投稿日時</th>
                <th>操作</th>
            </tr>
            <c:forEach var="review" items="${userReviews}">
                <tr>
                    <td>${review.name}</td>
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
                    <td>
                        <c:if test="${review.impressions.length() > 50}">
                            ${review.impressions.substring(0, 50)}...
                        </c:if>
                        <c:if test="${review.impressions.length() <= 50}">
                            ${review.impressions}
                        </c:if>
                    </td>
                    <td>${review.number}</td>
                    <td>${review.createdAtStr}</td>
                    <td>
                        <a href="/miniApp/reviewEdit?reviewId=${review.reviewsId}">編集</a> |
                        <a href="/miniApp/reviewDelete?reviewId=${review.reviewsId}" 
                           onclick="return confirm('このレビューを削除してもよろしいですか？')">削除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    
    <c:if test="${userReviews.size() == 0}">
        <p>まだレビューを投稿していません。</p>
        <p><a href="/miniApp/mangaList">漫画一覧からレビューを投稿してみましょう</a></p>
    </c:if>
    
    <h2>🔗 メニュー</h2>
    <p><a href="/miniApp/mangaList">漫画一覧に戻る</a></p>
    <p><a href="/miniApp/jsp/home.jsp">ホーム画面に戻る</a></p>
</body>
</html>