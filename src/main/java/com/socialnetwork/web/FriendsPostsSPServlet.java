package com.socialnetwork.web;

import com.socialnetwork.service.PostService;
import com.socialnetwork.model.Post;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/friends/posts/sp")
public class FriendsPostsSPServlet extends HttpServlet {
    private final PostService postService = new PostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, jakarta.servlet.ServletException {
        HttpSession s = req.getSession(false);
        if (s==null) { resp.sendRedirect("login.jsp"); return; }
        int userId = (int) s.getAttribute("userId");
        List<Post> posts = postService.getFriendsPosts(userId);
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/viewPosts.jsp").forward(req, resp);
    }
}
