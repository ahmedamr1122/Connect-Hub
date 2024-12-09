package Backend.user;

import java.util.List;

public class FindUser {
    
    public User findUserByEmail(String email,List <User> users){
        
        if (users == null || users.isEmpty()) {
            System.out.println("User list is empty or not initialized.");
            return null;
        }
        
       for (User user : users ) {
        //System.out.println(user.getEmail());
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    
    
    public User findUserById(String userId,List <User> users) {
    for (User user : users) {
        if (user.getUserId().equals(userId)) {
            return user;
        }
    }
    return null; // User not found
}
    public User findUserByUsername(String username,List <User> users) {
    for (User user : users) {
        if (user.getUsername().equals(username)) {
            return user;
        }
    }

    return null; // User not found
}
}
