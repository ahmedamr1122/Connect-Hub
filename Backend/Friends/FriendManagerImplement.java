package Backend.friends;

import Backend.user.FileManagement;
import Backend.user.User;
import java.util.ArrayList;
import java.util.List;

public class FriendManagerImplement implements FriendManager {

    private FileManagement fileManagement;

    public FriendManagerImplement(FileManagement fileManagement) {
        this.fileManagement = fileManagement;
    }

    @Override
 public void sendFriendRequest(User sender, User receiver, List<User> users) {
    FriendRequest request = new FriendRequest(sender.getUserId(), receiver.getUserId(), RequestStatus.PENDING);

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
    public void respondToFriendRequest(User sender, User receiver, RequestStatus response, List<User> users) {
        FriendRequest request = FriendRequest.findRequest(sender, receiver);
        if (response == RequestStatus.ACCEPTED) {
            sender.getFriends().add(receiver);
            receiver.getFriends().add(sender);
        }

        sender.getSentRequests().remove(request);
        receiver.getReceivedRequests().remove(request);
        fileManagement.saveUsers(users);

    }

    @Override
    public void removeFriend(User sender, User friend, List<User> users) {
        sender.getFriends().remove(friend);
        friend.getFriends().remove(sender);
        fileManagement.saveUsers(users);
    }

    @Override
    public List<User> suggestFriends(User user, List<User> users) {

        List<User> suggestions = new ArrayList<>();
        for (User findUser : users) {
            if (!user.getUserId().equals(findUser.getUserId()) && !user.getBlocked().contains(findUser) && !user.getFriends().contains(findUser)) {
                suggestions.add(findUser);
            }

        }
        return suggestions;
    }
}
