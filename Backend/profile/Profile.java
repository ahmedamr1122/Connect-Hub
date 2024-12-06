package Backend.profile;

import Backend.content.Post;
import Backend.content.Story;
import Backend.user.User;
import Backend.user.FindUser;
import Backend.user.FileManagement;
import Backend.user.UserAccountManagement;
import java.io.IOException;
import java.util.List;

public class Profile implements ProfileService {

    private final ProfileUpdater profileUpdater;
    private final FetcherService fetcherService;

    // Constructor now takes FetcherService and ProfileUpdater for dependency injection
    public Profile(ProfileUpdater profileUpdater, FetcherService fetcherService) {
        this.profileUpdater = profileUpdater;
        this.fetcherService = fetcherService;
    }

    // Update bio
    @Override
    public void updateBio(String userId, String newBio) throws IOException {
        profileUpdater.updateBio(userId, newBio);
    }

    // Update profile photo
    @Override
    public void updateProfilePhoto(String userId, String newProfilePhotoPath) throws IOException {
        profileUpdater.updateProfilePhoto(userId, newProfilePhotoPath);
    }

    // Update cover photo
    @Override
    public void updateCoverPhoto(String userId, String newCoverPhotoPath) throws IOException {
        profileUpdater.updateCoverPhoto(userId, newCoverPhotoPath);
    }

    // Update password
    @Override
    public void updatePassword(String userId, String newPassword) throws IOException {
        profileUpdater.updatePassword(userId, newPassword);
    }

    // Fetch user's posts
    public List<Post> fetchUserPosts(String userId) throws IOException {
        return fetcherService.fetchUserPosts(userId);  // Fetch posts using FetcherService
    }

    // Fetch user's stories
    public List<Story> fetchUserStories(String userId) throws IOException {
        return fetcherService.fetchUserStories(userId);  // Fetch stories using FetcherService
    }

    // Fetch user's friends
    public List<User> fetchUserFriends(String userId) throws IOException {
        return fetcherService.fetchUserFriends(userId);  // Fetch friends using FetcherService
    }
}
