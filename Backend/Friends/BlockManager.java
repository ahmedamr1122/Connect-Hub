package Backend.Friends;

import Backend.user.User;
import java.util.List;


public interface BlockManager {
    void block (User blocker , User blocked,List <User> users);
    void unBlock(User blocker , User blocked,List <User> users);
}
