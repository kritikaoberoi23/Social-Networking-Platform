package com.socialnetwork.web;

import com.socialnetwork.dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/account/delete")
public class DeleteAccountServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);
        if (s==null) { resp.sendRedirect("login.jsp"); return; }
        String confirm = req.getParameter("confirm");
        if (!"YES".equals(confirm)) { resp.sendRedirect("home.jsp"); return; }
        int userId = (int) s.getAttribute("userId");
        boolean ok = userDAO.deleteUser(userId);
        s.invalidate();
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
