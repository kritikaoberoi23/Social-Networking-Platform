package com.socialnetwork;

import com.socialnetwork.model.Post;
import com.socialnetwork.model.User;
import com.socialnetwork.service.AuthService;
import com.socialnetwork.service.FriendService;
import com.socialnetwork.service.MessageService;
import com.socialnetwork.service.PostService;
import com.socialnetwork.dao.UserDAO;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainApp {
    private static final AuthService auth = new AuthService();
    private static final PostService postService = new PostService();
    private static final FriendService friendService = new FriendService();
    private static final MessageService messageService = new MessageService();
    private static final UserDAO userDAO = new UserDAO();

    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("\n1) Signup  2) Login  3) Exit");
                System.out.print("Choose: ");
                String c = sc.nextLine().trim();
                switch (c) {
                    case "1": doSignup(sc);
                    break;
                    case "2": doLogin(sc);
                    break;
                    case "3": {
                        System.out.println("Bye");
                        return;
                        
                    }
                    default: System.out.println("Invalid");
                }
            } else {
                System.out.println("\nLogged in as: " + currentUser.getUsername());
                System.out.println("1) Create Post  2) View My Posts  3) Delete Post  4) Send Friend Request  5) Accept Request");
                System.out.println("6) Unfriend/Cancel Request  7) Send Message  8) View Conversation  9) View Friends (SP)");
                System.out.println("10) View Friends' Posts (SP)  11) Delete Account  12) Logout");
                System.out.print("Choose: ");
                String c = sc.nextLine().trim();
                switch (c) {
                    case "1" : createPost(sc);break;
                    case "2" : viewPosts();break;
                    case "3" : deletePost(sc);break;
                    case "4" : sendFriendRequest(sc);break;
                    case "5" : acceptFriendRequest(sc);break;
                    case "6" : unfriendOrCancel(sc);break;
                    case "7" : sendMessage(sc);break;
                    case "8" : viewConversation(sc);break;
                    case "9" : viewFriendsSP();break;
                    case "10" : viewFriendsPostsSP();break;
                    case "11" : deleteAccount(sc);break;
                    case "12": {
                        currentUser = null;
                        System.out.println("Logged out");
                        break;
                    }
                    default: System.out.println("Invalid");
                }
            }
        }
    }

    private static void doSignup(Scanner sc) {
        System.out.print("username: ");
        String username = sc.nextLine().trim();
        System.out.print("email: ");
        String email = sc.nextLine().trim();
        System.out.print("password: ");
        String password = sc.nextLine().trim();

        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setBio("");
        u.setPhotoUrl("");

        boolean ok = auth.signup(u, password);
        System.out.println(ok ? "Signup success" : "Signup failed (maybe duplicate)");
    }

    private static void doLogin(Scanner sc) {
        System.out.print("username: ");
        String username = sc.nextLine().trim();
        System.out.print("password: ");
        String password = sc.nextLine().trim();
        Optional<User> ou = auth.login(username, password);
        if (ou.isPresent()) {
            currentUser = ou.get();
            System.out.println("Welcome " + currentUser.getUsername());
        } else System.out.println("Login failed");
    }

    private static void createPost(Scanner sc) {
        System.out.print("Post content: ");
        String content = sc.nextLine().trim();
        boolean ok = postService.createPost(currentUser.getUserId(), content);
        System.out.println(ok ? "Posted." : "Failed to post.");
    }

    private static void viewPosts() {
        List<Post> ps = postService.getPostsByUser(currentUser.getUserId());
        if (ps.isEmpty()) { System.out.println("No posts"); return; }
        for (Post p : ps) {
            System.out.println("-- Post #" + p.getPostId() + " @" + p.getCreatedAt() + " --\n" + p.getContent());
        }
    }

    private static void deletePost(Scanner sc) {
        System.out.print("Enter post id to delete: ");
        int pid;
        try {
            pid = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) { System.out.println("Invalid id"); return; }
        boolean ok = postService.deletePost(pid, currentUser.getUserId());
        System.out.println(ok ? "Deleted." : "Delete failed (not owner or not exist).");
    }

    private static void sendFriendRequest(Scanner sc) {
        System.out.print("Enter receiver user id: ");
        int rid;
        try { rid = Integer.parseInt(sc.nextLine().trim()); } catch (NumberFormatException e) { System.out.println("Invalid id"); return; }
        boolean ok = friendService.sendRequest(currentUser.getUserId(), rid);
        System.out.println(ok ? "Request sent" : "Failed (maybe already requested).");
    }

    private static void acceptFriendRequest(Scanner sc) {
        System.out.print("Enter requester user id: ");
        int reqId;
        try { reqId = Integer.parseInt(sc.nextLine().trim()); } catch (NumberFormatException e) { System.out.println("Invalid id"); return; }
        boolean ok = friendService.acceptRequest(reqId, currentUser.getUserId());
        System.out.println(ok ? "Accepted" : "Failed");
    }

    private static void unfriendOrCancel(Scanner sc) {
        System.out.println("1) Cancel sent request  2) Unfriend someone");
        System.out.print("Choose: ");
        String ch = sc.nextLine().trim();
        if ("1".equals(ch)) {
            System.out.print("Enter receiver id of the pending request: ");
            int rid;
            try { rid = Integer.parseInt(sc.nextLine().trim()); } catch (NumberFormatException e) { System.out.println("Invalid id"); return; }
            boolean ok = friendService.cancelRequest(currentUser.getUserId(), rid);
            System.out.println(ok ? "Cancelled" : "Cancel failed");
        } else if ("2".equals(ch)) {
            System.out.print("Enter friend id to unfriend: ");
            int fid;
            try { fid = Integer.parseInt(sc.nextLine().trim()); } catch (NumberFormatException e) { System.out.println("Invalid id"); return; }
            boolean ok = friendService.unfriend(currentUser.getUserId(), fid);
            System.out.println(ok ? "Unfriended" : "Unfriend failed");
        } else System.out.println("Invalid choice");
    }

    private static void sendMessage(Scanner sc) {
        System.out.print("Enter receiver id: ");
        int rid;
        try { rid = Integer.parseInt(sc.nextLine().trim()); } catch (NumberFormatException e) { System.out.println("Invalid id"); return; }
        System.out.print("Message: ");
        String msg = sc.nextLine().trim();
        boolean ok = messageService.sendMessage(currentUser.getUserId(), rid, msg);
        System.out.println(ok ? "Sent" : "Failed");
    }

    private static void viewConversation(Scanner sc) {
        System.out.print("Enter other user id: ");
        int other;
        try { other = Integer.parseInt(sc.nextLine().trim()); } catch (NumberFormatException e) { System.out.println("Invalid id"); return; }
        List<com.socialnetwork.model.Message> conv = messageService.getConversation(currentUser.getUserId(), other);
        if (conv.isEmpty()) { System.out.println("No messages"); return; }
        for (com.socialnetwork.model.Message m : conv) {
            String prefix = m.getSenderId() == currentUser.getUserId() ? "You: " : ("User" + m.getSenderId() + ": ");
            System.out.println(prefix + m.getMessageText() + " (" + m.getSentAt() + ")");
        }
    }

    private static void viewFriendsSP() {
        List<Integer> friends = friendService.getFriendsViaSP(currentUser.getUserId());
        if (friends.isEmpty()) { System.out.println("No friends"); return; }
        System.out.println("Friends:");
        for (int f : friends) System.out.println("- User " + f);
    }

    private static void viewFriendsPostsSP() {
        List<Post> posts = postService.getFriendsPosts(currentUser.getUserId());
        if (posts.isEmpty()) { System.out.println("No posts from friends"); return; }
        System.out.println("Friends' Posts:");
        for (Post p : posts) {
            System.out.println("-- Post #" + p.getPostId() + " by User" + p.getUserId() + " @" + p.getCreatedAt() + " --");
            System.out.println(p.getContent());
        }
    }

    private static void deleteAccount(Scanner sc) {
        System.out.print("Are you sure? type YES to delete account: ");
        String ans = sc.nextLine().trim();
        if (!"YES".equals(ans)) { System.out.println("Aborted"); return; }
        boolean ok = userDAO.deleteUser(currentUser.getUserId());
        if (ok) {
            currentUser = null;
            System.out.println("Account deleted.");
        } else System.out.println("Delete failed");
    }
}
