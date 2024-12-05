/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class SignUp {
        private UserAccountManagement UAM;
    // Method to login a user

    public SignUp() {
        UAM = new UserAccountManagement();
    }
    
    public User signup(String email, String username, String password, LocalDate dateOfBirth) {
        if(UAM.contain(email)){
            System.out.println("This email is already exist");

        }
       
        else if(!UAM.contain(email)){
            String Id= UAM.generateUserId();
            User newUser  = new User(Id, email, username, password, dateOfBirth,Status.ONLINE);
            UAM.getUsers().add(newUser);
            return newUser;
        }
       
        return null;
    }
        // Method to sign up a new user
  
}
