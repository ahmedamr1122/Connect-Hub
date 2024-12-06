package Backend.friends;

import Backend.user.User;
import java.util.List;

public interface FriendManager {
    
    void sendFriendRequest(User sender, User receiver);
    void respondToFriendRequest(User sender, User receiver, RequestStatus response);
    void removeFriend(User sender, User friend);
    //List<User> suggestFriends(User user);
    
}
