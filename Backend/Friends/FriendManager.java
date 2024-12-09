package Backend.friends;

import Backend.user.User;
import java.util.List;

public interface FriendManager {
    
    void sendFriendRequest(User sender, User receiver,List<User> users);
    void respondToFriendRequest(User sender, User receiver, RequestStatus response,List<User> users);
    void removeFriend(User sender, User friend,List<User> users);
    List<User> suggestFriends(User user, List<User> users);
    
}
