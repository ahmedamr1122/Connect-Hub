package Backend.user;

public class FindUser extends UserAccountManagement{
    
    public User findUserByEmail(String email){
       for (User user : UserAccountManagement.getUsers() ) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    
    public User findUserById(String userId) {
    for (User user : UserAccountManagement.getUsers()) {
        if (user.getUserId().equals(userId)) {
            return user;
        }
    }
    return null; // User not found
}
}
