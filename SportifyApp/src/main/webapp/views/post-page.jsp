<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title id="tituloPost"></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/postPage.css">
    </head>
    <body>
        <%@ include file="../fragments/Header.jsp" %>

        <div class="content-wrapper">
            <div>
                <a href="${pageContext.request.contextPath}/home" class="back-button">Back to Posts</a>
                <input id="idPostActual" type="hidden" value="${postId}">
            </div>
            <main>
                <article>
                    <div class="article-info">
                        <div class="left">
                            <img id="avatarAutor" src="https://unavatar.io/hiiramsan" alt="">
                            <p>Posted by <a id="autorPost" href="">${post.autor.nombre}</a></p>
                            <p>• ${formattedDate}</p>
                        </div>

                    </div>
                    <h1 id="titulo-publicacion">${post.titulo}</h1>
                    <img src="${post.foto}" alt="" class="post-img">
                    <p id="contenidoPost">
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
                    <div id="contenedor-comentarios" class="comments-container">

                    </div>
                </section>
            </main>

        </div>


        <script>
            // URL del Servlet
            const idDelPost = document.getElementById("idPostActual");


            const urlPost = "postInfo?id=" + idDelPost.value;



            // Realizar la petición
            fetch(urlPost)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Error en la petición: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(post => {

                        const avatarAutor = document.getElementById("avatarAutor");
                        avatarAutor.src = post.autor.urlAvatar;

                        const tituloPost = document.getElementById("tituloPost");
                        tituloPost.textContent = post.titulo + " - Sportify";

                        const autorPost = document.getElementById("autorPost");
                        autorPost.textContent = post.autor.nombre;

                        const imagenPost = document.getElementsByClassName("post-img")[0];
                        imagenPost.src = post.foto;
                        
                        const tituloPublicacion = document.getElementById("titulo-publicacion");
                        tituloPublicacion.innerText = post.titulo;

                        const contenidoPost = document.getElementById("contenidoPost");
                        contenidoPost.textContent = post.contenido;

                        console.log(post.autor.nombre);
                        console.log(post.titulo);
                        console.log(post.isAnclado);
                    })
                    .catch(error => {
                        console.error("Hubo un error:", error);
                    });







            const urlComentarios = "commentsInfo?id=" + idDelPost.value;

            // Realizar la petición
            fetch(urlComentarios)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Error en la petición: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(data => {

                        const contenedorComentarios = document.getElementById("contenedor-comentarios");

                        data.forEach(comentario => {
                            // Crear comentario
                            const contenedorComentario = document.createElement("div");
                            contenedorComentario.classList.add("comment");

                            const encabezadoComentario = document.createElement("div");
                            encabezadoComentario.classList.add("comment-head");

                            const informacionEncabezado = document.createElement("div");
                            informacionEncabezado.classList.add("left");

                            const fotoComentario = document.createElement("img");
                            fotoComentario.src = comentario.usuario.urlAvatar;

                            const usuarioComentario = document.createElement("a");
                            usuarioComentario.textContent = comentario.usuario.nombre;

                            const contenidoComentario = document.createElement("div");
                            contenidoComentario.classList.add("comment-content");

                            const parrafo = document.createElement("p");
                            parrafo.textContent = comentario.contenido;

                            informacionEncabezado.appendChild(fotoComentario);
                            informacionEncabezado.appendChild(usuarioComentario);

                            encabezadoComentario.appendChild(informacionEncabezado);

                            contenedorComentario.appendChild(encabezadoComentario);

                            contenidoComentario.appendChild(parrafo);
                            contenedorComentario.appendChild(contenidoComentario);

                            contenedorComentarios.appendChild(contenedorComentario);

                        });
                    })
                    .catch(error => {
                        console.error("Hubo un error:", error);
                    });
        </script>
    </body>
</html>
