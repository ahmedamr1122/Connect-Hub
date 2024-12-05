/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IProfileManagement {
    void updateBio(String userId, String newBio) throws IOException;
    void updateProfilePhoto(String userId, String newProfilePhotoPath) throws IOException;
    void updateCoverPhoto(String userId, String newCoverPhotoPath) throws IOException;
    void updatePassword(String userId, String newPassword) throws IOException;
    List<Post> fetchUserPosts(String userId) throws IOException;
    List<Friend> fetchUserFriends(String userId) throws IOException;
}
