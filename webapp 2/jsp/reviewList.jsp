<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>レビュー・感想</title>
    <link rel="stylesheet" type="text/css" href="/miniApp/css/review-list.css"/>
</head>
<body class="review-list-page">
    <h1>📖 ${manga.title}</h1>
    <p style="text-align: right">${user.name} 様ログイン中</p>
    <p style="text-align: right"><a href="/miniApp/logout">ログアウト</a></p>
    
    <p><strong>作者：</strong>${manga.author} | <strong>出版社：</strong>${manga.publisher}</p>
    
    <!-- ============================================ -->
    <!-- レビュー一覧セクション -->
    <!-- ============================================ -->
    <h2>📊 レビュー一覧（★評価付き）</h2>
    
    <p><a href="/miniApp/reviewAdd?mangaId=${manga.mangaId}">📝 レビューを投稿する</a></p>
    
    <c:if test="${reviewList.size() > 0}">
        <table border="1">
            <tr>
                <th>投稿者</th>
                <th>評価</th>
                <th>感想</th>
                <th>巻数</th>
                <th>投稿日時</th>
            </tr>
            <c:forEach var="review" items="${reviewList}">
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
                    <td>${review.impressions}</td>
                    <td>${review.number}</td>
                    <td>${review.createdAtStr}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    
    <c:if test="${reviewList.size() == 0}">
        <p>まだレビューが投稿されていません。</p>
    </c:if>
    
    <!-- ============================================ -->
    <!-- 区切り線 -->
    <!-- ============================================ -->
    <div class="section-divider">
        <h2>💬 掲示板（自由な会話・感想）</h2>
    </div>
    
    <!-- ============================================ -->
    <!-- 掲示板セクション -->
    <!-- ============================================ -->
    
    <!-- 掲示板投稿一覧 -->
   <c:if test="${boardPostList.size() > 0}">
    <c:forEach var="post" items="${boardPostList}">
        <div class="board-post">
            <div class="post-header">
                ${post.postNumber}. ${post.name} ${post.createdAtStr}
                <a href="/miniApp/reviewList?mangaId=${manga.mangaId}&replyTo=${post.postNumber}" class="reply-link">[返信]</a>
            </div>
            <div class="post-content">
                ${post.content}
            </div>
        </div>
    </c:forEach>
	</c:if>
	
    <c:if test="${boardPostList.size() == 0}">
        <div class="board-post">
            <div class="post-content">
                まだ書き込みがありません。最初の書き込みをしてみませんか？
            </div>
        </div>
    </c:if>
    
    <!-- 掲示板投稿フォーム -->
    <div class="board-form">
        <h3>📝 掲示板に書き込む</h3>
        <form action="/miniApp/boardPost" method="post">
            <input type="hidden" name="mangaId" value="${manga.mangaId}">
            <table>
             <tr>
                 <th>名前</th>
                 <td><input type="text" name="name" placeholder="名無しさん（空欄可）" style="width: 200px;"></td>
             </tr>
                <tr>
    			<th>書き込み</th>
    			<td><textarea name="content" rows="4" cols="60" placeholder="自由に書き込んでください。>>1 のようにアンカーも使えます。"><c:if test="${replyTo != null}">>>${replyTo}
			</c:if></textarea></td>
			</tr>
            </table>
            <input type="submit" value="💬 書き込む">
        </form>
    </div>
    
    <!-- ============================================ -->
    <!-- ナビゲーションリンク -->
    <!-- ============================================ -->
    <p><a href="/miniApp/mangaList">📚 漫画一覧に戻る</a></p>
    <p><a href="/miniApp/characterDictionary?mangaId=${manga.mangaId}">👥 キャラクター図鑑</a></p>
</body>
</html>