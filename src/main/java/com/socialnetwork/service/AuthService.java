package com.socialnetwork.service;

import com.socialnetwork.dao.UserDAO;
import com.socialnetwork.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public boolean signup(User user, String rawPassword) {
        try {
            user.setPassword(hash(rawPassword));
            return userDAO.createUser(user);
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Optional<User> login(String username, String rawPassword) {
        Optional<User> ou = userDAO.findByUsername(username);
        if (ou.isEmpty()) return Optional.empty();
        User u = ou.get();
        try {
            String hashed = hash(rawPassword);
            if (hashed.equals(u.getPassword())) return Optional.of(u);
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    private String hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] h = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(h);
    }
}
