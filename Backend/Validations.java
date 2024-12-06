/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author asus
 */
public class Validations {
  
    public void validateBio(String bio) {
        if (bio == null || bio.trim().isEmpty()) {
            throw new IllegalArgumentException("Bio cannot be empty.");
        }
    }

    public void validatePhotoPath(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Photo path cannot be empty.");
        }
        if (!path.endsWith(".jpg") && !path.endsWith(".png")) {
            throw new IllegalArgumentException("Invalid file type. Only .jpg or .png allowed.");
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
    }
}


