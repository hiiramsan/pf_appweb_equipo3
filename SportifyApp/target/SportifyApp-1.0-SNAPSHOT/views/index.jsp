<%-- 
    Document   : index
    Created on : Nov 15, 2024, 11:42:52 AM
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
    </head>
    <body>
        <%@ include file="../fragments/Header.jsp" %>
        <div class="content-wrapper">
            <main>
                <c:if test="${not empty posts}">
                    <div class="posts">
                        <div id="contenedor-posts-anclados">

                        </div>
                        <hr>
                        <br>

                        <c:forEach var="post" items="${posts}">
                            <c:if test="${usuario.rol == 'ADMIN'}">
                                <button class="delete-post-button" data-post-id="${post.idPost}">Eliminar post</button>
                                <button class="boton-anclar" data-anclar-id="${post.idPost}">Fijar</button>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/postpage?id=${post.idPost}">
                                <article class="post normal-post">
                                    <header>
                                        <img src="${post.autor.urlAvatar}" alt="Profile Picture" />
                                        <p>${post.autor.nombre}</p>
                                        <p class="light-gray">• <fmt:formatDate value="${post.fechaHoraCreacion.time}" pattern="dd/MM/yyyy"/></p>

                                    </header>
                                    <section class="content">
                                        <div class="left">
                                            <h2>${post.titulo}</h2>
                                            <p class="light-gray">
                                                ${post.contenido}
                                            </p>
                                        </div>
                                        <div class="right">
                                            <img src="${post.foto}" alt="img post" />
                                        </div>
                                    </section>
                                </article>
                            </a>
                            <br>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty posts}">
                    <p>No hay posts disponibles en este momento.</p>
                </c:if>
            </main>
            <aside>
                <a class="create-post" href="${pageContext.request.contextPath}/views/create-post.jsp">
                    <svg
                        fill="#000000"
                        viewBox="0 0 512 512"
                        xmlns="http://www.w3.org/2000/svg"
                        >
                    <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                    <g
                        id="SVGRepo_tracerCarrier"
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        ></g>
                    <g id="SVGRepo_iconCarrier">
                    <title>ionicons-v5-n</title>
                    <path
                        d="M459.94,53.25a16.06,16.06,0,0,0-23.22-.56L424.35,65a8,8,0,0,0,0,11.31l11.34,11.32a8,8,0,0,0,11.34,0l12.06-12C465.19,69.54,465.76,59.62,459.94,53.25Z"
                        ></path>
                    <path
                        d="M399.34,90,218.82,270.2a9,9,0,0,0-2.31,3.93L208.16,299a3.91,3.91,0,0,0,4.86,4.86l24.85-8.35a9,9,0,0,0,3.93-2.31L422,112.66A9,9,0,0,0,422,100L412.05,90A9,9,0,0,0,399.34,90Z"
                        ></path>
                    <path
                        d="M386.34,193.66,264.45,315.79A41.08,41.08,0,0,1,247.58,326l-25.9,8.67a35.92,35.92,0,0,1-44.33-44.33l8.67-25.9a41.08,41.08,0,0,1,10.19-16.87L318.34,125.66A8,8,0,0,0,312.69,112H104a56,56,0,0,0-56,56V408a56,56,0,0,0,56,56H344a56,56,0,0,0,56-56V199.31A8,8,0,0,0,386.34,193.66Z"
                        ></path>
                    </g>
                    </svg>
                    Create new post
                </a>
                <div class="weekly-picks">
                    <h3>This week picks</h3>
                    <a href="">
                        <article>
                            <div class="header">
                                <img src="https://unavatar.io/jack" alt="" />
                                <p>JackFR</p>
                            </div>
                            <h4>
                                Real Madrid just lost 0-1 against Lille. Is this something to
                                wrorry about?
                            </h4>
                        </article>
                    </a>
                    <a href="">
                        <article>
                            <div class="header">
                                <img src="https://unavatar.io/lolman" alt="" />
                                <p>lolman123</p>
                            </div>
                            <h4>
                                Madrid just lost 0-1 against Lille. Is this something to
                                wrorry about?
                            </h4>
                        </article>
                    </a>

                    <a href="">
                        <article>
                            <div class="header">
                                <img src="https://unavatar.io/edtarsz" alt="" />
                                <p>edtarsz</p>
                            </div>
                            <h4>
                                Real Madrid just lost 0-1 against Lille. Is this something to
                                wrorry about?
                            </h4>
                        </article>
                    </a>
                </div>
            </aside>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {

                // PETICIÓN PARA MOSTRAR EN PANTALLA LOS POSTS ANCLADOS POR EL ADMINISTRADOR
                fetch("postsAnclados")
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Error en la petición: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then(data => {
                        const postsAnclados = document.getElementById("contenedor-posts-anclados");

                        data.forEach(postAnclado => {
                            const enlacePost = document.createElement("a");
                            const idPostAnclado = postAnclado.idPost;
                            enlacePost.href = "${pageContext.request.contextPath}/postpage?id=" + idPostAnclado;
                            postsAnclados.appendChild(enlacePost);

                            const articulo = document.createElement("article");
                            articulo.classList.add("post", "normal-post");
                            enlacePost.appendChild(articulo);

                            const encabezado = document.createElement("header");
                            articulo.appendChild(encabezado);
                            const avatarUsuario = document.createElement("img");
                            avatarUsuario.src = postAnclado.autor.urlAvatar;
                            encabezado.appendChild(avatarUsuario);

                            const nombreAutor = document.createElement("p");
                            nombreAutor.textContent = postAnclado.autor.nombre;
                            encabezado.appendChild(nombreAutor);

                            const fechaPublicacion = document.createElement("p");
                            const { dayOfMonth, month, year } = postAnclado.fechaHoraCreacion;
                            fechaPublicacion.textContent = `• ${dayOfMonth}/${month}/${year}`;
                            fechaPublicacion.classList.add("light-gray");
                            encabezado.append(fechaPublicacion);

                            const indiceAnclado = document.createElement("p");
                            indiceAnclado.textContent = "ANCLADO";
                            encabezado.appendChild(indiceAnclado);

                            const seccion = document.createElement("section");
                            seccion.classList.add("content");
                            articulo.appendChild(seccion);

                            const contenidoIzquierdo = document.createElement("div");
                            contenidoIzquierdo.classList.add("left");
                            seccion.appendChild(contenidoIzquierdo);

                            const tituloPostAnclado = document.createElement("h2");
                            tituloPostAnclado.innerText = postAnclado.titulo;
                            contenidoIzquierdo.appendChild(tituloPostAnclado);

                            const parrafoPost = document.createElement("p");
                            parrafoPost.classList.add("light-gray");
                            parrafoPost.textContent = postAnclado.contenido;
                            contenidoIzquierdo.appendChild(parrafoPost);

                            const contenidoDerecho = document.createElement("div");
                            contenidoDerecho.classList.add("right");
                            seccion.appendChild(contenidoDerecho);

                            const imagenPostAnclado = document.createElement("img");
                            imagenPostAnclado.src = postAnclado.foto;
                            contenidoDerecho.appendChild(imagenPostAnclado);
                        });
                    })
                    .catch(error => {
                        console.error("Hubo un error:", error);
                    });

                // FUNCIONALIDAD PARA ANCLAR UN POST
                const botonesAnclarPosts = document.getElementsByClassName("boton-anclar");
                for (let botonAnclar of botonesAnclarPosts) {
                    botonAnclar.addEventListener("click", anclarPost);
                }

                // Función para anclar un post
                function anclarPost(evento) {
                    const jsonData = { postPorAnclarId: evento.target.dataset.anclarId };

                    fetch('postsAnclados', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(jsonData)
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Respuesta del servidor:', data);
                        alert("Datos enviados exitosamente");
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
                }

                // Funcionalidad para eliminar un post
                const deleteButtons = document.querySelectorAll(".delete-post-button");
                deleteButtons.forEach(button => {
                    button.addEventListener("click", function () {
                        const postId = this.getAttribute("data-post-id");

                        if (confirm("¿Estás seguro de que deseas eliminar este post?")) {
                            fetch(`${pageContext.request.contextPath}/principal`, {
                                method: "POST",
                                headers: {
                                    "Content-Type": "application/json"
                                },
                                body: JSON.stringify({
                                    action: "delete", 
                                    id: postId
                                })
                            })
                            .then(response => response.json())
                            .then(data => {
                                if (data.status === "success") {
                                    alert("Post eliminado correctamente.");
                                    location.reload(); // Recargar la página para reflejar los cambios
                                } else {
                                    alert(data.message);
                                }
                            })
                            .catch(error => {
                                console.error("Error:", error);
                                alert("Ocurrió un error.");
                            });
                        }
                    });
                });
            });
        </script>
    </body>
</html>
