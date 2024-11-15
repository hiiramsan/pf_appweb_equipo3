<%-- 
    Document   : login
    Created on : Nov 15, 2024, 12:02:33 PM
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login = Sportify</title>
        <link rel="stylesheet" href="styles/login.css"/>
        <<link rel="stylesheet" href="styles/style.css"/>
    </head>
    <body>
        <div  class="contenedor01">
            <section  class="titulo">
                <img src="./img/sportify-logo.png" alt="">
                <p>Welcome back!</p>
                <h1>Log in</h1>
            </section>


            <form action="">
                <label for="email">Email</label>
                <input type="email" placeholder="example@mail.com" required autofocus>

                <label for="password">Pasword</label>
                <input type="password" placeholder="Write a secret password" required>

                <input type="submit" value="Log in">
                <p>Don’t have an account? <a href="registro.html">Sign Up, It’s Free!</a></p>
            </form>

        </div>
        <div class="contenedor02">
            <img src="./img/wp1936496.jpg" alt="">
            <p>The place for <span>sports</span>, the place for <span>you</span></p>
        </div>
    </body>
</html>
