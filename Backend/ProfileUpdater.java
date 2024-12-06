
package Backend;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author asus
 */
public class ProfileUpdater {
 

    private final IUserAccountManagement userAccountManagement;
    private final Validations validations;

    public ProfileUpdater(IUserAccountManagement userAccountManagement) {
        this.userAccountManagement = userAccountManagement;
        this.validations = new Validations();
    }

    public void updateBio(String userId, String newBio) throws IOException {
        validations.validateBio(newBio);  // Call validation method
        updateUserField(userId, user -> user.setBio(newBio));
        System.out.println("User bio updated successfully.");
    }

    public void updateProfilePhoto(String userId, String newProfilePhotoPath) throws IOException {
        validations.validatePhotoPath(newProfilePhotoPath);  // Call validation method
        updateUserField(userId, user -> user.setProfilePhoto(newProfilePhotoPath));
        System.out.println("Profile photo updated successfully.");
    }

    public void updateCoverPhoto(String userId, String newCoverPhotoPath) throws IOException {
        validations.validatePhotoPath(newCoverPhotoPath);  // Call validation method
        updateUserField(userId, user -> user.setCoverPhoto(newCoverPhotoPath));
        System.out.println("Cover photo updated successfully.");
    }

    public void updatePassword(String userId, String newPassword) throws IOException {
        validations.validatePassword(newPassword);  // Call validation method
        updateUserField(userId, user -> user.setPassword(newPassword));
        System.out.println("Password updated successfully.");
    }

    private void updateUserField(String userId, UserUpdater updater) throws IOException {
        List<User> users = userAccountManagement.loadUsers("Users.json");
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                updater.update(user);
                break;
            }
        }
        userAccountManagement.saveUsers(users, "Users.json");
    }

    @FunctionalInterface
    private interface UserUpdater {
        void update(User user);
    }
}
  

