package Backend.user;

import Backend.content.ContentFactory;
import Backend.content.Content;
import Backend.content.Post;
import Backend.content.Story;
import Backend.friends.FriendRequest;
import Backend.friends.RequestStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserFactory {


    public static User createUser(JSONObject obj) {
        String userId = obj.getString("userId");
        String email = obj.getString("email");
        String username = obj.getString("username");
        String password = obj.getString("password");
        LocalDate dateOfBirth = LocalDate.parse(obj.getString("dateOfBirth"));
        Status status = Status.valueOf(obj.getString("status"));
        String bio = obj.getString("Bio");
        String profilePhoto = obj.getString("ProfilePhoto");
        String coverPhoto = obj.getString("CoverPhoto");

        // Parse friends
        List<User> friends = new ArrayList<>();
        JSONArray friendsArray = obj.optJSONArray("friends");
        if (friendsArray != null) {
            for (int i = 0; i < friendsArray.length(); i++) {
                JSONObject friendObj = friendsArray.getJSONObject(i);
                String friendId = friendObj.getString("FriendId");
                friends.add(new User(friendId, null, null, null, null, null, null, null, null));
            }
        }

        // Parse blocked users
        List<User> blockedUsers = new ArrayList<>();
        JSONArray blockedArray = obj.optJSONArray("blocked");
        if (blockedArray != null) {
            for (int i = 0; i < blockedArray.length(); i++) {
                JSONObject blockedObj = blockedArray.getJSONObject(i);
                String blockedId = blockedObj.getString("BlockedId");
                blockedUsers.add(new User(blockedId, null, null, null, null, null, null, null, null));
            }
        }

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
                        postObj.optString("imagePath",null)
                        
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
                        storyObj.optString("imagePath",null),
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
        User user = new User( userId, email, username, password, dateOfBirth, status, bio,profilePhoto,coverPhoto);
        user.setFriends(friends);
        user.setBlocked(blockedUsers);
        user.setStories(stories);
        user.setPosts(posts);
        user.setSentRequests(sentRequests);
        user.setReceivedRequests(receivedRequests);

        // Construct the user
        return user;
    }
}
