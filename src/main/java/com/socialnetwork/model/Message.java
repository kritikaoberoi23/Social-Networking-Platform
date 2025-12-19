package com.socialnetwork.model;

import java.time.LocalDateTime;

public class Message {
    private int msgId;
    private int senderId;
    private int receiverId;
    private String messageText;
    private LocalDateTime sentAt;

    public int getMsgId() { return msgId; }
    public void setMsgId(int msgId) { this.msgId = msgId; }
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
