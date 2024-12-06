package Backend;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Profile implements IProfileService {

    private final ProfileUpdater profileUpdater;
    private final IUserAccountManagement userAccountManagement;
    private final FriendFetcher friendFetcher;
    private final ContentFetcher contentFetcher;
    private final FriendSuggestionService friendSuggestionService;

    public Profile(ProfileUpdater profileUpdater,IUserAccountManagement userAccountManagement) {
        this.profileUpdater = profileUpdater;
        this.userAccountManagement = userAccountManagement;
        this.friendFetcher = new FriendFetcher();  // Create an instance of FriendFetcher
        this.contentFetcher = new ContentFetcher();  // Create an instance of ContentFetcher
        this.friendSuggestionService = new FriendSuggestionService( userAccountManagement, friendFetcher);  // Instantiate FriendSuggestionService
    }

    // Update bio using ProfileUpdater
    @Override
    public void updateBio(String userId, String newBio) throws IOException {
        profileUpdater.updateBio(userId, newBio);
    }

    // Fetch posts for the user
    public List<Post> fetchUserPosts(String userId) throws IOException {
        return contentFetcher.fetchUserPosts(userId);  // Use ContentFetcher for fetching posts
    }

    // Fetch friends for the user
    public List<Friend> fetchUserFriends(String userId) throws IOException {
        return friendFetcher.fetchUserFriends(userId);  // Use FriendFetcher for fetching friends
    }

    // Fetch friend suggestions (users not yet friends)
    public List<FriendSuggestion> fetchFriendSuggestions(String userId) throws IOException {
        return friendSuggestionService.fetchFriendSuggestions(userId);  // Use FriendSuggestionService for fetching friend suggestions
    }

    @Override
    public void updateProfilePhoto(String userId, String newProfilePhotoPath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateCoverPhoto(String userId, String newCoverPhotoPath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updatePassword(String userId, String newPassword) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
