package com.socialnetwork.web;

import com.socialnetwork.service.PostService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/post/create")
public class CreatePostServlet extends HttpServlet {
    private final PostService postService = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);
        if (s == null) { 
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int userId = (int) s.getAttribute("userId");
        String content = req.getParameter("content");

        postService.createPost(userId, content);

        resp.sendRedirect(req.getContextPath() + "/home.jsp");  // FIXED
    }
}
