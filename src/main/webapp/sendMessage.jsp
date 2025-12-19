<%@ page contentType="text/html;charset=UTF-8" %>
<%
  Integer receiverId = Integer.parseInt(request.getParameter("receiverId").trim());
%>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="page-center">
    <div class="page-card">

        <div class="page-title">Send Message</div>

        <form action="${pageContext.request.contextPath}/message/send" method="post">

            <input type="hidden" name="toId" value="<%=receiverId%>"/>

            <textarea class="auth-input" name="text" rows="4" placeholder="Type your message..." required></textarea>

            <button class="auth-btn">Send</button>
        </form>

        <div class="auth-link" style="margin-top:15px;">
            <a href="<%= request.getContextPath() %>/home.jsp">Back to Home</a>
        </div>

    </div>
</div>

</body>
</html>
