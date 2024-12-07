package Backend.user;

import Backend.content.Post;
import Backend.content.Story;
import Backend.friends.FriendRequest;
import static Backend.user.UserFactory.createUser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    /* public static List<User> loadUsers(String filePath) {

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
    }*/
    public static List<User> loadUsers(String filePath) {
        List<User> users = new ArrayList<>();

        try {
            // Check if the file exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist. Returning an empty user list.");
                return users;
            }

            // Read file content as a single string
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the content as a JSON array
            JSONArray jsonArray = new JSONArray(fileContent);

            // Process each JSON object into a User
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                // Use the provided createUser method to create User objects
                User user = createUser(obj);
                users.add(user);
            }

            System.out.println("Users loaded successfully from " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        return users;
    }

    public static void saveUsers(List<User> users) {
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
                obj.put("Bio", user.getBio());
                obj.put("ProfilePhoto", user.getProfilePhoto());
                obj.put("CoverPhoto", user.getCoverPhoto());
                // Add friends
                JSONArray friendsArray = new JSONArray();
                if(user.getFriends()!= null ){
                for (User friend : user.getFriends()) {
                    JSONObject friendObj = new JSONObject();
                    friendObj.put("FriendId", friend.getUserId());
                    friendsArray.put(friendObj);
                }
                obj.put("friends", friendsArray);
                }

                // Add blocked users
                if(user.getBlocked()!=null){
                JSONArray blockedArray = new JSONArray();
                for (User blocked : user.getBlocked()) {
                    JSONObject blockedObj = new JSONObject();
                    blockedObj.put("BlockedId", blocked.getUserId());
                    blockedArray.put(blockedObj);
                }
                obj.put("blocked", blockedArray);
                }

                // Add posts
                JSONArray postArray = new JSONArray();
                if(user.getPosts()!=null){
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
                }

                
                // Add stories
                if(user.getStories()!=null){
                JSONArray storyArray = new JSONArray();
                for (Story story : user.getStories()) {
                    JSONObject storyObj = new JSONObject();
                    storyObj.put("contentId", story.getContentId());
                    storyObj.put("authorId", story.getAuthorId());
                    storyObj.put("contentText", story.getContentText());
                    storyObj.put("timestamp", story.getTimestamp().toString());
                    storyObj.put("imagePath", story.getImagePath());
                    storyObj.put("type", story.getType());
                    postArray.put(storyObj);  // This should be storyArray.put() instead of postArray.put()
                }
                obj.put("Story", storyArray);
                }

                // Add the sent requests
                if(user.getStatus()!=null){
                JSONArray sentRequestArray = new JSONArray();
                for (FriendRequest request : user.getSentRequests()) {
                    JSONObject sentObj = new JSONObject();
                    sentObj.put("receiverId", request.getReceiver());
                    sentObj.put("status", request.getStatus().toString());
                    sentRequestArray.put(sentObj);
                }
                obj.put("sentRequest", sentRequestArray);
                }

                // Add the received requests
                if(user.getReceivedRequests()!=null){
                JSONArray receiveRequestArray = new JSONArray();
                for (FriendRequest request : user.getReceivedRequests()) {
                    JSONObject receivedObj = new JSONObject();
                    receivedObj.put("senderId", request.getSender());
                    receivedObj.put("status", request.getStatus().toString());
                    receiveRequestArray.put(receivedObj);
                }
                obj.put("receivedRequest", receiveRequestArray);
                }

                // Add the user object to the json array
                jsonArray.put(obj);
            }

            // Write array contents into the file
            try (Writer writer = new FileWriter("Users.json")) {
                writer.write(jsonArray.toString(4)); // Pretty print with an indent of 4 spaces
                System.out.println("Users saved successfully to " + "Users.json");
            }
        } catch (IOException e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
    /*public static void saveUsers(List<User> users, String filePath) {
    try {
        JSONArray jsonArray = new JSONArray();

        for (User  user : users) {
            JSONObject obj = new JSONObject();
            obj.put("userId", user.getUserId());
            obj.put("email", user.getEmail());
            obj.put("username", user.getUsername());
            obj.put("dateOfBirth", user.getDateOfBirth() != null ? user.getDateOfBirth().toString() : ""); // Handle null dateOfBirth
            obj.put("status", user.getStatus().toString());
            obj.put("Bio", user.getBio() != null ? user.getBio() : "");  // Ensure Bio is added as an empty string if null
            obj.put("ProfilePhoto", user.getProfilePhoto() != null ? user.getProfilePhoto() : "");  // Handle ProfilePhoto
            obj.put("CoverPhoto", user.getCoverPhoto() != null ? user.getCoverPhoto() : "");  // Handle CoverPhoto

            // Add friends
            JSONArray friendsArray = new JSONArray();
            for (User  friend : user.getFriends()) {
                JSONObject friendObj = new JSONObject();
                friendObj.put("FriendId", friend.getUserId());
                friendsArray.put(friendObj);
            }
            obj.put("friends", friendsArray);

            // Add blocked users
            JSONArray blockedArray = new JSONArray();
            for (User  blocked : user.getBlocked()) {
                JSONObject blockedObj = new JSONObject();
                blockedObj.put("BlockedId", blocked.getUserId());
                blockedArray.put(blockedObj);
            }
            obj.put("blocked", blockedArray);

            // Add posts
            JSONArray postArray = new JSONArray();
            for (Post post : user.getPosts()) {
                JSONObject postObj = new JSONObject();
                postObj.put("contentId", post.getContentId());
                postObj.put("authorId", post.getAuthorId());
                postObj.put("contentText", post.getContentText());
                postObj.put("timestamp", post.getTimestamp() != null ? post.getTimestamp().toString() : ""); // Handle null timestamp
                postObj.put("imagePath", post.getImagePath() != null ? post.getImagePath() : "");  // Handle empty image path
                postObj.put("type", post.getType());
                postArray.put(postObj);
            }
            obj.put("posts", postArray); // Changed from "Post" to "posts"

            // Add stories
            JSONArray storyArray = new JSONArray();
            for (Story story : user.getStories()) {
                JSONObject storyObj = new JSONObject();
                storyObj.put("contentId", story.getContentId());
                storyObj.put("authorId", story.getAuthorId());
                storyObj.put("contentText", story.getContentText());
                storyObj.put("timestamp", story.getTimestamp() != null ? story.getTimestamp().toString() : ""); // Handle null timestamp
                storyObj.put("imagePath", story.getImagePath() != null ? story.getImagePath() : "");  // Handle empty image path
                storyObj.put("type", story.getType());
                storyObj.put("expiryTime", story.getExpiryTime() != null ? story.getExpiryTime().toString() : "");  // Handle expiryTime (may be null)
                storyArray.put(storyObj);
            }
            obj.put("stories", storyArray); // Changed from "Story" to "stories"

            // Add the sent requests
            JSONArray sentRequestArray = new JSONArray();
            for (FriendRequest request : user.getSentRequests()) {
                JSONObject sentObj = new JSONObject();
                sentObj.put("receiverId", request.getReceiver());
                sentObj.put("status", request.getStatus().toString());
                sentRequestArray.put(sentObj);
            }
            obj.put("sentRequests", sentRequestArray); // Changed from "sentRequest" to "sentRequests"

            // Add the received requests
            JSONArray receiveRequestArray = new JSONArray();
            for (FriendRequest request : user.getReceivedRequests()) {
                JSONObject receivedObj = new JSONObject();
                receivedObj.put("senderId", request.getSender());
                receivedObj .put("status", request.getStatus().toString());
                receiveRequestArray.put(receivedObj);
            }
            obj.put("receivedRequests", receiveRequestArray); // Changed from "receivedRequest" to "receivedRequests"

            jsonArray.put(obj);
        }

        // Write the JSON array to the specified file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString());
            file.flush();
        }
    } catch (IOException e) {
        e.printStackTrace(); // Improved error handling
    }
}*/

}
