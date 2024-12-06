package Backend.user;

import Backend.ContentFactory;
import Backend.content.Content;
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
        List<User> friends = new ArrayList<>();
        for (int j = 0; j < friendsArray.length(); j++) {
            String friendId = friendsArray.getString(j);
            User friend = findUser.findUserById(friendId);
            if (friend != null) {
                friends.add(friend);
            }
        }

        // Parse blocked user IDs
        JSONArray blockedArray = obj.getJSONArray("blocked");
        List<User> blockedUsers = new ArrayList<>();
        for (int j = 0; j < blockedArray.length(); j++) {
            String blockedId = blockedArray.getString(j);
            User blocked = findUser.findUserById(blockedId);
            if (blocked != null) {
                blockedUsers.add(blocked);
            }
        }
        
        
        // Parse Content
        JSONArray contentArray = obj.getJSONArray("Content");
        List<Content> contentList = new ArrayList<>();
        for (int i = 0; i < contentArray.length(); i++) {
                Content content = ContentFactory.createContent(obj); // Delegates content creation to the factory.
                contentList.add(content); // Adds the created content to the list.
            }
        
        return new User(userId,email,username,password,dateOfBirth,status,bio,profilePhoto,coverPhoto);

    }

}
