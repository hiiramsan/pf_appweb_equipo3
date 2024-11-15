<%-- 
    Document   : signup
    Created on : Nov 15, 2024, 11:56:08 AM
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up - Sportify</title>
        <<link rel="stylesheet" href="styles/signup.css"/>
    </head>     
    <body>
        <div class="container">
            <h1>Create your Sport<span>ify</span> account</h1>
            <form action="">
                <div class="form-column">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" placeholder="example@mail.com" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="name">Full name</label>
                        <input type="text" id="name" placeholder="Your full name" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" placeholder="Write a secret password" required>
                    </div>
                    <div class="form-group">
                        <label for="confirm-password">Confirm password</label>
                        <input type="password" id="confirm-password" placeholder="Rewrite the password" required>
                    </div>
                </div>
                <div class="form-column">
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="tel" id="phone" placeholder="Your phone number"  required>
                    </div>
                    <div class="form-group">
                        <label for="city">City</label>
                        <input type="text" id="city" placeholder="Your city" required>
                    </div>
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" required>
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select id="gender" required>
                            <option value="">Select gender</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="avatar">Avatar</label>
                        <input type="file" id="avatar" accept="image/*">
                    </div>
                </div>
                <div class="submit-container">
                    <input type="submit" value="Create Account">
                    <p>Have an account already? <a href="login.html">Log in</a></p>
                </div>

            </form>
        </div>
    </body>
</html>
