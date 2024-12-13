package Backend.Group;

import Backend.content.Post;
import Backend.friends.RequestStatus;
import Backend.user.FindUser;
import Backend.user.User;
import Backend.user.UserAccountManagement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GroupFactory {
    
    public static Group createGroup(JSONObject obj, List<User> users)
    {
        String groupId = obj.getString("groupId");
        String name = obj.getString("name");
        String description = obj.getString("description");
        String groupPhotoPath = obj.getString("groupPhotoPath");
        String primaryAdmin = obj.getString("primaryAdminId");
        
        FindUser find = new FindUser();
        User primary = find.findUserById(primaryAdmin, users);
        
       // Parse admins
        List<User> admins = new ArrayList<>();
        JSONArray adminsArray = obj.optJSONArray("admins");
        if (adminsArray != null) {
            for (int i = 0; i < adminsArray.length(); i++) {
                JSONObject adminObj = adminsArray.getJSONObject(i);
                String adminId = adminObj.getString("adminId");
                admins.add(find.findUserById(adminId, users));
            }
        }
        
        // Parse members
        List<User> members = new ArrayList<>();
        JSONArray membersArray = obj.optJSONArray("members");
        if (membersArray != null) {
            for (int i = 0; i < membersArray.length(); i++) {
                JSONObject memberObj = membersArray.getJSONObject(i);
                String memberId = memberObj.getString("memberId");
                members.add(find.findUserById(memberId, users));
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
        
        // Parse join requests
        List<GroupRequest> pendingJoinRequests = new ArrayList<>();
        JSONArray pendingJoinArray = obj.optJSONArray("pendingJoinRequests");
        if (pendingJoinArray != null) {
            for (int i = 0; i < pendingJoinArray.length(); i++) {
                JSONObject requestObj = pendingJoinArray.getJSONObject(i);
                String senderId = requestObj.getString("senderId");
                RequestStatus requestStatus = RequestStatus.valueOf(requestObj.getString("status"));
                pendingJoinRequests.add(new GroupRequest(senderId, groupId, requestStatus));
            }
        }
        
        Group group = new Group(groupId, name, description, groupPhotoPath, primary);
        group.setAdmins(admins);
        group.setMembers(members);
        group.setPosts(posts);
        group.setPendingJoinRequests(pendingJoinRequests);
        
        return group;
    }
    
}
