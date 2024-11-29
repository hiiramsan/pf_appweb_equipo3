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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/signup.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Create your Sport<span>ify</span> account</h1>
            <form action="${pageContext.request.contextPath}/registro" method="POST">
                <div class="form-column">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <c:if test="${not empty errorMap['email']}">
                            <p class="error">${errorMap['email']}</p>
                        </c:if>
                        <input type="email" id="email" name="email" value="${param.email}" placeholder="example@mail.com" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="first-name">First name</label>
                        <c:if test="${not empty errorMap['first-name']}">
                            <p class="error">${errorMap['first-name']}</p>
                        </c:if>
                        <input type="text" id="first-name" name="first-name" value="${param['first-name']}" placeholder="Your first name" required>
                    </div>
                    <div class="form-group">
                        <label for="last-name">Last name</label>
                        <c:if test="${not empty errorMap['last-name']}">
                            <p class="error">${errorMap['last-name']}</p>
                        </c:if>
                        <input type="text" id="last-name" name="last-name" value="${param['last-name']}" placeholder="Your last name" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <c:if test="${not empty errorMap['password']}">
                            <p class="error">${errorMap['password']}</p>
                        </c:if>
                        <input type="password" id="password" name="password" placeholder="Write a secret password" required>
                    </div>
                    <div class="form-group">
                        <label for="confirm-password">Confirm password</label>
                        <c:if test="${not empty errorMap['confirm-password']}">
                            <p class="error">${errorMap['confirm-password']}</p>
                        </c:if>
                        <input type="password" id="confirm-password" name="confirm-password" placeholder="Rewrite the password" required>
                    </div>
                </div>
                <div class="form-column">
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <c:if test="${not empty errorMap['phone']}">
                            <p class="error">${errorMap['phone']}</p>
                        </c:if>
                        <input type="tel" id="phone" name="phone" value="${param.phone}" placeholder="Your phone number" required>
                    </div>
                    <div class="form-group">
                        <label for="city">City</label>
                        <c:if test="${not empty errorMap['city']}">
                            <p class="error">${errorMap['city']}</p>
                        </c:if>
                        <input type="text" id="city" name="city" value="${param.city}" placeholder="Your city" required>
                    </div>
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <c:if test="${not empty errorMap['dob']}">
                            <p class="error">${errorMap['dob']}</p>
                        </c:if>
                        <input type="date" id="dob" name="dob" value="${param.dob}" required>
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <c:if test="${not empty errorMap['gender']}">
                            <p class="error">${errorMap['gender']}</p>
                        </c:if>
                        <select id="gender" name="gender" required>
                            <option value="">Select gender</option>
                            <option value="MASCULINO" ${param.gender == 'MASCULINO' ? 'selected' : ''}>Male</option>
                            <option value="FEMENINO" ${param.gender == 'FEMENINO' ? 'selected' : ''}>Female</option>
                            <option value="NO_ESPECIFICADO" ${param.gender == 'NO_ESPECIFICADO' ? 'selected' : ''}>Prefiero no especificar</option>
                        </select>
                    </div>
                </div>
                <div class="submit-container">
                    <input type="submit" value="Create Account">
                    <p>Have an account already? <a href="login.jsp">Log in</a></p>
                </div>
            </form>
        </div>
    </body>
</html>
