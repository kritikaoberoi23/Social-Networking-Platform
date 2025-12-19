package com.socialnetwork.model;

import java.time.LocalDateTime;

public class Post {
    private int postId;
    private int userId;
    private String content;
    private LocalDateTime createdAt;

    public Post() {}

    public Post(int userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
