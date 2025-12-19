<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="auth-page">
    <div class="auth-card">

        <div class="auth-title">Welcome</div>

        <a href="signup.jsp">
            <button class="auth-btn">Create Account</button>
        </a>

        <a href="<%= request.getContextPath() %>/login.jsp">
            <button class="auth-btn" style="margin-top:12px;">Login</button>
        </a>

    </div>
</div>

</body>
</html>

