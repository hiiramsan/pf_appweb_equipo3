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
                        <input type="email" id="email" name="email" value="${param.email}" placeholder="example@mail.com" required autofocus>
                        <c:if test="${errorMap['email'] != null}">
                            <p style="color: red;">${errorMap['email']}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="first-name">First name</label>
                        <input type="text" id="first-name" name="first-name" value="${param['first-name']}" placeholder="Your first name" required>
                        <c:if test="${errorMap['first-name'] != null}">
                            <p style="color: red;">${errorMap['first-name']}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="last-name">Last name</label>
                        <input type="text" id="last-name" name="last-name" value="${param['last-name']}" placeholder="Your last name" required>
                        <c:if test="${errorMap['last-name'] != null}">
                            <p style="color: red;">${errorMap['last-name']}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" placeholder="Write a secret password" required>
                        <c:if test="${errorMap['password'] != null}">
                            <p style="color: red;">${errorMap['password']}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="confirm-password">Confirm password</label>
                        <input type="password" id="confirm-password" name="confirm-password" placeholder="Rewrite the password" required>
                        <c:if test="${errorMap['confirm-password'] != null}">
                            <p style="color: red;">${errorMap['confirm-password']}</p>
                        </c:if>
                    </div>
                </div>
                <div class="form-column">
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="tel" id="phone" name="phone" value="${param.phone}" placeholder="Your phone number" required>
                        <c:if test="${errorMap['phone'] != null}">
                            <p style="color: red;">${errorMap['phone']}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="city">City</label>
                        <input type="text" id="city" name="city" value="${param.city}" placeholder="Your city" required>
                        <c:if test="${errorMap['city'] != null}">
                            <p style="color: red;">${errorMap['city']}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="dob">Date of Birth</label>
                        <input type="date" id="dob" name="dob" value="${param.dob}" required>
                        <c:if test="${errorMap['dob'] != null}">
                            <p style="color: red;">${errorMap['dob']}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select id="gender" name="gender" required>
                            <option value="">Select gender</option>
                            <option value="MASCULINO" ${param.gender == 'MASCULINO' ? 'selected' : ''}>Male</option>
                            <option value="FEMENINO" ${param.gender == 'FEMENINO' ? 'selected' : ''}>Female</option>
                            <option value="NO_ESPECIFICADO" ${param.gender == 'NO_ESPECIFICADO' ? 'selected' : ''}>Prefiero no especificar</option>
                        </select>
                        <c:if test="${errorMap['gender'] != null}">
                            <p style="color: red;">${errorMap['gender']}</p>
                        </c:if>
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
