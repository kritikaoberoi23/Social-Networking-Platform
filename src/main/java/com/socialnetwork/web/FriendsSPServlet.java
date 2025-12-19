package com.socialnetwork.web;

import com.socialnetwork.service.FriendService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/friends/sp")
public class FriendsSPServlet extends HttpServlet {
    private final FriendService friendService = new FriendService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, jakarta.servlet.ServletException {
        HttpSession s = req.getSession(false);
        if (s==null) { resp.sendRedirect("login.jsp"); return; }
        int userId = (int) s.getAttribute("userId");
        List<Integer> friends = friendService.getFriendsViaSP(userId);
        req.setAttribute("friends", friends);
        req.getRequestDispatcher("/friends.jsp").forward(req, resp);
    }
}
