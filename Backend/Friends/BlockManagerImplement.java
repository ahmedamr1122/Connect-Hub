package Backend.Friends;

import Backend.user.User;

public class BlockManagerImplement implements BlockManager {

    //by using singleton pattern
    //blocking behavior must remain uniform
    private static BlockManagerImplement instance;

    private BlockManagerImplement() {
    }

    public static BlockManagerImplement getInstance() {
        if (instance == null) {
            instance = new BlockManagerImplement();
        }
        return instance;
    }

    @Override
    public void block(User blocker, User blocked) {
        if (blocker.getFriends().contains(blocked)) {
            blocker.getFriends().remove(blocked);
            blocked.getFriends().remove(blocker);
        }
        blocker.getBlocked().add(blocked);
        //blocked.getfriendsuggetions().remove(blocker)
        //blocker.getfriendsuggetions().remove(blocked)
    }

    @Override
    public void unBlock(User blocker, User blocked) {
        if (blocker.getBlocked().contains(blocked)) {
            blocker.getBlocked().remove(blocked);
            //blocked.getfriendsuggetions().add(blocker)
            //blocker.getfriendsuggetions().add(blocked)
        }
        else{
            System.out.println("the user is not blocked");
        }
    }

}
