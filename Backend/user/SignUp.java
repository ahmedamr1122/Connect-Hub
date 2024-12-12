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
public class SignUp extends UserAccountManagement {

    private ContainUser CU = new ContainUser();
    private FileManagement fileManagement = FileManagement.getInstance();
    

    // Method to Sign up
    public User signup(String email, String username, String password, LocalDate dateOfBirth,List<User> users) {
        if (!CU.containEmail(email,users) || !CU.containUserName(username)) {
            User newUser = new User(Integer.toString(users.size() + 100), email, username, PasswordHashing.hashedPass(password), dateOfBirth, Status.OFFLINE, "", "", "");
            super.getUsers().add(newUser);
           
            return newUser;
        }
        return null;

    }

}
