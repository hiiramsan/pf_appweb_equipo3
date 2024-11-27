<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${post.titulo} - Sportify</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/postPage.css">
    </head>
    <body>
        <%@ include file="../fragments/Header.jsp" %>

        <div class="content-wrapper">
            <div>
                <a href="${pageContext.request.contextPath}/home" class="back-button">Back to Posts</a>
            </div>
            <main>
                <article>
                    <div class="article-info">
                        <div class="left">
                            <img src="https://unavatar.io/hiiramsan" alt="">
                            <p>Posted by <a href="">${post.autor.nombre}</a></p>
                            <p>• ${formattedDate}</p>
                        </div>

                    </div>
                    <h1>${post.titulo}</h1>
                    <img src="${post.foto}" alt="" class="post-img">
                    <p>
                        ${post.contenido} 
                    </p>

                </article>
                <section class="comments">
                    <h2>Comments (${comentarios.size()})</h2>
                    <c:if test="${not empty sessionScope.usuario}">
                    <form action="${pageContext.request.contextPath}/postpage" method="POST">
                        <textarea class="comment-sender" name="comment-sender" id="comment-sender" placeholder="Leave a comment..." rows="5"></textarea>
                        <button type="submit">
                            <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" id="send-alt" class="icon glyph" fill="#ffffff"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M21.88,4.73,16.2,20.65A2,2,0,0,1,14.3,22h0a2,2,0,0,1-1.9-1.31l-2.12-5.52,1.54-1.54,2.49-2.49a1,1,0,1,0-1.42-1.42l-2.49,2.49L8.82,13.76,3.31,11.63a2,2,0,0,1,0-3.83L19.27,2.12a2,2,0,0,1,2.61,2.61Z"></path></g></svg>
                        </button>
                    </form>
                    
                    </c:if>
                    <div class="comments-container">
                        <c:forEach var="comentario" items="${comentarios}">
                        <div class="comment">
                            <div class="comment-head">
                                <div class="left">
                                    <img src=${comentario.usuario.foto} alt="">
                                    <p><a href="">${comentario.contenido}</a> commented...</p>
                                </div>
                                <div></div>
                            </div>
                            <div class="comment-content">
                                <p></p>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </section>
            </main>

        </div>
    </body>
</html>
