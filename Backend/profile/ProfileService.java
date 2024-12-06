package Backend.profile;

import java.io.IOException;

public interface ProfileService {
    void updateBio(String userId, String newBio) throws IOException;
    void updateProfilePhoto(String userId, String newProfilePhotoPath) throws IOException;
    void updateCoverPhoto(String userId, String newCoverPhotoPath) throws IOException;
    void updatePassword(String userId, String newPassword) throws IOException;
}
