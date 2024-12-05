package Backend;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ProfileManagement implements IProfileManagement{

    private final UserAccountManagement userAccountManagement;
    private String bio;
    private String profilePhotoPath;
    private String coverPhotoPath;
    
 public ProfileManagement(IUserAccountManagement userAccountManagement) {
     
        this.userAccountManagement = (UserAccountManagement) userAccountManagement;
 }

    public String getBio() {
        return bio;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    // Update user bio
    @Override
    public void updateBio(String userId, String newBio) throws IOException {
        // Validate the bio input
        if (newBio == null || newBio.trim().isEmpty()) {
            throw new IllegalArgumentException("Bio cannot be empty.");
        }

        // Set the new bio
        this.bio = newBio;
        // Load the Users and find the user to update the bio
        List<User> users = userAccountManagement.loadUsers("Users.json");
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                break;
            }
        }

        // Save the updated users list
        userAccountManagement.saveUsers(users, "Users.json");
        System.out.println("User bio updated successfully.");
    }

    // Update profile photo
    @Override
    public void updateProfilePhoto(String userId, String newProfilePhotoPath) throws IOException {
        // Validate the photo path
        if (newProfilePhotoPath == null || newProfilePhotoPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Profile photo path cannot be empty.");
        }
        if (!newProfilePhotoPath.endsWith(".jpg") && !newProfilePhotoPath.endsWith(".png")) {
            throw new IllegalArgumentException("Invalid file type for profile photo. Only .jpg or .png files are allowed.");
        }

        // Set the new profile photo
        this.profilePhotoPath = newProfilePhotoPath;

       

        // Load the Users and find the user to update the profile photo
        List<User> users = userAccountManagement.loadUsers("Users.json");
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                break;
            }
        }
        // Save the updated users list
        userAccountManagement.saveUsers(users, "Users.json");
        System.out.println("Profile photo updated successfully.");
    }

    // Update cover photo
    @Override
    public void updateCoverPhoto(String userId, String newCoverPhotoPath) throws IOException {
        // Validate the cover photo path
        if (newCoverPhotoPath == null || newCoverPhotoPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Cover photo path cannot be empty.");
        }
        if (!newCoverPhotoPath.endsWith(".jpg") && !newCoverPhotoPath.endsWith(".png")) {
            throw new IllegalArgumentException("Invalid file type for cover photo. Only .jpg or .png files are allowed.");
        }

        this.coverPhotoPath = newCoverPhotoPath;


        // Load Users and find the user to update the cover photo
        List<User> users = userAccountManagement.loadUsers("Users.json");
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                break;
            }
        }

        // Save the updated users list
        userAccountManagement.saveUsers(users, "Users.json");
        System.out.println("Cover photo updated successfully.");
    }

    @Override
  public void updatePassword(String userId, String newPassword) throws IOException {
    // Validate the password
    if (newPassword == null || newPassword.trim().isEmpty()) {
        throw new IllegalArgumentException("Password cannot be empty.");
    }
    if (newPassword.length() < 8) {
        throw new IllegalArgumentException("Password must be at least 8 characters long.");
    }
    // Load Users and find the user to update the password
    List<User> users = userAccountManagement.loadUsers("Users.json");
   for (User user : users) {
        if (user.getUserId().equals(userId)) {
            // Settin the new password
            user.setPassword(newPassword);  
            break;
        }
    }

    // Save the updated users list
    userAccountManagement.saveUsers(users, "Users.json");
    System.out.println("Password updated successfully.");
}



    @Override
    public List<Post> fetchUserPosts(String userId) throws IOException {
    List<Post> posts = new ArrayList<>();
    File file = new File("Posts.json");  // Assuming posts are stored in Posts.jso

    if (!file.exists()) return posts;  // Return empty list if file doesn't exist

    try (Reader reader = new FileReader("Posts.json")) {
        JSONTokener tokener = new JSONTokener(reader);
        JSONArray postsArray = new JSONArray(tokener);

        // Go through all posts and find posts for the given userId
        for (int i = 0; i < postsArray.length(); i++) {
            JSONObject postObject = postsArray.getJSONObject(i);
            String postUserId = postObject.getString("userId");

            if (postUserId.equals(userId)) {
                // Use the Post constructor with the required fields
                Post post = new Post(
                    postObject.getString("postId"),
                    postUserId,
                    postObject.getString("content"),
                    LocalDateTime.parse(postObject.getString("timestamp")), // Assuming timestamp is stored as a string
                    postObject.optString("imagePath", null)  // Default to null if imagePath is not provided
                );
                posts.add(post);
            }
        }
    }

    return posts;
}


    // Fetch friends of the user (directly from Users.json or friends.json)
    @Override
    public List<Friend> fetchUserFriends(String userId) throws IOException {
        List<Friend> friends = new ArrayList<>();
        File file = new File("Friends.json");  // Assuming friends are stored in Friends.json

        if (!file.exists()) {
            return friends;  // Return empty list if file doesn't exist
        }
        try (Reader reader = new FileReader("Friends.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray friendsArray = new JSONArray(tokener);

            // Go through all friends and find friends for the given userId
            for (int i = 0; i < friendsArray.length(); i++) {
                JSONObject friendObject = friendsArray.getJSONObject(i);
                String friendUserId = friendObject.getString("friendId");

                if (friendUserId.equals(userId)) {
                    Friend friend = new Friend(
                            friendObject.getString("friendId"),
                            friendObject.getString("status") // Assuming status is stored in the friends data
                    );
                    friends.add(friend);
                }
            }
        }

        return friends;
    }
}
