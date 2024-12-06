/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class SignUp extends UserAccountManagement{
    
    // Method to Sign up
    public User signup(String email, String username, String password, LocalDate dateOfBirth) {

        List<User> friends = new ArrayList<>();
        List<User> Blocked = new ArrayList<>();
        User newUser = new User(super.generateUserId(), email, username, password, dateOfBirth, Status.ONLINE,"bio","default","default");
        super.getUsers().add(newUser);
        return newUser;
        
    }
  
}
