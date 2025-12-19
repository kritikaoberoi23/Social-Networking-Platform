package com.socialnetwork.service;

import com.socialnetwork.dao.MessageDAO;
import com.socialnetwork.model.Message;

import java.util.List;

public class MessageService {
    private final MessageDAO dao = new MessageDAO();

    public boolean sendMessage(int senderId, int receiverId, String text) {
        Message m = new Message();
        m.setSenderId(senderId);
        m.setReceiverId(receiverId);
        m.setMessageText(text);
        return dao.insertMessage(m);
    }

    public List<Message> getConversation(int a, int b) {
        return dao.fetchMessagesBetween(a, b);
    }
}
