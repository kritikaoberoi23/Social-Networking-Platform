<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.socialnetwork.model.Post" %>

<%
    List<Post> posts = (List<Post>) request.getAttribute("posts");
%>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="page-center">
    <div class="page-card">

        <div class="page-title">Posts</div>

        <% if (posts == null || posts.isEmpty()) { %>

            <p style="text-align:center; color:#666;">No posts found.</p>

        <% } else {
            for(Post p : posts) {
        %>

        <div class="mini-post">
            <b>Post #<%= p.getPostId() %></b><br>
            <small style="color:#777;"><%= p.getCreatedAt() %></small>
            <p style="margin-top:10px;"><%= p.getContent() %></p>
        </div>

        <% }} %>

        <a href="<%= request.getContextPath() %>/home.jsp"><button class="auth-btn" style="margin-top:20px;">Back</button></a>

    </div>
</div>

</body>
</html>


