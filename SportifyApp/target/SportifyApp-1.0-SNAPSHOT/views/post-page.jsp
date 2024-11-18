<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${post.titulo} - Sportify</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/postPage.css"/>
    </head>
    <body>
        <%@ include file="../fragments/Header.jsp" %>
        
        <div class="content-wrapper">
            <main class="post-detail">
                <article class="post">
                    <div class="post-header">
                        <h1>${post.titulo}</h1>
                        <div class="post-meta">
                            <div class="author">
                                <img src="https://cdn.nba.com/manage/2022/03/lebron030522.jpg" alt="sport" />
                                <span>${post.autor.nombre}</span>
                            </div>
                            <fmt:formatDate value="${post.fechaHoraCreacion.time}" pattern="dd MMM, yyyy" var="formattedDate" />
                            <span class="date">${formattedDate}</span>
                        </div>
                    </div>
                    
                    <div class="post-content">
                        ${post.contenido}
                    </div>
                </article>
                <section class="comments">
                    <h2>Comments (${comentarios.size()})</h2>
                    
                    <c:if test="${not empty sessionScope.usuario}">
                        <form class="comment-form" action="${pageContext.request.contextPath}/postpage" method="POST">
                            <input type="hidden" name="postId" value="${post.idPost}">
                            <textarea name="comentario" placeholder="Write a comment..." required></textarea>
                            <button type="submit">Post Comment</button>
                        </form>
                    </c:if>
                    
                    <div class="comments-list">
                        <c:forEach var="comentario" items="${comentarios}">
                            <div class="comment">
                                <div class="comment-header">
                                    <div class="author">
                                        <img src="https://cdn.nba.com/manage/2022/03/lebron030522.jpg" alt="av" />
                                        <span>${comentario.usuario.nombre}</span>
                                    </div>
                                    <fmt:formatDate value="${comentario.fechaHora.time}" pattern="dd MMM, yyyy" var="commentDate" />
                                    <span class="date">${commentDate}</span>
                                </div>
                                <p class="comment-content">${comentario.contenido}</p>
                            </div>
                        </c:forEach>
                        
                        <c:if test="${empty comentarios}">
                            <p class="no-comments">No comments yet. Be the first to comment!</p>
                        </c:if>
                    </div>
                </section>
            </main>
            
            <aside>
                <a href="${pageContext.request.contextPath}/home" class="back-button">
                    Back to Posts
                </a>
            </aside>
        </div>
    </body>
</html>