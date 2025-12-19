package com.socialnetwork.web;

import com.socialnetwork.model.User;
import com.socialnetwork.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final AuthService auth = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setBio("");
        u.setPhotoUrl("");

        boolean ok = auth.signup(u, password);
        if (ok) resp.sendRedirect("login.jsp");
        else resp.getWriter().println("Signup failed (maybe duplicate).");
    }
}
