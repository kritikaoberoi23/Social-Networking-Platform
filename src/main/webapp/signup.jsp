<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="auth-page">
    <div class="auth-card">

        <div class="auth-title">Create Account</div>

        <form action="signup" method="post">
            <input class="auth-input" name="username" placeholder="Username" required/>
            <input class="auth-input" name="email" type="email" placeholder="Email" required/>
            <input class="auth-input" name="password" type="password" placeholder="Password" required/>

            <button class="auth-btn">Sign Up</button>
        </form>

        <div class="auth-link">
            Already have an account?
            <a href="<%= request.getContextPath() %>/login.jsp">Login</a>
        </div>

    </div>
</div>

</body>
</html>
