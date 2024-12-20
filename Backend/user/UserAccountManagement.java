package Backend.user;

import java.util.ArrayList;
import java.util.List;

public class UserAccountManagement{

    private List<User> users;


    public UserAccountManagement() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    

    // Method to generate a unique user ID (for simplicity, using username here)
    public String generateUserId() {
        return Integer.toString(users.size() + 100); // Simple user ID generation
    }
   
    // Method to display all users (for testing purposes)
    public void displayUsers() {
        if(users.size()==0){
            System.out.println("the file is empty");
        }
        System.out.println("sss");
            
        for (User user : users) {
            System.out.println("User ID: " + user.getUserId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail() +"Bio: "+user.getBio() + ", Status: " + user.getStatus());
            System.out.println("Password: " + user.getPassword());
        }
    }

}
