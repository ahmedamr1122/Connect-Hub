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

    private ContainUser CU;

    // Method to Sign up
    public User signup(String email, String username, String password, LocalDate dateOfBirth) {
        if (!CU.containEmail(email) || !CU.containUserName(username)) {
            User newUser = new User(super.generateUserId(), email, username, PasswordHashing.hashedPass(password), dateOfBirth, Status.ONLINE, "", "", "");
            super.getUsers().add(newUser);
            return newUser;
        }
        return null;

    }

}
