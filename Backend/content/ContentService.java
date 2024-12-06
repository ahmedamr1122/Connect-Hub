package Backend.content;

import Backend.content.Content;
import Backend.user.User;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContentService {

    public void createPost(User user, String contentText, String imagePath) {
        String postId = "p" + (user.getPosts().size() + 1); // Generate unique ID based on the user's post count
        Post post = new Post(postId, user.getUserId(), contentText, LocalDateTime.now(), imagePath);
        user.getPosts().add(post); // Add the post directly to the user's post list
    }

    public void createStory(User user, String contentText, String imagePath) {
        String storyId = "s" + (user.getStories().size() + 1); // Generate unique ID based on the user's story count
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(24); // Set story expiry to 24 hours from now
        Story story = new Story(storyId, user.getUserId(), contentText, LocalDateTime.now(), imagePath, expiryTime);
        user.getStories().add(story); // Add the story directly to the user's story list
    }
    
    public void deleteExpiredStories(User user) throws IOException {
        List<Story> stories = user.getStories();
        for (int i = 0; i < stories.size(); i++)
        {
            if (stories.get(i).getExpiryTime().isBefore(LocalDateTime.now())) 
            {
                stories.remove(i);
                i--; // Adjust index after removal
            }
        }
    }
     /**
     * Fetches all posts from the user's friends.
     * @param user The user whose friends' posts are being fetched.
     * @return A list of posts from all friends.
     */
     public ArrayList<Post> getFriendPosts(User user) {
        ArrayList<Post> friendPosts = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friendPosts.addAll(friend.getPosts());
        }
        friendPosts.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp())); // Sort posts by timestamp (newest first)
        return friendPosts;
    }
     
     /**
     * Fetches all active stories from the user's friends.
     * @param user The user whose friends' stories are being fetched.
     * @return A list of active stories from all friends.
     */
    public ArrayList<Story> getFriendStories(User user) {
        ArrayList<Story> friendStories = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (User friend : user.getFriends()) {
            for (Story story : friend.getStories()) {
                if (story.getExpiryTime().isAfter(now)) {
                    friendStories.add(story); // Add only active stories
                }
            }
        }

        return friendStories;
    }
}
