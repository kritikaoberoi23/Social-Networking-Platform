package com.socialnetwork.web;

import com.socialnetwork.model.User;
import com.socialnetwork.service.AuthService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService auth = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<User> ou = auth.login(username, password);
        if (ou.isPresent()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userId", ou.get().getUserId());
            session.setAttribute("username", ou.get().getUsername());
            resp.sendRedirect("home.jsp");
        } else {
            resp.getWriter().println("Login failed");
        }
    }
}
