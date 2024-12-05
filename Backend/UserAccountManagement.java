package Backend;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import Backend.User;
import java.time.LocalDate;
import java.util.List;

public class UserAccountManagement {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public UserAccountManagement() {
        this.users = new ArrayList<>();
    }

   /* // Method to sign up a new user
    public User signup(String email, String username, String password, LocalDate dateOfBirth) {
        String userId = generateUserId();
        User newUser  = new User(userId, email, username, password, dateOfBirth,Status.ONLINE);
        newUser.hashedPass(password);
        users.add(newUser );
        System.out.println("User  signed up: " + username);
        return newUser;
    }*/

    // Method to login a user
    //CREATE A VALIDATION CLASS ( CONTAIN , CONTAIN USERNAME)
    public boolean contain(String email){
          for (User  user : users) {
            if (user.getEmail().equals(email)) {

                return true;
            } 
        }
           return false ;
    }
    public boolean containUserName(String username){
          for (User  user : users) {
            if (user.getUsername().equals(username)) {

                return true;
            } 
        }
           return false ;
    }
    public User findUserByEmail(String email){
       for (User user : users ) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    public User findUserById(String userId) {
    for (User user : users) {
        if (user.getUserId().equals(userId)) {
            return user;
        }
    }
    return null; // User not found
}

    // Method to logout a user
    public void logout(User user) {
        if (user != null) {
            user.setStatus(Status.OFFLINE);
            System.out.println("User  logged out: " + user.getUsername());
            saveUsers(users,"Users.json");
        }
    }

    // Method to generate a unique user ID (for simplicity, using username here)
    public String generateUserId() {
        return  Integer.toString(users.size()+ 100); // Simple user ID generation
    }
      // Method to display all users (for testing purposes)
    public void displayUsers() {
        for (User  user : users ) {
            System.out.println("User  ID: " + user.getUserId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail() + ", Status: " + user.getStatus());
            System.out.println("Password: "+ user.getPassword());
        }
}

  // Static method to save a list of users to a JSON file
public  void saveUsers(List<User> users, String filePath) {
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
            jsonArray.put(obj);
              JSONArray friendsArray = new JSONArray();
                for (User friend : user.getFriends()) {
                    JSONObject friendObj = new JSONObject();
                    friendObj.put("userId", friend.getUserId());
                    /*friendObj.put("email", friend.getEmail());
                    friendObj.put("username", friend.getUsername());
                    friendObj.put("password", user.getPassword());
                    friendObj.put("dateOfBirth", user.getDateOfBirth().toString());
                    friendObj.put("status", friend.getStatus().toString());*/
                    friendsArray.put(friendObj);
                }
                obj.put("friends", friendsArray);

                // Serialize blocked users
                JSONArray blockedArray = new JSONArray();
                for (User blocked : user.getBlocked()) {
                    JSONObject blockedObj = new JSONObject();
                    blockedObj.put("userId", blocked.getUserId());
                    /*blockedObj.put("username", blocked.getUsername());
                    blockedObj.put("email", blocked.getEmail());
                    blockedObj.put("status", blocked.getStatus().toString());*/
                    blockedArray.put(blockedObj);
                }
                obj.put("blocked", blockedArray);

                // Add the user object to the users array
                jsonArray.put(obj);
        }

        try (Writer writer = new FileWriter(filePath)) {
            writer.write(jsonArray.toString(4)); // Pretty print with an indent of 4 spaces
            System.out.println("Users saved successfully to " + filePath);
        }
    } catch (IOException e) {
        System.err.println("Error saving users to file: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
    }
}

// Static method to load users from a JSON file
public  List<User> loadUsers(String filePath) {
   // List<User> users = new ArrayList<>();
    try {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist. Returning an empty user list.");
            return users;
        }

        try (Reader reader = new FileReader(filePath)) {
            JSONArray jsonArray = new JSONArray(new BufferedReader(reader).lines().reduce("", String::concat));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String userId = obj.getString("userId");
                String email = obj.getString("email");
                String username = obj.getString("username");
                String password = obj.getString("password");
                LocalDate dateOfBirth = LocalDate.parse(obj.getString("dateOfBirth"));
                Status status = Status.valueOf(obj.getString("status"));
                User user= new User(userId, email, username, password, dateOfBirth, status);
                
                 // Parse friend IDs
            JSONArray friendsArray = obj.getJSONArray("friends");
            List<User> friends = new ArrayList<>();
            for (int j = 0; j < friendsArray.length(); j++) {
                String friendId = friendsArray.getString(j);
                User friend = findUserById(friendId);
                if (friend != null) {
                    friends.add(friend);
                }
            }
            user.getFriends().addAll(friends);

            // Parse blocked user IDs
            JSONArray blockedArray = obj.getJSONArray("blocked");
            List<User> blockedUsers = new ArrayList<>();
            for (int j = 0; j < blockedArray.length(); j++) {
                String blockedId = blockedArray.getString(j);
                User blocked = findUserById(blockedId);
                if (blocked != null) {
                    blockedUsers.add(blocked);
                }
            }
            user.getBlocked().addAll(blockedUsers);

            // Add user to the users list
            users.add(user);
        }

            
            System.out.println("Users loaded successfully from " + filePath);
        }
    } catch (IOException e) {
        System.err.println("Error reading users from file: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
    }
    return users;
}


  
    }
    
