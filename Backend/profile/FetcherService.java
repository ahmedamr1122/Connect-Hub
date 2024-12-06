package Backend.profile;

import Backend.content.Post;
import Backend.content.Story;
import Backend.user.User;
import Backend.user.FindUser;
import java.io.IOException;
import java.util.List;

public class FetcherService {

    private final FindUser findUser;

    public FetcherService(FindUser findUser) {
        this.findUser = findUser;
    }

    // Fetch user's posts
    public List<Post> fetchUserPosts(String userId) throws IOException {
        User user = findUser.findUserById(userId);
      if (user == null) {
    return List.of(); // Return empty list
}
return user.getPosts();

    }

    // Fetch user's stories
    public List<Story> fetchUserStories(String userId) throws IOException {
        User user = findUser.findUserById(userId);
        if (user == null) {
    return List.of(); // Return empty list 
}
return user.getStories();

    }

    // Fetch user's friends
    public List<User> fetchUserFriends(String userId) throws IOException {
        User user = findUser.findUserById(userId);
       if (user == null) {
    return List.of(); // Return empty list 
}
return user.getFriends();

    }
}
