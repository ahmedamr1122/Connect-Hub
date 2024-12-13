package Backend.user;

import Backend.Group.Group;
import java.time.LocalDate;
import java.util.List;
import Backend.content.Post;
import Backend.content.Story;
import Backend.friends.FriendRequest;
import java.util.ArrayList;

// Class to represent a user
public class User {

    private String userId;
    private String email;
    private String username;
    private String password; // Hashed password
    private LocalDate dateOfBirth;
    private Status status; // Online or Offline
    private String bio;
    private String profilePhoto;
    private String coverPhoto;
    private List<User> friends;
    private List<User> Blocked;
    private List<Post> posts;
    private List<Story> stories;
    private List<FriendRequest> sentRequests;
    private List<FriendRequest> receivedRequests;
    private List<Group> ownedGroups;
    private List<Group> joinedGroups;

    public User(String userId, String email, String username, String password, LocalDate dateOfBirth, Status status, String bio, String profilePhoto, String coverPhoto) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.bio = bio;
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.friends = new ArrayList<>();
        this.Blocked = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.receivedRequests = new ArrayList<>();
        this.sentRequests = new ArrayList<>();
        this.ownedGroups = new ArrayList<>();
        this.joinedGroups = new ArrayList<>();

    }

    public User(String userId) {
        this.userId = userId;
        this.email = null;
        this.username = null;
        this.password = null;
        this.dateOfBirth = null;
        this.status = null;
        this.bio = null;
        this.profilePhoto = null;
        this.coverPhoto = null;
        this.friends = new ArrayList<>();
        this.Blocked = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.receivedRequests = new ArrayList<>();
        this.sentRequests = new ArrayList<>();
        this.ownedGroups = new ArrayList<>();
        this.joinedGroups = new ArrayList<>();

    }

    public List<User> getFriends() {
        return friends;
    }

    public List<User> getFriends(List<User> users) {
        List<User> list = new ArrayList<>();
        FindUser find = new FindUser();
        User user = null;
        for (User friend : friends) {
            user = find.findUserById(friend.getUserId(), users);
            list.add(user);
        }
        return list;
    }

    public List<User> getBlocked(List<User> users) {
        List<User> list = new ArrayList<>();
        FindUser find = new FindUser();
        User user = null;
        for (User blocked : Blocked) {
            user = find.findUserById(blocked.getUserId(), users);
            list.add(user);
        }
        return list;
    }

    public List<User> getBlocked() {
        return Blocked;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Status getStatus() {
        return status;
    }

    public String getBio() {
        return bio;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void setBlocked(List<User> Blocked) {
        this.Blocked = Blocked;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public void setSentRequests(List<FriendRequest> sentRequests) {
        this.sentRequests = sentRequests;
    }

    public void setReceivedRequests(List<FriendRequest> receivedRequests) {
        this.receivedRequests = receivedRequests;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setPassword(String password) {
        this.password = PasswordHashing.hashedPass(password);
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setCoverPhopo(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<FriendRequest> getSentRequests() {
        return sentRequests;
    }

    public List<FriendRequest> getReceivedRequests() {
        return receivedRequests;
    }


    public void displayFriends() {
        for (Post user : posts) {
            System.out.println("User ID: " + user.getAuthorId() + ", Username: " + user.getContentId() + ", Email: " + user.getContentText() + ", Status: " + user.getImagePath());
            System.out.println("Password: " + user.getType());

        }
    }
    
    // Add a group to the owned groups
    public void addOwnedGroup(Group group) {
        if (!ownedGroups.contains(group)) {
            ownedGroups.add(group);
        } else {
            System.out.println("Group is already owned by the user.");
        }
    }

    // Remove a group from the owned groups
    public void removeOwnedGroup(Group group) {
        if (ownedGroups.contains(group)) {
            ownedGroups.remove(group);
        } else {
            System.out.println("Group is not owned by the user.");
        }
    }

    public List<Group> getOwnedGroups() {
        return ownedGroups;
    }
    
    public List<Group> getJoinedGroups() {
        return joinedGroups;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same instance in memory
        if (obj == null || getClass() != obj.getClass()) return false; // Not null and both are of the same class
        User user = (User) obj;
        return userId != null && userId.equals(user.userId); // this.userId is not null and equals that of the user
    }

    // Equal objects must have the same HashCode
    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0; // Ensure that two strings with the same characters have the same hash code.
    }

}
