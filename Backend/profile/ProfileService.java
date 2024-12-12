package Backend.profile;

import Backend.user.User;
import java.io.IOException;
import java.util.List;

public interface ProfileService {
    void updateBio(String userId, String newBio,List<User> users) throws IOException;
    void updateProfilePhoto(String userId, String newProfilePhotoPath,List<User> users) throws IOException;
    void updateCoverPhoto(String userId, String newCoverPhotoPath,List<User> users) throws IOException;
    void updatePassword(String userId, String newPassword,List<User> users) throws IOException;
}
