package Backend.user;

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
    private List <FriendRequest> sentRequests;
    private List <FriendRequest> receivedRequests;
    private List <User> suggestFriends;
    
    
    public User(String userId, String email, String username, String password, LocalDate dateOfBirth, Status status,String bio,String profilePhoto,String coverPhoto) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.bio = bio;
        this.profilePhoto = profilePhoto;
        this.coverPhoto=coverPhoto;
        this.friends = new ArrayList<>();
        this.Blocked = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.receivedRequests = new ArrayList<>();
        this.sentRequests = new ArrayList<>();
        this.suggestFriends= new ArrayList<>();
        
    }
    

    

    public List<User> getFriends() {
        return friends;
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

    public List<User> getSuggestFriends() {
        return suggestFriends;
    }
    public void displayFriends(){
           for (Post user : posts) {
            System.out.println("User ID: " + user.getAuthorId()+ ", Username: " + user.getContentId()+ ", Email: " + user.getContentText()+ ", Status: " + user.getImagePath());
            System.out.println("Password: " + user.getType());
           
        }
    }
    
    
}
