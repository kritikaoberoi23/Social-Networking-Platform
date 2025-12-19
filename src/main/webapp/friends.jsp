<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>

<%
    List<Integer> friends = (List<Integer>) request.getAttribute("friends");
%>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="page-center">
    <div class="page-card">

        <div class="page-title">Your Friends</div>

        <% if (friends == null || friends.isEmpty()) { %>

            <p style="text-align:center; color:#666;">No friends found.</p>

        <% } else { %>

            <% for(Integer f : friends) { %>
                <div class="list-item">
                    User ID: <b><%= f %></b>
                </div>
            <% } %>

        <% } %>

        <a href="<%= request.getContextPath() %>/home.jsp"><button class="auth-btn" style="margin-top:20px;">Back</button></a>

    </div>
</div>

</body>
</html>
