//package com.socialnetwork.web;
//
//import com.socialnetwork.service.MessageService;
//import com.socialnetwork.model.Message;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/message/*")
//public class MessageServlet extends HttpServlet {
//    private final MessageService msgService = new MessageService();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String path = req.getPathInfo(); // /send
//        if (path == null) path = "";
//
//        HttpSession s = req.getSession(false);
//        if (s == null) {
//            resp.sendRedirect("login.jsp");
//            return;
//        }
//
//        int userId = (int) s.getAttribute("userId");
//
//        if (path.equals("/send")) {
//
//            int toId = Integer.parseInt(req.getParameter("toId"));
//            String text = req.getParameter("text");
//
//            msgService.sendMessage(userId, toId, text);
//
//            // 👇 SAME PAGE PAR WAPAS AAYE
//            resp.sendRedirect(req.getContextPath() + "/sendMessage.jsp?receiverId=" + toId);
//
//        } else {
//            resp.sendError(404);
//        }
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, jakarta.servlet.ServletException {
//        // view at /message/view?otherId=...
//        String path = req.getPathInfo();
//        HttpSession s = req.getSession(false);
//        if (s==null) { resp.sendRedirect("login.jsp"); return; }
//        int userId = (int) s.getAttribute("userId");
//        if ("/view".equals(path)) {
//            int other = Integer.parseInt(req.getParameter("otherId"));
//            List<Message> conv = msgService.getConversation(userId, other);
//            req.setAttribute("conv", conv);
//            req.getRequestDispatcher("/viewConversation.jsp").forward(req, resp);
//        } else resp.sendError(404);
//    }
//}
package com.socialnetwork.web;

import com.socialnetwork.service.MessageService;
import com.socialnetwork.model.Message;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/message/*")
public class MessageServlet extends HttpServlet {
    private final MessageService msgService = new MessageService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo(); // /send
        if (path == null) path = "";

        HttpSession s = req.getSession(false);
        if (s == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) s.getAttribute("userId");

        if (path.equals("/send")) {

            // 🔥 FIX — TRIM() before parsing
            int toId = Integer.parseInt(req.getParameter("toId").trim());
            String text = req.getParameter("text").trim();

            msgService.sendMessage(userId, toId, text);

            // SAME PAGE PAR VAAPAS
            resp.sendRedirect(req.getContextPath() + "/sendMessage.jsp?receiverId=" + toId);

        } else {
            resp.sendError(404);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, jakarta.servlet.ServletException {

        String path = req.getPathInfo();
        HttpSession s = req.getSession(false);

        if (s == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) s.getAttribute("userId");

        if ("/view".equals(path)) {

            // 🔥 FIX — TRIM()
            int other = Integer.parseInt(req.getParameter("otherId").trim());

            List<Message> conv = msgService.getConversation(userId, other);
            req.setAttribute("conv", conv);
            req.getRequestDispatcher("/viewConversation.jsp").forward(req, resp);

        } else {
            resp.sendError(404);
        }
    }
}

