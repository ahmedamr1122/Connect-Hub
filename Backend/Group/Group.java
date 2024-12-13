package Backend.Group;

import Backend.content.Post;
import Backend.user.User;
import java.util.ArrayList;
import java.util.List;

public class Group {
    
    private String groupId;
    private String name;
    private String description;
    private String groupPhotoPath;
    private User primaryAdmin;
    private List<User> admins;
    private List<User> members;
    private List<Post> posts;
    private List<GroupRequest> pendingJoinRequests;

    public Group(String groupId, String name, String description, String groupPhotoPath, User primaryAdmin) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.groupPhotoPath = groupPhotoPath;
        this.primaryAdmin = primaryAdmin;
        this.admins = new ArrayList<>();
        this.members = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.pendingJoinRequests = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupPhotoPath() {
        return groupPhotoPath;
    }

    public void setGroupPhotoPath(String groupPhotoPath) {
        this.groupPhotoPath = groupPhotoPath;
    }

    public User getPrimaryAdmin() {
        return primaryAdmin;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    public void addAdmin(User user)
    {
        if (isMember(user)) {
            admins.add(user);
        } else {
            throw new IllegalArgumentException("User must be a member to become an admin.");
        }
    }

    public boolean isMember(User user) 
    {
        return members.contains(user);
    }

    public boolean isAdmin(User member)
    {
        return admins.contains(member);
    }

    public void removeAdmin(User admin)
    {
        admins.remove(admin);
    }

    public void addMember(User user) 
    {
        members.add(user);
    }

    public void removeMember(User user) 
    {
        admins.remove(user); // Remove from admins if applicable
        members.remove(user);
    }

    public List<GroupRequest> getPendingJoinRequests() {
        return pendingJoinRequests;
    }

    public void setPendingJoinRequests(List<GroupRequest> pendingJoinRequests) {
        this.pendingJoinRequests = pendingJoinRequests;
    }    
    
    
}
