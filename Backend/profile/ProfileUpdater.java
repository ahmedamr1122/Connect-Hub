package Backend.profile;
import Backend.user.FindUser;
import Backend.user.User;
import Backend.user.FileManagement;
import Backend.user.UserAccountManagement;
import Backend.user.Validations;
import java.io.IOException;
import java.util.List;

public class ProfileUpdater {

    private FindUser findUser;
    private Validations validations;
    private FileManagement fileManagement;
    private UserAccountManagement UAM;
    private List<User> users;
    
    public ProfileUpdater() {
        this.validations = new Validations();
        this.findUser = new FindUser();  // Ensure FindUser is instantiated
        this.fileManagement = new FileManagement("Users.json");  // Ensure FileManagement is instantiated
    }

    // Update user bio
    public void updateBio(String userId, String newBio) throws IOException {
        validations.validateBio(newBio); // Call validation method
        User user = findUser.findUserById(userId);
        if (user != null) {
            user.setBio(newBio);
             fileManagement.saveUsers(findUser.getUsers());
            System.out.println("User bio updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

    // Update profile photo
    public void updateProfilePhoto(String userId, String newProfilePhotoPath) throws IOException {
        validations.validatePhotoPath(newProfilePhotoPath); // Validate the photo path
        User user = findUser.findUserById(userId);
        if (user != null) {
            user.setProfilePhoto(newProfilePhotoPath);
              fileManagement.saveUsers(findUser.getUsers());
            System.out.println("Profile photo updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

    // Update cover photo
    public void updateCoverPhoto(String userId, String newCoverPhotoPath) throws IOException {
        validations.validatePhotoPath(newCoverPhotoPath); // Validate the cover photo path
        User user = findUser.findUserById(userId);
        if (user != null) {
            user.setCoverPhoto(newCoverPhotoPath);
              fileManagement.saveUsers(findUser.getUsers());
            System.out.println("Cover photo updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

    // Update password
    public void updatePassword(String userId, String newPassword) throws IOException {
        validations.validatePassword(newPassword); // Validate the password
        User user = findUser.findUserById(userId);
        if (user != null) {
            user.setPassword(newPassword);
            fileManagement.saveUsers(findUser.getUsers());
            System.out.println("Password updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

}
