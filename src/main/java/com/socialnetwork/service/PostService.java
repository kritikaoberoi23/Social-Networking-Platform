package com.socialnetwork.service;

import com.socialnetwork.dao.PostDAO;
import com.socialnetwork.model.Post;

import java.util.List;

public class PostService {
    private final PostDAO postDAO = new PostDAO();

    public boolean createPost(int userId, String content) {
        Post p = new Post(userId, content);
        return postDAO.createPost(p);
    }

    public List<Post> getPostsByUser(int userId) {
        return postDAO.getPostsByUser(userId);
    }

    public boolean deletePost(int postId, int userId) {
        return postDAO.deletePost(postId, userId);
    }

    public List<Post> getFriendsPosts(int userId) {
        return postDAO.getFriendsPostsViaSP(userId);
    }
}
