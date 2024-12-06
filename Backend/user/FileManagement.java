package Backend.user;

import Backend.content.Post;
import Backend.content.Story;
import Backend.friends.FriendRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileManagement {

    private String filePath;

    public FileManagement(String filePath) {
        this.filePath = filePath;
    }

    // load users from a JSON file
    public static List<User> loadUsers(String filePath) {

        List<User> users = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist. Returning an empty user list.");
                return users;
            }

            try (Reader reader = new FileReader(filePath)) {
                JSONArray jsonArray = new JSONArray(new BufferedReader(reader).lines().reduce("", String::concat));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    User user = UserFactory.createUser(obj);

                    // Add user to the users list
                    users.add(user);
                }

                System.out.println("Users loaded successfully from " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return users;
    }

    public void saveUsers(List<User> users) {
        try {
            JSONArray jsonArray = new JSONArray();

            for (User user : users) {
                JSONObject obj = new JSONObject();
                obj.put("userId", user.getUserId());
                obj.put("email", user.getEmail());
                obj.put("username", user.getUsername());
                obj.put("password", user.getPassword());
                obj.put("dateOfBirth", user.getDateOfBirth().toString());
                obj.put("status", user.getStatus().toString());
                obj.put("Bio", user.getBio().toString());
                obj.put("ProfilePhoto", user.getProfilePhoto().toString());
                obj.put("CoverPhoto", user.getCoverPhoto().toString());

                // Add friends
                JSONArray friendsArray = new JSONArray();
                for (User friend : user.getFriends()) {
                    JSONObject friendObj = new JSONObject();
                    friendObj.put("FriendId", friend.getUserId());
                    friendsArray.put(friendObj);
                }
                obj.put("friends", friendsArray);

                // Add blocked users
                JSONArray blockedArray = new JSONArray();
                for (User blocked : user.getBlocked()) {
                    JSONObject blockedObj = new JSONObject();
                    blockedObj.put("BlockedId", blocked.getUserId());
                    blockedArray.put(blockedObj);
                }
                obj.put("blocked", blockedArray);

                // Add posts
                JSONArray postArray = new JSONArray();
                for (Post post : user.getPosts()) {
                    JSONObject postobj = new JSONObject();
                    postobj.put("contentId", post.getContentId());
                    postobj.put("authorId", post.getAuthorId());
                    postobj.put("contentText", post.getContentText());
                    postobj.put("timestamp", post.getTimestamp().toString());
                    postobj.put("imagePath", post.getImagePath());
                    postobj.put("type", post.getType());
                    postArray.put(postobj);
                }
                obj.put("Post", postArray);

                // Add stories
                JSONArray storyArray = new JSONArray();
                for (Story story : user.getStories()) {
                    JSONObject storyObj = new JSONObject();
                    storyObj.put("contentId", story.getContentId());
                    storyObj.put("authorId", story.getAuthorId());
                    storyObj.put("contentText", story.getContentText());
                    storyObj.put("timestamp", story.getTimestamp().toString());
                    storyObj.put("imagePath", story.getImagePath());
                    storyObj.put("type", story.getType());
                    postArray.put(storyObj);
                }
                obj.put("Story", storyArray);

                // Add the sent requests
                JSONArray sentRequestArray = new JSONArray();
                for (FriendRequest request : user.getSentRequests()) {
                    JSONObject sentObj = new JSONObject();
                    sentObj.put("receiverId", request.getReceiver());
                    sentObj.put("status", request.getStatus().toString());
                    sentRequestArray.put(sentObj);
                }
                obj.put("sentRequest", sentRequestArray);
                
                // Add the received requests
                JSONArray receiveRequestArray = new JSONArray();
                for (FriendRequest request : user.getReceivedRequests()) {
                    JSONObject receivedObj = new JSONObject();
                    receivedObj.put("senderId", request.getSender());
                    receivedObj.put("status", request.getStatus().toString());
                    receiveRequestArray.put(receivedObj);
                }
                obj.put("receivedRequest", receiveRequestArray);

                // Add the user object to the json array
                jsonArray.put(obj);

            }

            // Write array contents into the file
            try (Writer writer = new FileWriter(filePath)) {
                writer.write(jsonArray.toString(4)); // Pretty print with an indent of 4 spaces
                System.out.println("Users saved successfully to " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

}
