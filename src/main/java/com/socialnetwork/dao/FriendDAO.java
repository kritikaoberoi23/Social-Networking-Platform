//package com.socialnetwork.dao;
//
//import com.socialnetwork.util.DBConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FriendDAO {
//    public boolean sendRequest(int requesterId, int receiverId) {
//        String sql = "INSERT INTO friends (requester_id, receiver_id, status) VALUES (?, ?, 'PENDING')";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, requesterId);
//            ps.setInt(2, receiverId);
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) { e.printStackTrace(); return false; }
//    }
//
//    public boolean updateStatus(int requesterId, int receiverId, String status) {
//        String sql = "UPDATE friends SET status = ? WHERE requester_id = ? AND receiver_id = ?";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, status);
//            ps.setInt(2, requesterId);
//            ps.setInt(3, receiverId);
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) { e.printStackTrace(); return false; }
//    }
//
//    public boolean cancelRequest(int requesterId, int receiverId) {
//        String sql = "DELETE FROM friends WHERE requester_id = ? AND receiver_id = ? AND status = 'PENDING'";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, requesterId);
//            ps.setInt(2, receiverId);
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) { e.printStackTrace(); return false; }
//    }
//
//    public boolean unfriend(int userA, int userB) {
//        String sql = "DELETE FROM friends WHERE (requester_id = ? AND receiver_id = ?) OR (requester_id = ? AND receiver_id = ?)";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, userA);
//            ps.setInt(2, userB);
//            ps.setInt(3, userB);
//            ps.setInt(4, userA);
//            return ps.executeUpdate() > 0;
//        } catch (SQLException e) { e.printStackTrace(); return false; }
//    }
//
//    public List<Integer> getFriends(int userId) {
//        List<Integer> list = new ArrayList<>();
//        String sql = "SELECT CASE WHEN requester_id = ? THEN receiver_id ELSE requester_id END AS friend_id "
//                + "FROM friends WHERE (requester_id = ? OR receiver_id = ?) AND status = 'ACCEPTED'";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            ps.setInt(2, userId);
//            ps.setInt(3, userId);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) list.add(rs.getInt("friend_id"));
//            }
//        } catch (SQLException e) { e.printStackTrace(); }
//        return list;
//    }
//
//    // stored proc wrapper
//    public List<Integer> getFriendsViaSP(int userId) {
//        List<Integer> list = new ArrayList<>();
//        String sql = "{ call sp_view_friends(?) }";
//        try (Connection conn = DBConnection.getConnection();
//             CallableStatement cs = conn.prepareCall(sql)) {
//            cs.setInt(1, userId);
//            try (ResultSet rs = cs.executeQuery()) {
//                while (rs.next()) list.add(rs.getInt("friend_id"));
//            }
//        } catch (SQLException e) { e.printStackTrace(); }
//        return list;
//    }
//}

package com.socialnetwork.dao;

import com.socialnetwork.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendDAO {

    // SEND REQUEST
    public boolean sendRequest(int requesterId, int receiverId) {
        String sql = "INSERT INTO friends (requester_id, receiver_id, status) VALUES (?, ?, 'PENDING')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, requesterId);
            ps.setInt(2, receiverId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); return false; }
    }


    // ACCEPT REQUEST
    public boolean updateStatus(int requesterId, int receiverId, String status) {

        String sql = "UPDATE friends SET status = ? " +
                     "WHERE requester_id = ? AND receiver_id = ? " +
                     "AND status = 'PENDING'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, requesterId);
            ps.setInt(3, receiverId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); return false; }
    }


    // CANCEL REQUEST (only pending)
    public boolean cancelRequest(int requesterId, int receiverId) {
        String sql = "DELETE FROM friends WHERE requester_id = ? AND receiver_id = ? AND status = 'PENDING'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, requesterId);
            ps.setInt(2, receiverId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); return false; }
    }


    // UNFRIEND (works both sides)
    public boolean unfriend(int userA, int userB) {
        String sql =
                "DELETE FROM friends " +
                "WHERE (requester_id = ? AND receiver_id = ? AND status = 'ACCEPTED') " +
                "   OR (requester_id = ? AND receiver_id = ? AND status = 'ACCEPTED')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userA);
            ps.setInt(2, userB);
            ps.setInt(3, userB);
            ps.setInt(4, userA);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) { e.printStackTrace(); return false; }
    }


    // VIEW FRIENDS
    public List<Integer> getFriends(int userId) {
        List<Integer> list = new ArrayList<>();

        String sql = "SELECT CASE WHEN requester_id = ? THEN receiver_id ELSE requester_id END AS friend_id " +
                     "FROM friends " +
                     "WHERE status = 'ACCEPTED' AND (requester_id = ? OR receiver_id = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ps.setInt(3, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(rs.getInt("friend_id"));

        } catch (SQLException e) { e.printStackTrace(); }

        return list;
    }


    // STORED PROC
    public List<Integer> getFriendsViaSP(int userId) {
        List<Integer> list = new ArrayList<>();

        String sql = "{ call sp_view_friends(?) }";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, userId);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) list.add(rs.getInt("friend_id"));

        } catch (SQLException e) { e.printStackTrace(); }

        return list;
    }
}

