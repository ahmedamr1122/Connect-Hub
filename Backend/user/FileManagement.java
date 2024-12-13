package Backend.user;

import Backend.Group.FindGroup;
import Backend.Group.Group;
import Backend.Group.GroupFileManagement;
import Backend.Group.groupService;
import Backend.content.Post;
import Backend.content.Story;
import Backend.friends.FriendRequest;
import Backend.friends.RequestStatus;
import Backend.notification.Notification;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileManagement {

    private String filePath;
    private static FileManagement instance;
    private GroupFileManagement groupFile;
    private groupService Service;

    public FileManagement() {
        this.filePath = "Users.json";
        groupFile = GroupFileManagement.getInstance();
        Service = groupService.getInstance();
    }

    public static FileManagement getInstance() {
        if (instance == null) {
            instance = new FileManagement();
        }
        return instance;
    }

    /*  public List<User> loadUsers() {
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
}*/
    public List<User> loadUsers() {
        Map<String, User> userMap = new HashMap<>(); // To store all users by their IDs
        List<User> users = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist: " + filePath);
                return users;
            }

            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            if (fileContent.trim().isEmpty()) {
                System.out.println("File content is empty.");
                return users;
            }

            JSONArray jsonArray = new JSONArray(fileContent);

            // First pass: Create all users with basic info
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject obj = jsonArray.getJSONObject(j);
                String userId = obj.getString("userId");
                String email = obj.optString("email", null);
                String username = obj.optString("username", null);
                String password = obj.optString("password", null);
                LocalDate dateOfBirth = obj.has("dateOfBirth") ? LocalDate.parse(obj.getString("dateOfBirth")) : null;
                Status status = obj.has("status") ? Status.valueOf(obj.getString("status")) : null;
                String bio = obj.optString("Bio", null);
                String profilePhoto = obj.optString("ProfilePhoto", null);
                String coverPhoto = obj.optString("CoverPhoto", null);

                // Parse posts
                List<Post> posts = new ArrayList<>();
                JSONArray postArray = obj.optJSONArray("Post");
                if (postArray != null) {
                    for (int i = 0; i < postArray.length(); i++) {
                        JSONObject postObj = postArray.getJSONObject(i);
                        Post post = new Post(
                                postObj.getString("contentId"),
                                postObj.getString("authorId"),
                                postObj.getString("contentText"),
                                LocalDateTime.parse(postObj.getString("timestamp")),
                                postObj.optString("imagePath", null)
                        );
                        posts.add(post);
                    }
                }

                // Parse stories
                List<Story> stories = new ArrayList<>();
                JSONArray storyArray = obj.optJSONArray("Story");
                if (storyArray != null) {
                    for (int i = 0; i < storyArray.length(); i++) {
                        JSONObject storyObj = storyArray.getJSONObject(i);
                        Story story = new Story(
                                storyObj.getString("contentId"),
                                storyObj.getString("authorId"),
                                storyObj.getString("contentText"),
                                LocalDateTime.parse(storyObj.getString("timestamp")),
                                storyObj.optString("imagePath", null),
                                LocalDateTime.parse(storyObj.getString("expiryTime"))
                        );

                        stories.add(story);
                    }
                }

                // Parse sent requests
                List<FriendRequest> sentRequests = new ArrayList<>();
                JSONArray sentRequestArray = obj.optJSONArray("sentRequest");
                if (sentRequestArray != null) {
                    for (int i = 0; i < sentRequestArray.length(); i++) {
                        JSONObject sentObj = sentRequestArray.getJSONObject(i);
                        String receiverId = sentObj.getString("receiverId");
                        RequestStatus requestStatus = RequestStatus.valueOf(sentObj.getString("status"));
                        sentRequests.add(new FriendRequest(userId, receiverId, requestStatus));
                    }
                }

                // Parse received requests
                List<FriendRequest> receivedRequests = new ArrayList<>();
                JSONArray receivedRequestArray = obj.optJSONArray("receivedRequest");
                if (receivedRequestArray != null) {
                    for (int i = 0; i < receivedRequestArray.length(); i++) {
                        JSONObject receivedObj = receivedRequestArray.getJSONObject(i);
                        String senderId = receivedObj.getString("senderId");
                        RequestStatus requestStatus = RequestStatus.valueOf(receivedObj.getString("status"));
                        receivedRequests.add(new FriendRequest(senderId, userId, requestStatus));
                    }

                }

                User user = new User(userId, email, username, password, dateOfBirth, status, bio, profilePhoto, coverPhoto);
                user.setStories(stories);
                user.setPosts(posts);
                user.setSentRequests(sentRequests);
                user.setReceivedRequests(receivedRequests);
                users.add(user);
                userMap.put(userId, user); // Add to map for later reference
            }
            // Second pass : load Groups
            Service.setAllGroups(groupFile.loadGroups(users));

            // Third pass: Populate relationships
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                User user = userMap.get(obj.getString("userId"));

                // Populate friends
                JSONArray friendsArray = obj.optJSONArray("friends");
                if (friendsArray != null) {
                    for (int j = 0; j < friendsArray.length(); j++) {
                        JSONObject friendObj = friendsArray.getJSONObject(j);
                        String friendId = friendObj.getString("FriendId");
                        User friend = userMap.get(friendId); // Resolve from map
                        if (friend != null) {
                            user.getFriends().add(friend);
                        }
                    }
                }

                // Populate blocked users
                JSONArray blockedArray = obj.optJSONArray("blocked");
                if (blockedArray != null) {
                    for (int j = 0; j < blockedArray.length(); j++) {
                        JSONObject blockedObj = blockedArray.getJSONObject(j);
                        String blockedId = blockedObj.getString("BlockedId");
                        User blockedUser = userMap.get(blockedId); // Resolve from map
                        if (blockedUser != null) {
                            user.getBlocked().add(blockedUser);
                        }
                    }
                }
        
                JSONArray ownedArray = obj.optJSONArray("owned groups");
                if (ownedArray != null) {
                    for (int j = 0; j < ownedArray.length(); j++) {
                        JSONObject ownedObj = ownedArray.getJSONObject(j);
                        String groupId = ownedObj.getString("groupId");
                        Group group = FindGroup.findGroupById(groupId);
                        if(group!=null){
                        user.getOwnedGroups().add(group);
                            
                        }
                        
                    }
                }

                // Parse joined groups
         
                JSONArray joinedArray = obj.optJSONArray("joined groups");
                if (joinedArray != null) {
                    for (int j = 0; j < joinedArray.length(); j++) {
                        JSONObject joinedObj = joinedArray.getJSONObject(j);
                        String groupId = joinedObj.getString("groupId");
                        Group group2 = FindGroup.findGroupById(groupId);
                        if(group2!=null){
                            user.getJoinedGroups().add(group2);
                        }
                    }
                }
            }
            //Groups
            // Parse owned groups

            // Parse notifications
            /*List<Notification> notification = new ArrayList<>();
        JSONArray notifArray = obj.optJSONArray("notifications");
        if (notifArray != null) {
            for (int i = 0; i < notifArray.length(); i++) {
                JSONObject notifObj = notifArray.getJSONObject(i);
                String notifId = notifObj.getString("notificationId");
                notification.add(new Notification(notifId) {});
            }
        }*/
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
                if (user.getFriends() != null) {
                    for (User friend : user.getFriends()) {
                        JSONObject friendObj = new JSONObject();
                        friendObj.put("FriendId", friend.getUserId());
                        friendsArray.put(friendObj);
                    }
                    obj.put("friends", friendsArray);
                }

                // Add blocked users
                if (user.getBlocked() != null) {
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
                if (user.getPosts() != null) {
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
                if (user.getStories() != null) {
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
                if (user.getStatus() != null) {
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
                if (user.getReceivedRequests() != null) {
                    JSONArray receiveRequestArray = new JSONArray();
                    for (FriendRequest request : user.getReceivedRequests()) {
                        JSONObject receivedObj = new JSONObject();
                        receivedObj.put("senderId", request.getSender());
                        receivedObj.put("status", request.getStatus().toString());
                        receiveRequestArray.put(receivedObj);
                    }
                    obj.put("receivedRequest", receiveRequestArray);
                }

                // Add owned groups
                JSONArray ownedArray = new JSONArray();
                if (user.getOwnedGroups() != null) {
                    for (Group owned : user.getOwnedGroups()) {
                        JSONObject ownedObj = new JSONObject();
                        ownedObj.put("groupId", owned.getGroupId());
                        ownedArray.put(ownedObj);
                    }
                    obj.put("owned groups", ownedArray);
                }

                // Add joined groups
                JSONArray joinedArray = new JSONArray();
                if (user.getJoinedGroups() != null) {
                    for (Group joined : user.getJoinedGroups()) {
                        JSONObject joinedObj = new JSONObject();
                        joinedObj.put("groupId", joined.getGroupId());
                        joinedArray.put(joinedObj);
                    }
                    obj.put("joined groups", joinedArray);
                }

                // Add notifications
                JSONArray notificationArray = new JSONArray();
                if (user.getNotifications() != null) {
                    for (Notification notif : user.getNotifications()) {
                        JSONObject notifObj = new JSONObject();
                        notifObj.put("notificationId", notif.getId());
                        notificationArray.put(notifObj);
                    }
                    obj.put("notifications", notificationArray);
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
