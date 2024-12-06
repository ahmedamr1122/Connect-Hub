/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Backend;
import Backend.user.FileManagement;
import Backend.user.Login;
import Backend.user.User;
import Backend.user.UserAccountManagement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.time.LocalDate;
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
        UserAccountManagement userAccountManagement = new UserAccountManagement();
        FileManagement FM = new FileManagement("Users.json");
          List<User> users= new ArrayList<>();
          List<User> users2= new ArrayList<>();
        // Sign up new users
        LocalDate currentDate = LocalDate.now();
        /*User u1= userAccountManagement.signup("user1@example.com", "Ahmed", "password1", currentDate);
        User u2 = userAccountManagement.signup("user2@example.com", "Seif", "password2", currentDate);
        System.out.println(u1.getUserId());
        users.add(u1);
        users.add(u2);*/
        //userAccountManagement.saveUsers(users, "Users.json");
        users=FM.loadUsers("Users.json");

       boolean x  = new Login().login("Ahmed", "password1");
        System.out.println(x);

        // Display loaded users
        userAccountManagement.displayUsers();

        // Login a user

        // Logout the user
        //userAccountManagement.logout(loggedInUser );
    }
    
}
