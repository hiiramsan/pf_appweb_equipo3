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
                        <c:forEach var="post" items="${posts}">
                           <article class="post normal-post">
                <header>
                    <img src="${post.autor.urlAvatar}" alt="pfp" />
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
                        <img src="${pageContext.request.contextPath}/post_images/${post.foto}" alt="img">
                    </div>
                </section>
                <div class="stats-container">
                    <div class="stat">
                        <svg viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M1.24264 8.24264L8 15L14.7574 8.24264C15.553 7.44699 16 6.36786 16 5.24264V5.05234C16 2.8143 14.1857 1 11.9477 1C10.7166 1 9.55233 1.55959 8.78331 2.52086L8 3.5L7.21669 2.52086C6.44767 1.55959 5.28338 1 4.05234 1C1.8143 1 0 2.8143 0 5.05234V5.24264C0 6.36786 0.44699 7.44699 1.24264 8.24264Z" fill="#ffffff"></path> </g></svg>
                        <p>23</p>
                    </div>
                    <div class="stat">
                        <svg fill="#000000" viewBox="0 0 1920 1920" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M1662.178 0v1359.964h-648.703l-560.154 560.154v-560.154H0V0h1662.178ZM906.794 755.55H453.32v117.53h453.473V755.55Zm302.063-302.365H453.32v117.529h755.536V453.185Z" fill-rule="evenodd"></path> </g></svg>
                        <p>3</p>
                    </div>
                    <div class="stat sport">
                        <svg
                        viewBox="0 0 48 48"
                        xmlns="http://www.w3.org/2000/svg"
                        fill="#0000000"
                        stroke="#0000000"
                      >
                        <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                        <g
                          id="SVGRepo_tracerCarrier"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        ></g>
                        <g id="SVGRepo_iconCarrier">
                          <title>sports-soccer</title>
                          <g id="Layer_2" data-name="Layer 2">
                            <g id="invisible_box" data-name="invisible box">
                              <rect width="48" height="48" fill="none"></rect>
                            </g>
                            <g id="Q3_icons" data-name="Q3 icons">
                              <g>
                                <polygon
                                  points="17.5 21.6 20 29 28 29 30.5 21.6 24 17 17.5 21.6"
                                ></polygon>
                                <path
                                  d="M45.5,19.1A22.1,22.1,0,0,0,24,2a21.2,21.2,0,0,0-4.9.6A22,22,0,0,0,24,46a28.1,28.1,0,0,0,4.9-.5A22.1,22.1,0,0,0,45.5,19.1Zm-7,15.6-1.1-3.3H29.5l-2.6,7.6,2.8,2-1.7.5a18.1,18.1,0,0,1-4,.4,17.9,17.9,0,0,1-5.7-.9l2.8-2-2.6-7.6H10.6L9.5,34.7a17,17,0,0,1-3-6.7A14.8,14.8,0,0,1,6,23.9l2.8,2,6.3-4.8-2.3-7.6H9.4a18.3,18.3,0,0,1,9.2-6.6l-1.1,3.2L24,14.7l6.5-4.6L29.4,6.8a18.6,18.6,0,0,1,9.3,6.7H35.2l-2.3,7.6,6.3,4.8L42,23.8A18.9,18.9,0,0,1,38.5,34.7Z"
                                ></path>
                              </g>
                            </g>
                          </g>
                        </g>
                      </svg>
                    </div>
                  </div>
            </article>
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
                                Real Madrid just lost 0-1 against Lille. Is this something to
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
    </body>
</html>
