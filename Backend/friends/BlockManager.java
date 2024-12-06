package Backend.Friends;

import Backend.user.User;


public interface BlockManager {
    void block (User blocker , User blocked);
    void unBlock(User blocker , User blocked);
}
