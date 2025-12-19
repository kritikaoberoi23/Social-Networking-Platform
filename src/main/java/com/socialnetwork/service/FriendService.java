package com.socialnetwork.service;

import com.socialnetwork.dao.FriendDAO;

import java.util.List;

public class FriendService {
    private final FriendDAO dao = new FriendDAO();

    public boolean sendRequest(int requesterId, int receiverId) {
        return dao.sendRequest(requesterId, receiverId);
    }

    public boolean acceptRequest(int requesterId, int receiverId) {
        return dao.updateStatus(requesterId, receiverId, "ACCEPTED");
    }

    public boolean cancelRequest(int requesterId, int receiverId) {
        return dao.cancelRequest(requesterId, receiverId);
    }

    public boolean unfriend(int userA, int userB) {
        return dao.unfriend(userA, userB);
    }

    public List<Integer> getFriends(int userId) {
        return dao.getFriends(userId);
    }

    public List<Integer> getFriendsViaSP(int userId) {
        return dao.getFriendsViaSP(userId);
    }
}
