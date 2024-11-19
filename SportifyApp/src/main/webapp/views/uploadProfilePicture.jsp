<%-- 
    Document   : uploadProfilePicture
    Created on : Nov 18, 2024, 9:01:06 PM
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload a picture</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/pictureUploader.css"/>
    </head>
    <body>
        <h1>Upload a Profile Picture</h1>
        <form action="${pageContext.request.contextPath}/uploadProfilePicture" method="post" enctype="multipart/form-data">
            <label for="profilePicture">Choose a profile picture:</label>
            <input type="file" name="profilePicture" id="profilePicture" accept="image/*" required>
            <button type="submit" id="upload">Upload</button>
        </form>
        <form action="${pageContext.request.contextPath}/home" method="get">
            <button type="submit" id="later">Do it later</button>
        </form>
    </body>
</html>
