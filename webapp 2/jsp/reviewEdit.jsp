<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comic Base-レビュー編集</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/stylesheet.css"/>
</head>
<body>
    <h1>Comic Base</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <h2>レビュー編集</h2>
    
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
    
    <form action="/miniApp/reviewEdit" method="post">
        <input type="hidden" name="reviewId" value="${review.reviewsId}">
        <table border="1">
            <tr>
                <th>漫画名</th>
                <td><input type="text" name="name" value="${review.name}" placeholder="漫画名を入力"></td>
            </tr>
            <tr>
                <th>評価</th>
                <td>
                    <select name="evaluation">
                        <c:if test="${review.evaluation == '5'}">
                            <option value="5" selected>★★★★★ (5)</option>
                        </c:if>
                        <c:if test="${review.evaluation != '5'}">
                            <option value="5">★★★★★ (5)</option>
                        </c:if>
                        
                        <c:if test="${review.evaluation == '4'}">
                            <option value="4" selected>★★★★☆ (4)</option>
                        </c:if>
                        <c:if test="${review.evaluation != '4'}">
                            <option value="4">★★★★☆ (4)</option>
                        </c:if>
                        
                        <c:if test="${review.evaluation == '3'}">
                            <option value="3" selected>★★★☆☆ (3)</option>
                        </c:if>
                        <c:if test="${review.evaluation != '3'}">
                            <option value="3">★★★☆☆ (3)</option>
                        </c:if>
                        
                        <c:if test="${review.evaluation == '2'}">
                            <option value="2" selected>★★☆☆☆ (2)</option>
                        </c:if>
                        <c:if test="${review.evaluation != '2'}">
                            <option value="2">★★☆☆☆ (2)</option>
                        </c:if>
                        
                        <c:if test="${review.evaluation == '1'}">
                            <option value="1" selected>★☆☆☆☆ (1)</option>
                        </c:if>
                        <c:if test="${review.evaluation != '1'}">
                            <option value="1">★☆☆☆☆ (1)</option>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <th>感想</th>
                <td><textarea name="impressions" rows="5" cols="50" placeholder="感想を入力">${review.impressions}</textarea></td>
            </tr>
            <tr>
                <th>巻数</th>
                <td><input type="text" name="number" value="${review.number}" placeholder="巻数を入力（任意）"></td>
            </tr>
        </table>
        <input type="submit" value="更新">
    </form>
    
    <p><a href="/miniApp/mypage">マイページに戻る</a></p>
</body>
</html>