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
    private static FileManagement instance;
    
    public FileManagement() {
        this.filePath = "Users.json";
    }

    public static FileManagement getInstance() {
        if (instance == null) {
            instance = new FileManagement();
        }
        return instance;
    }

    public List<User> loadUsers() {
    List<User> users = new ArrayList<>();

    try {
        // Check if the file exists
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist: " + filePath);
            return users;  // Return empty list if file does not exist
        }

        System.out.println("Loading users from file: " + filePath);

        // Read file content as a single string
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        // Check if the file content is empty
        if (fileContent.trim().isEmpty()) {
            System.out.println("File content is empty. Returning an empty user list.");
            return users;
        }

        // Parse the content as a JSON array
        JSONArray jsonArray = new JSONArray(fileContent);

        // Process each JSON object into a User
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            try {
                // Use the provided createUser method to create User objects
                User user = createUser(obj);
                users.add(user);  // Add the user to the list
                System.out.println("Loaded user: " + user.getUsername());
            } catch (Exception e) {
                System.err.println("Error creating user from JSON object at index " + i + ": " + e.getMessage());
            }
        }

        System.out.println("Users loaded successfully. Total users: " + users.size());
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
                JSONArray storyArray = new JSONArray();
                if(user.getStories()!=null){
                for (Story story : user.getStories()) {
                    JSONObject storyObj = new JSONObject();
                    storyObj.put("contentId", story.getContentId());
                    storyObj.put("authorId", story.getAuthorId());
                    storyObj.put("contentText", story.getContentText());
                    storyObj.put("timestamp", story.getTimestamp().toString());
                    storyObj.put("imagePath", story.getImagePath());
                    storyObj.put("type", story.getType());
                    storyArray.put(storyObj);  // This should be storyArray.put() instead of postArray.put()
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
    

}
