
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.socialnetwork.model.Message" %>

<%
  List<Message> conv = (List<Message>) request.getAttribute("conv");
  Integer userId = (Integer) session.getAttribute("userId");
%>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="page-center">
    <div class="page-card">

        <div class="page-title">Conversation</div>

        <div class="chat-area">

        <% if (conv == null || conv.isEmpty()) { %>

            <p style="text-align:center; color:#666;">No messages</p>

        <% } else {
            for (Message m : conv) {
                boolean mine = (m.getSenderId() == userId);
        %>

            <div class="chat-bubble <%= mine ? "chat-me" : "chat-them" %>">
                <b><%= mine ? "You" : "User "+m.getSenderId() %></b><br>
                <%= m.getMessageText() %><br>
                <small style="font-size:12px; color:#555;"><%= m.getSentAt() %></small>
            </div>

        <% } } %>

        </div>

        <a href="<%= request.getContextPath() %>/home.jsp"><button class="auth-btn">Back</button></a>

    </div>
</div>

</body>
</html>
