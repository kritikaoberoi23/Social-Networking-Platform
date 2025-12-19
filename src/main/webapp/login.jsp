<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="auth-page">
    <div class="auth-card">

        <div class="auth-title">Login</div>

        <form action="login" method="post">
            <input class="auth-input" name="username" placeholder="Username" required/>
            <input class="auth-input" name="password" type="password" placeholder="Password" required/>

            <button class="auth-btn">Login</button>
        </form>

        <div class="auth-link">
            Don't have an account?
            <a href="<%= request.getContextPath() %>/signup.jsp">Signup</a>
        </div>

    </div>
</div>

</body>
</html>

