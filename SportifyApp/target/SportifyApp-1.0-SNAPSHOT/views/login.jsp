<%-- 
    Document   : login
    Created on : Nov 15, 2024, 12:02:33 PM
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Sportify</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css">
    </head>
    <body>
        <div  class="contenedor01">
            <section  class="titulo">
                <img src="./img/sportify-logo.png" alt="">
                <p>Welcome back!</p>
                <h1>Log in</h1>
            </section>

            <c:if test="${param.error eq 'true'}">
                <p style="color: red;">Email o contraseña incorrectos. Por favor, inténtalo de nuevo.</p>
            </c:if>
            <form action="${pageContext.request.contextPath}/login" method="POST">
                <label for="email">Email</label>
                <input type="email" placeholder="your email" name="email" required autofocus>

                <label for="password">Password</label>
                <input type="password" placeholder="Write a secret password" name="password" required>

                <input type="submit" value="Log in">
                <p>Don’t have an account? <a href="./signup.jsp">Sign Up, It’s Free!</a></p>
            </form>
        </div>
        <div class="contenedor02">

            <img src="${pageContext.request.contextPath}/img/wp1936496.png" alt="">
            <p>The place for <span>sports</span>, the place for <span>you</span></p>
        </div>
    </body>
</html>