<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>

<%
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="fade">

<div class="layout">

    <!-- LEFT SIDEBAR -->
    <div class="sidebar">
        <h3>Navigation</h3>
        <a href="post/my">My Posts</a>
        <a href="friends/sp">Friends</a>
        <a href="friends/posts/sp">Friends' Posts</a>
        <a href="logout">Logout</a>
    </div>


    <!-- MAIN FEED -->
    <div class="feed">

        <div class="page-title">Welcome, <%= username %></div>

        <!-- Create Post -->
        <div class="section">
            <h2>Create Post</h2>
            <form action="post/create" method="post">
                <textarea name="content" rows="3" placeholder="Share something..." required></textarea>
                <button>Create Post</button>
            </form>
        </div>

        <!-- Delete Post -->
        <div class="section">
            <h2>Delete Post</h2>
            <form action="post/delete" method="post">
                <input name="postId" placeholder="Post ID to delete" required/>
                <button>Delete</button>
            </form>
        </div>

        <!-- Messaging -->
        <div class="section">
            <h2>Messaging</h2>
            <form action="message/send" method="post">
                <input name="toId" placeholder="Send to User ID" required/>
                <input name="text" placeholder="Write a message..." required/>
                <button>Send Message</button>
            </form>

            <form action="message/view" method="get">
                <input name="otherId" placeholder="View conversation with User ID" required/>
                <button>View Conversation</button>
            </form>
        </div>

    </div>


    <!-- RIGHT SIDEBAR -->
    <div class="rightbar">

        <h3>Friend Actions</h3>

        <form action="friend" method="post">
            <select name="action">
                <option value="send">Send Request</option>
                <option value="accept">Accept Request</option>
                <option value="cancel">Cancel Request</option>
                <option value="unfriend">Unfriend</option>
            </select>

            <input name="targetId" placeholder="Target User ID" required/>
            <button>Submit</button>
        </form>

        <hr>

        <h3>Delete Account</h3>

        <form action="account/delete" method="post">
            <input name="confirm" placeholder="Type YES to delete"/>
            <button>Delete Account</button>
        </form>

    </div>

</div>

</body>
</html>
