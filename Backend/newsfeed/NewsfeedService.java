/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.newsfeed;

import Backend.content.ContentService;
import Backend.content.Post;
import Backend.content.Story;
import Backend.user.User;
import java.util.List;

/**
 *
 * @author Jana
 */
public class NewsfeedService {
    private ContentService contentService; // Dependency on ContentService
    
    public NewsfeedService(ContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * Fetches posts from friends for the current user's newsfeed.
     * @param user The user whose friends' posts are being fetched.
     * @return A list of posts from friends.
     */
    public List<Post> getNewsfeedPosts(User user) {
        return contentService.getFriendPosts(user); // Delegate to ContentService
    }

    /**
     * Fetches active stories from friends for the current user's newsfeed.
     * @param user The user whose friends' stories are being fetched.
     * @return A list of active stories from friends.
     */
    public List<Story> getNewsfeedStories(User user) {
        return contentService.getFriendStories(user); // Delegate to ContentService
    }

    /**
     * Adds a new post for the user.
     * @param user The user creating the post.
     * @param contentText The text of the post.
     * @param imagePath The path to the image associated with the post (optional).
     */
    public void addPost(User user, String contentText, String imagePath) {
        contentService.createPost(user, contentText, imagePath); // Delegate to ContentService
    }

    /**
     * Adds a new story for the user.
     * @param user The user creating the story.
     * @param contentText The text of the story.
     * @param imagePath The path to the image associated with the story (optional).
     */
    public void addStory(User user, String contentText, String imagePath) {
        contentService.createStory(user, contentText, imagePath); // Delegate to ContentService
    }
}
