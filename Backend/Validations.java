/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class Validations {

    // Validate email format
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Regex pattern for validating email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    // Validate password strength
    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        // Password must contain at least one digit, one uppercase letter, one lowercase letter, and one special character
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    // Validate username format
    /*public boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        // Username must be alphanumeric and between 3-15 characters
        String usernameRegex = "^[a-zA-Z0-9._-]{3,15}$";
        return Pattern.matches(usernameRegex, username);
    }*/

    // Validate date of birth (user must be older than a specific age, e.g., 10 years old)
    /*public boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        int minimumAge = 10;
        return dateOfBirth.isBefore(currentDate.minusYears(minimumAge));
    }*/


    /*public static boolean EmailValidation(String email) {
        // Check if the email is null or empty
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Email regex pattern directly used with matches() method
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }*/

    public boolean isValidDateOfBirth(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(date, currentDate);
        if (years > 10) {
            return true;

        }
        return false;
    }
    // Validate username format and uniqueness
public static boolean isValidUsername(String username, List<User> users) {
    

    // Regex for username: alphanumeric, 3-15 characters, allows underscores, hyphens, and periods
    /*String usernameRegex = "^[a-zA-Z0-9._-]{3,15}$";
    if (!Pattern.matches(usernameRegex, username)) {
        return false;
    }*/

    // Check if the username already exists
    for (User user : users) {
        if (user.getUsername().equalsIgnoreCase(username)) { // Case-insensitive comparison
            return false; // Username already exists
        }
    }

    return true; // Username is valid and unique
}

}
