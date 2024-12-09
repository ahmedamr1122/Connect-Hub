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
  //  private List<User> users;
    
    public ProfileUpdater(FileManagement fileManagement ) {
        this.validations = new Validations();
        this.findUser = new FindUser();  // Ensure FindUser is instantiated
        this.fileManagement = fileManagement;  // Ensure FileManagement is instantiated
        //this.users = users;
    }

    // Update user bio
    public void updateBio(String userId, String newBio,List <User> users) throws IOException {
        validations.validateBio(newBio); // Call validation method
        User user = findUser.findUserById(userId,users);
        if (user != null) {
            user.setBio(newBio);
             fileManagement.saveUsers(users);
            System.out.println("User bio updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

    // Update profile photo
    public void updateProfilePhoto(String userId, String newProfilePhotoPath,List <User> users) throws IOException {
        validations.validatePhotoPath(newProfilePhotoPath); // Validate the photo path
        User user = findUser.findUserById(userId,users);
        if (user != null) {
            user.setProfilePhoto(newProfilePhotoPath);
              fileManagement.saveUsers(users);
            System.out.println("Profile photo updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

    // Update cover photo
    public void updateCoverPhoto(String userId, String newCoverPhotoPath,List <User> users) throws IOException {
        validations.validatePhotoPath(newCoverPhotoPath); // Validate the cover photo path
        User user = findUser.findUserById(userId,users);
        if (user != null) {
            user.setCoverPhoto(newCoverPhotoPath);
              fileManagement.saveUsers(users);
            System.out.println("Cover photo updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

    // Update password
    public void updatePassword(String userId, String newPassword,List <User> users) throws IOException {
        validations.validatePassword(newPassword); // Validate the password
        User user = findUser.findUserById(userId,users);
        if (user != null) {
            user.setPassword(newPassword);
            fileManagement.saveUsers(users);
            System.out.println("Password updated successfully.");
        } else {
            System.err.println("User not found.");
        }
    }

}
