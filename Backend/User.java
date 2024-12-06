package Backend;

import java.io.*;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// Class to represent a user
public class User {

    private String userId;
    private String email;
    private String username;
    private String password; // Hashed password
    private LocalDate dateOfBirth;
    private Status status; // Online or Offline

    public User(String userId, String email, String username, String password, LocalDate dateOfBirth, Status status) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = hashedPass(password);
        this.dateOfBirth = dateOfBirth;
        this.status = status; // Default status
    }

    /* User(String userId, String email, String username, String password, String dateOfBirth, String online) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
    // Hashing password using SHA-256
  

    public String hashedPass(String pass) {
        try {
            //Chooses Algorithm SHA-256
            MessageDigest transform = MessageDigest.getInstance("SHA-256");

            //apply algorithm and return list of bytes
            byte[] hashed = transform.digest(pass.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hashed) {
                hex.append(String.format("%02x", b)); //convert each byte into two hexadecimal chars
            }
            String hashedPassword = hex.toString();
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean EmailValidation(String email) {
        // Check if the email is null or empty
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Email regex pattern directly used with matches() method
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean DateValidation(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(date, currentDate);
        if (years > 10) {
            return true;

        }
        return false;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = hashedPass(password);
    }
    
}
