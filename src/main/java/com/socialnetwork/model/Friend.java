package com.socialnetwork.model;

public class Friend {
    private int id;
    private int requesterId;
    private int receiverId;
    private String status; // PENDING, ACCEPTED, REJECTED

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getRequesterId() { return requesterId; }
    public void setRequesterId(int requesterId) { this.requesterId = requesterId; }
    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
