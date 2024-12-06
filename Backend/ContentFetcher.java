package Backend;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContentFetcher implements IContentFetcher {

    private final String filePath = "Posts.json";  // Path to posts file

    @Override
    public List<Post> fetchUserPosts(String userId) throws IOException {
        List<Post> posts = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) return posts;  // Return empty if file doesn't exist

        try (Reader reader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray postsArray = new JSONArray(tokener);

            // Iterate through the posts and filter by userId
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject postObject = postsArray.getJSONObject(i);
                String postUserId = postObject.getString("authorId");

                if (postUserId.equals(userId)) {
                    Post post = new Post(
                        postObject.getString("postId"),
                        postUserId,
                        postObject.getString("contentText"),
                        LocalDateTime.parse(postObject.getString("timestamp")),
                        postObject.optString("imagePath", null)
                    );
                    posts.add(post);
                }
            }
        }
        return posts;
    }
}
