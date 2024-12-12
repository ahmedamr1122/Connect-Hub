package Backend.user;

import java.util.List;

public class Logout{
    
   
    // Method to logout a user
    public void logout(User user,List<User> users) {
        if (user != null) {
            user.setStatus(Status.OFFLINE);
            System.out.println("User  logged out: " + user.getUsername());
            FileManagement userRepository =  FileManagement.getInstance();
            userRepository.saveUsers(users);
        }
    }
    
}
