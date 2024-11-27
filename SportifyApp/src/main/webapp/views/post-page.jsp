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
            </main>
            <aside>
                <a href="${pageContext.request.contextPath}/home" class="back-button">Back to Posts</a>
            </aside>
        </div>
    </body>
</html>
