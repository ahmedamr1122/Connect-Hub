package Backend.Friends;

import Backend.user.User;
import Backend.user.FindUser;
import java.util.ArrayList;
import java.util.List;

public class BlockManagerImplement implements BlockManager {

    FindUser findUser = new FindUser();
    //by using singleton pattern
    //blocking behavior must remain uniform
    private static BlockManagerImplement instance;

    /*private BlockManagerImplement() {
    }*/
    public static BlockManagerImplement getInstance() {
        if (instance == null) {
            instance = new BlockManagerImplement();
        }
        return instance;
    }

    @Override
//    public void block(User blocker, User blocked,List<User> users) {
//        List<String> friendIds = new ArrayList<>();
//        for (User friend : blocker.getFriends()) {
//            friendIds.add(friend.getUserId());
//        }
//        User blocker1 = new User(blocker.getUserId());
//        User blocked1 = new User(blocked.getUserId());
//
//        if (friendIds.contains(blocked.getUserId())) {
//            blocker.getFriends().remove(blocker1);
//            blocked.getFriends().remove(blocked1);
//        }
//        blocker.getBlocked().add(blocked);
//
//
//    }
    public void block(User blocker, User blocked, List<User> users) {
        List friendsList1 = blocker.getFriends(users);
        List friendsList2 = blocked.getFriends(users);
        if (friendsList1.contains(blocked)) {
            friendsList1.remove(blocked);
            blocker.setFriends(friendsList1);
            friendsList2.remove(blocker);
            blocked.setFriends(friendsList2);
        }
        blocker.getBlocked().add(blocked);

    }

    @Override
    public void unBlock(User blocker, User blocked, List<User> users) {
        List blockedList1 = blocker.getFriends(users);
        if (blockedList1.contains(blocked)) {
            blockedList1.remove(blocked);
            blocker.setBlocked(blockedList1);
            //blocked.getfriendsuggetions().add(blocker)
            //blocker.getfriendsuggetions().add(blocked)
        } else {
            System.out.println("the user is not blocked");
        }
    }

}
