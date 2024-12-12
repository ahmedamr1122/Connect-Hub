/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Backend.user;

import Backend.user.FileManagement;
import Backend.user.User;
import Backend.user.UserAccountManagement;
import java.util.ArrayList;
import java.util.List;
import Backend.content.Post;
import Backend.content.Story;
import Backend.friends.FriendManager;
import Backend.friends.FriendManagerImplement;
import Backend.friends.FriendRequest;
import Backend.user.Status;
import Frontend.ConnectHubWindow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /*
        String filePath = "Users.json"; // File to store and retrieve users
        UserAccountManagement UAM = new UserAccountManagement();

        // Load users from the file
         List<User> loadedUsers = FileManagement.loadUsers(filePath);
        System.out.println("Loaded Users:");
        for (User user : UAM.getUsers()) {
            UAM.displayUsers();
            //  user.displayFriends();
            //  System.out.println(user.getFriends());
        }
        User user1 = new User("8769", "seif@gmail.com", "seif", "12334", LocalDate.of(1990, 1, 1), Status.OFFLINE, "dasf", "dsf", "fasd");
        User user2 = new User("8908", "ahmed@gmail.com", "ahmed", "12334", LocalDate.of(1990, 1, 1), Status.OFFLINE, "fff", "fff", "ff");
        UAM.getUsers().add(user1);
        UAM.getUsers().add(user2);
        //FriendRequest fr = new FriendRequest();
        //FriendManagerImplement fr = new FriendManagerImplement();
        //fr.sendFriendRequest(user1, user2);
        FileManagement.saveUsers(UAM.getUsers());
       

        // Add a pending friend request
       /* FriendRequest request = new FriendRequest(user1.getUserId(), user2.getUserId(), RequestStatus.PENDING);
        user1.addReceivedRequest(request);
        user2.addSentRequest(request);

        // Save users to the file
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

    }
    */
     /*  FindUser FU = new FindUser();
       
       List<User> loadedUsers = fm.loadUsers("Users.json");
       UserAccountManagement UAM = new UserAccountManagement();
       UAM.setUsers(loadedUsers);
       UAM.displayUsers();*/
     
       
       //FileManagement fm = new FileManagement();
       new ConnectHubWindow().setVisible(true);
    
    }
    
}
