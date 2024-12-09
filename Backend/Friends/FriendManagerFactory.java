package Backend.friends;

import Backend.user.FileManagement;

public class FriendManagerFactory {
    
    
    public static FriendManager createFriendManager(FileManagement fileManagement) {
        
        return new FriendManagerImplement(fileManagement);
        
    }
}
