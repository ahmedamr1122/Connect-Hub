package Backend.user;

public class Logout extends UserAccountManagement{
    
    // Method to logout a user
    public void logout(User user) {
        if (user != null) {
            user.setStatus(Status.OFFLINE);
            System.out.println("User  logged out: " + user.getUsername());
            FileManagement userRepository = new FileManagement("Users.json");
            userRepository.saveUsers(super.getUsers());
        }
    }
    
}
