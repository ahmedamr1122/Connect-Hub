package Backend.user;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit;

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
        // Password must contain at least eight digits, one uppercase letter, one lowercase letter, and one special character
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    // Validate username format
    public boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        // Username must be alphanumeric and between 3-15 characters
        String usernameRegex = "^[a-zA-Z0-9_]{3,15}$";
        return Pattern.matches(usernameRegex, username);
    }

    // Validate date of birth (user must be older than a specific age (10 years old)
    public boolean isValidDateOfBirth(LocalDate date) {

        if (date == null) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(date, currentDate);
        if (years > 10) {
            return true;
        }
        return false;
    }

    // Check if the username is taken
    public static boolean loginUsernameValidation(String username) {
        ContainUser containUser = new ContainUser();
        if (containUser.containUserName(username)) {
            System.out.println("This username is taken");
            return false;
        }
        return true;
    }

    // Check if email is taken
    public boolean loginEmailValidation(String email) {
        ContainUser containUser = new ContainUser();
        if (containUser.containEmail(email)) {
            System.out.println("This email is taken");
            return false;
        }
        return true;
    }

    // Validate bio length
    public void validateBio(String bio) {
        if (bio == null || bio.trim().isEmpty()) {
            throw new IllegalArgumentException("Bio cannot be empty.");
        }
        if (bio.length() > 150) {
            throw new IllegalArgumentException("Bio cannot exceed 150 characters.");
        }
    }

    // Validate photo path
    public void validatePhotoPath(String photoPath) {
        if (photoPath == null || photoPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Photo path cannot be empty.");
        }
        if (!photoPath.matches(".*\\.(jpg|jpeg|png|gif)$")) {
            throw new IllegalArgumentException("Invalid file type for photo. Only .jpg or .png files are allowed.");
        }
    }

    // Validate password (minimum length only)
    public void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
    }
}