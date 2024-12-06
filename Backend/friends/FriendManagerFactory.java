package Backend.friends;

public class FriendManagerFactory {
    
    
    public static FriendManager createFriendManager() {
        
        return new FriendManagerImplement();
        
    }
}
