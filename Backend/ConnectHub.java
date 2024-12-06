/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Backend;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class ConnectHub {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     IUserAccountManagement userAccountManagement = new UserAccountManagement();
        ProfileUpdater profileUpdater = new ProfileUpdater(userAccountManagement);
        FriendFetcher friendFetcher = new FriendFetcher();  // Create an instance of FriendFetcher

        // Create Profile instance with all dependencies (ProfileUpdater, IUserAccountManagement, FriendFetcher)
        Profile profile;
        profile = new Profile(profileUpdater,userAccountManagement);

        try {
            // 1. Test updating bio
            String userId = "100";  // Assuming we have a user with this ID
            String newBio = "This is a new bio!";
            profile.updateBio(userId, newBio);
            System.out.println("Bio updated successfully for user: " + userId);

            // 2. Test updating profile photo
            String newProfilePhotoPath = "/path/to/profile/photo.jpg";
            profile.updateProfilePhoto(userId, newProfilePhotoPath);
            System.out.println("Profile photo updated successfully for user: " + userId);

            // 3. Test updating cover photo
            String newCoverPhotoPath = "/path/to/cover/photo.jpg";
            profile.updateCoverPhoto(userId, newCoverPhotoPath);
            System.out.println("Cover photo updated successfully for user: " + userId);

            // 4. Test updating password
            String newPassword = "newStrongPassword123";
            profile.updatePassword(userId, newPassword);
            System.out.println("Password updated successfully for user: " + userId);

            // 5. Test fetching user posts
            List<Post> userPosts = profile.fetchUserPosts(userId);
            System.out.println("User Posts: ");
            for (Post post : userPosts) {
                System.out.println("Post ID: " + post.getContentId() + " | Content: " + post.getContentText());
            }

            // 6. Test fetching user friends
            List<Friend> userFriends = profile.fetchUserFriends(userId);
            System.out.println("User Friends: ");
            for (Friend friend : userFriends) {
                System.out.println("Friend ID: " + friend.getFriendId() + " | Status: " + friend.getStatus());
            }

        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }}

