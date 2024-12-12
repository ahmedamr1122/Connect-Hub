package Backend.content;

import Backend.content.Content;
import Backend.user.FindUser;
import Backend.user.User;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContentService {

    FindUser findUser = new FindUser();

    public void createPost(User user, String contentText, String imagePath, List<User> users) {
        String postId = "p" + (user.getPosts().size() + 1); // Generate unique ID based on the user's post count
        Post post = new Post(postId, user.getUserId(), contentText, LocalDateTime.now(), imagePath);
        User userFound = findUser.findUserById(user.getUserId(), users);
        userFound.getPosts().add(post); // Add the post directly to the user's post list
    }

    public void createStory(User user, String contentText, String imagePath, List<User> users) {
        String storyId = "s" + (user.getStories().size() + 1); // Generate unique ID based on the user's story count
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(24); // Set story expiry to 24 hours from now
        Story story = new Story(storyId, user.getUserId(), contentText, LocalDateTime.now(), imagePath, expiryTime);
        User userFound = findUser.findUserById(user.getUserId(), users);
        userFound.getStories().add(story); // Add the story directly to the user's story list
    }

    public void deleteExpiredStories(User user) throws IOException {
        List<Story> stories = user.getStories();
        for (int i = 0; i < stories.size(); i++) {
            if (stories.get(i).getExpiryTime().isBefore(LocalDateTime.now())) {
                stories.remove(i);
                i--; // Adjust index after removal
            }
        }
    }

    /**
     * Fetches all posts from the user's friends.
     *
     * @param user The user whose friends' posts are being fetched.
     * @return A list of posts from all friends.
     */
    public ArrayList<Post> getFriendPosts(User user, List<User> users) {
        ArrayList<Post> friendPosts = new ArrayList<>();
        for (User friend : user.getFriends()) {
            FindUser find = new FindUser();
            User user1 = find.findUserById(friend.getUserId(), users);
            friendPosts.addAll(user1.getPosts());
        }
        friendPosts.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp())); // Sort posts by timestamp (newest first)
        return friendPosts;
    }

    /**
     * Fetches all active stories from the user's friends.
     *
     * @param user The user whose friends' stories are being fetched.
     * @return A list of active stories from all friends.
     */
    public ArrayList<Story> getFriendStories(User user, List<User> users) {
        ArrayList<Story> friendStories = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (User friend : user.getFriends()) {
            FindUser find = new FindUser();
            User user1 = find.findUserById(friend.getUserId(), users);
            for (Story story : user1.getStories()) {
                if (story.getExpiryTime().isAfter(now)) {
                    friendStories.add(story); // Add only active stories
                }
            }
        }

        return friendStories;
    }
}
