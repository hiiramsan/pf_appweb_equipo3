<%-- 
    Document   : create-post
    Created on : Nov 17, 2024, 10:10:57 AM
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Post</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/create-post.css"/>
    </head>
    <body>
        <%@ include file="../fragments/Header.jsp" %>
        <div class="top">
            <a href="./index.jsp">
                <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path fill-rule="evenodd" clip-rule="evenodd" d="M11.7071 4.29289C12.0976 4.68342 12.0976 5.31658 11.7071 5.70711L6.41421 11H20C20.5523 11 21 11.4477 21 12C21 12.5523 20.5523 13 20 13H6.41421L11.7071 18.2929C12.0976 18.6834 12.0976 19.3166 11.7071 19.7071C11.3166 20.0976 10.6834 20.0976 10.2929 19.7071L3.29289 12.7071C3.10536 12.5196 3 12.2652 3 12C3 11.7348 3.10536 11.4804 3.29289 11.2929L10.2929 4.29289C10.6834 3.90237 11.3166 3.90237 11.7071 4.29289Z" fill="#ffffff"></path> </g></svg>
            </a>
            <h1>Create new post</h1>
            <div class="div"></div>
        </div>
        <main>
            <form action="${pageContext.request.contextPath}/posts" method="POST" onsubmit="console.log('Form submitting...');" enctype="multipart/form-data">
                <div class="option-btns">
                    <div class="select-wrapper">
                        <select name="sport" id="sport" required>
                            <option value="" disabled selected>Select a sport</option>
                            <option value="soccer">⚽ Soccer</option>
                            <option value="basketball">🏀 Basketball</option>
                            <option value="baseball">⚾ Baseball</option>
                            <option value="american-football">🏈 American Football</option>
                            <option value="tennis">🎾 Tennis</option>
                            <option value="volleyball">🏐 Volleyball</option>
                        </select>
                    </div>

                    <label>Upload Image:</label>
                    <input type="file" name="image" id="image" accept="image/*">
                </div>
                <div class="entry">
                    <input type="text" name="title" id="title" placeholder="Title..." required>
                    <textarea name="content" id="content" placeholder="Write here..." rows="20" required></textarea>
                </div>
                <button class="option" id="post" type="submit">
                    Post
                </button>
            </form>
        </main>
                
            <script>
                // JavaScript to handle the form submission using Fetch API
                document.getElementById("postForm").addEventListener("submit", function(event) {
                    event.preventDefault(); // Prevent default form submission

                    // Prepare FormData
                    const formData = new FormData();
                    formData.append("title", document.getElementById("title").value);
                    formData.append("content", document.getElementById("content").value);
                    formData.append("sport", document.getElementById("sport").value);
                    formData.append("image", document.getElementById("image").files[0]);

                    // Use Fetch API to send data to the server
                    fetch("${pageContext.request.contextPath}/posts", {
                        method: "POST",
                        body: formData
                    })
                    .then(response => response.json()) // Expect JSON response
                    .then(data => {
                        if (data.success) {
                            alert("Post created successfully!");
                            // Optionally, redirect or clear the form
                        } else {
                            alert("Error creating post: " + data.message);
                        }
                    })
                    .catch(error => {
                        console.error("Error:", error);
                        alert("An error occurred while creating the post.");
                    });
                });
            </script>
    </body>
</html>
