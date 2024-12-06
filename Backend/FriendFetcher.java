package Backend;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FriendFetcher implements IFriendFetcher {

    private final String filePath = "Friends.json";  // Path to friends file

    @Override
    public List<Friend> fetchUserFriends(String userId) throws IOException {
        List<Friend> friends = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) return friends;  // Return empty if file doesn't exist

        try (Reader reader = new FileReader(file)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray friendsArray = new JSONArray(tokener);

            // Iterate through friends and filter by userId
            for (int i = 0; i < friendsArray.length(); i++) {
                JSONObject friendObject = friendsArray.getJSONObject(i);
                String friendUserId = friendObject.getString("friendId");

                if (friendUserId.equals(userId)) {
                    Friend friend = new Friend(
                        friendObject.getString("friendId"),
                        friendObject.getString("status")
                    );
                    friends.add(friend);
                }
            }
        }
        return friends;
    }
}
