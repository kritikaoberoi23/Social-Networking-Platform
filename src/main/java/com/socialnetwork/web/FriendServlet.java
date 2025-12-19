package com.socialnetwork.web;

import com.socialnetwork.service.FriendService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/friend")
public class FriendServlet extends HttpServlet {
    private final FriendService friendService = new FriendService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession s = req.getSession(false);
        if (s==null) { resp.sendRedirect("login.jsp"); return; }
        int userId = (int) s.getAttribute("userId");
        String action = req.getParameter("action"); // send, accept, cancel, unfriend
        int target = Integer.parseInt(req.getParameter("targetId"));
        boolean ok = false;
        switch (action) {
            case "send": ok = friendService.sendRequest(userId, target);
            break;
            case "accept": ok = friendService.acceptRequest(target, userId); // target is requester
            break;
            case "cancel": ok = friendService.cancelRequest(userId, target);
            break;
            case "unfriend": ok = friendService.unfriend(userId, target);
            break;
        }
        resp.sendRedirect("home.jsp");
    }
}
