package Backend.friends;

import Backend.user.FileManagement;
import Backend.user.FindUser;
import Backend.user.User;
import java.util.ArrayList;
import java.util.List;

public class FriendManagerImplement implements FriendManager {

    private FileManagement fileManagement = FileManagement.getInstance();

    private static FriendManagerImplement instance;

    /*private FriendManagerImplement() {
        this.fileManagement = fileManagement;
    }*/
    public static FriendManagerImplement getInstance() {
        if (instance == null) {
            instance = new FriendManagerImplement();
        }
        return instance;
    }

    /*public FriendManagerImplement(FileManagement fileManagement) {
        
    }*/
    @Override
    public void sendFriendRequest(User sender, User receiver, List<User> users) {

        FriendRequest request = new FriendRequest(sender.getUserId(), receiver.getUserId(), RequestStatus.PENDING);

        if (fileManagement == null) {

            System.err.println("FileManagement is null when attempting to save users.");
        }

        // Check if the sender is blocked by the receiver
        if (receiver.getBlocked().contains(sender)) {
            System.out.println("ERROR: This User blocked you.");
            return;
        }

        // Check if the request already exists in the sender's sent requests
        if (sender.getSentRequests().contains(request)) {
            System.out.println("ERROR: You have already sent a friend request to this user.");
            return;
        }

        // Add the friend request to both sender and receiver
        sender.getSentRequests().add(request);
        receiver.getReceivedRequests().add(request);

        // Save users to persistent storage
        fileManagement.saveUsers(users);

        System.out.println("Friend request sent successfully.");
    }

    @Override
    /* public void respondToFriendRequest(User sender, User receiver, RequestStatus response, List<User> users) {
        FriendRequest request = FriendRequest.findRequest(sender, receiver);
        if (response == RequestStatus.ACCEPTED) {
            sender.getFriends().add(receiver);
            receiver.getFriends().add(sender);
        }

        sender.getSentRequests().remove(request);
        receiver.getReceivedRequests().remove(request);
        fileManagement.saveUsers(users);

    }*/
    public void respondToFriendRequest(User sender, User receiver, RequestStatus response, List<User> users) {
        // Find the friend request
        FriendRequest request1 = FriendRequest.findSentRequest(sender, receiver);
        FriendRequest request2 = FriendRequest.findReceivedRequest(receiver, sender);

        if (request1 == null && request2 == null) {
            return; // Exit if no friend request exists
        }

        if (response == RequestStatus.ACCEPTED) {
            // Check if they're already friends before adding
            if (!sender.getFriends().contains(receiver)) {
                sender.getFriends().add(receiver);
                receiver.getFriends().add(sender);
                
            }
        }

        // Remove the friend request from sender and receiver
        sender.getSentRequests().remove(request1);
        receiver.getReceivedRequests().remove(request2);

        // Save users to file
        try {
            fileManagement.saveUsers(users);
        } catch (Exception e) {
            e.printStackTrace(); // Handle any save errors
        }
    }

    @Override
    public void removeFriend(User sender, User friend, List<User> users) {
        List friendsList1 = sender.getFriends(users);
        List friendsList2 = friend.getFriends(users);
        friendsList1.remove(friend);
        friendsList2.remove(sender);
        sender.setFriends(friendsList1);
        friend.setFriends(friendsList1);

        fileManagement.saveUsers(users);
    }

    @Override
    public List<User> suggestFriends(User user, List<User> users) {
        List<String> blockedIds = new ArrayList<>();
        for (User blocked : user.getBlocked()) {
            blockedIds.add(blocked.getUserId());
        }

        List<String> friendIds = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friendIds.add(friend.getUserId());
        }

        List<User> suggestions = new ArrayList<>();

        for (User potentialFriend : users) {
            // Skip if the user is the same, is blocked, or is already a friend
            if (potentialFriend.getUserId().equals(user.getUserId())
                    || blockedIds.contains(potentialFriend.getUserId())
                    || friendIds.contains(potentialFriend.getUserId())) {
                continue;
            }
            // Add to suggestions if not already in the list
            suggestions.add(potentialFriend);
        }

        return suggestions;
    }

}
