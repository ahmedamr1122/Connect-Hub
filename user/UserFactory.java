package Backend.user;

import Backend.ContentFactory;
import Backend.content.Content;
import Backend.friends.FriendRequest;
import Backend.friends.RequestStatus;
import java.time.LocalDate;
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

        // Parse friend IDs
        JSONArray friendsArray = obj.getJSONArray("friends");
        FindUser findUser = new FindUser();
        if (friendsArray != null) {
            List<User> friends = new ArrayList<>();
            for (int j = 0; j < friendsArray.length(); j++) {
                String friendId = friendsArray.getString(j);
                User friend = new User(friendId, null, null, null, null, null, null, null, null);
                friends.add(friend);

            }
        }

        // Parse blocked user IDs
        JSONArray blockedArray = obj.getJSONArray("blocked");
        if (blockedArray != null) {
            List<User> blockedUsers = new ArrayList<>();
            for (int j = 0; j < blockedArray.length(); j++) {
                String blockedId = blockedArray.getString(j);
                User blocked = new User(blockedId, null, null, null, null, null, null, null, null);

                blockedUsers.add(blocked);

            }
        }

        // Parse Content
        JSONArray contentArray = obj.getJSONArray("Content");
        if (contentArray != null) {
            List<Content> contentList = new ArrayList<>();
            for (int i = 0; i < contentArray.length(); i++) {
                Content content = ContentFactory.createContent(obj); // Delegates content creation to the factory.
                contentList.add(content); // Adds the created content to the list.
            }
        }
        
        
        // Parse Sent Requests
        JSONArray sentRequestArray = obj.getJSONArray("sentRequest");
        if (sentRequestArray != null) {
            List<FriendRequest> sentRequestList = new ArrayList<>();

            for (int i = 0; i < sentRequestArray.length(); i++) {
                JSONObject sentObj = sentRequestArray.getJSONObject(i);
                String receiverId = sentObj.getString("receiverId");
                RequestStatus requestStatus = RequestStatus.valueOf(obj.getString("status"));
                FriendRequest request = new FriendRequest(userId, receiverId, requestStatus);
                sentRequestList.add(request);
            }
        }
        
        // Parse Received Rquests
        JSONArray receivedRequestArray = obj.getJSONArray("receivedRequest");
        if (receivedRequestArray != null) {

            List<FriendRequest> receivedRequestList = new ArrayList<>();
            for (int i = 0; i < receivedRequestArray.length(); i++) {
                JSONObject sentObj = receivedRequestArray.getJSONObject(i);
                String senderId = sentObj.getString("receiverId");
                RequestStatus requestStatus = RequestStatus.valueOf(obj.getString("status"));
                FriendRequest request = new FriendRequest(senderId, userId, requestStatus);
                receivedRequestList.add(request);
            }

        }

        return new User(userId, email, username, password, dateOfBirth, status, bio, profilePhoto, coverPhoto);

    }

}
