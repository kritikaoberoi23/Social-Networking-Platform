package com.socialnetwork.web;

import com.socialnetwork.service.PostService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private PostService postService = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String content = req.getParameter("content");

        boolean ok = postService.createPost(userId, content);

        if (ok)
            resp.sendRedirect("home.jsp");
        else
            resp.getWriter().println("Failed to post!");
    }
}
