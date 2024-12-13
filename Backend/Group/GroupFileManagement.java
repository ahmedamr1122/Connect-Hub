package Backend.Group;

import Backend.content.Post;
import Backend.user.User;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;

public class GroupFileManagement {

    private String filePath;
    private static GroupFileManagement instance;

    public GroupFileManagement() {
        this.filePath = "Groups.json";
    }

    public static GroupFileManagement getInstance() {
        if (instance == null) {
            instance = new GroupFileManagement();
        }
        return instance;
    }

    public List<Group> loadGroups(List<User> users) {
        List<Group> groups = new ArrayList<>();

        try {
            // Check if the file exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist: " + filePath);
                return groups;  // Return empty list if file does not exist
            }

            System.out.println("Loading users from file: " + filePath);

            // Read file content as a single string
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Check if the file content is empty
            if (fileContent.trim().isEmpty()) {
                System.out.println("File content is empty. Returning an empty group list.");
                return groups;
            }

            // Parse the content as a JSON array
            JSONArray jsonArray = new JSONArray(fileContent);

            // Process each JSON object into a group
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                try {
                    Group group = GroupFactory.createGroup(obj,users);
                    groups.add(group);
                    System.out.println("Loaded group: " + group.getName());

                } catch (Exception e) {
                    System.err.println("Error creating group from JSON object at index " + i + ": " + e.getMessage());
                }
            }

            System.out.println("Groups loaded successfully. Total groups: " + groups.size());
        } catch (IOException e) {
            System.err.println("Error reading groups from file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return groups;

    }
    
    public void saveGroups(List<Group> groups) {
        try {
            JSONArray jsonArray = new JSONArray();

            for (Group group : groups) {
                JSONObject obj = new JSONObject();
                obj.put("groupId", group.getGroupId());
                obj.put("name", group.getName());
                obj.put("description", group.getDescription());
                obj.put("groupPhotoPath", group.getGroupPhotoPath());
                obj.put("primaryAdminId", group.getPrimaryAdmin().getUserId());
                
                // Add admins
                JSONArray adminArray = new JSONArray();
                if(group.getAdmins()!= null ){
                for (User admin : group.getAdmins()) {
                    JSONObject adminObj = new JSONObject();
                    adminObj.put("adminId", admin.getUserId());
                    adminArray.put(adminObj);
                }
                obj.put("admins", adminArray);
                }
                

                // Add members
                JSONArray memberArray = new JSONArray();
                if(group.getMembers()!= null ){
                for (User member : group.getMembers()) {
                    JSONObject memberObj = new JSONObject();
                    memberObj.put("memberId", member.getUserId());
                    memberArray.put(memberObj);
                }
                obj.put("members", memberArray);
                }
                
                
                // Add posts
                JSONArray postArray = new JSONArray();
                if(group.getPosts()!=null){
                for (Post post : group.getPosts()) {
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

                
                // Add the pending requests
                if(group.getPendingJoinRequests()!=null){
                JSONArray pendingJoinArray = new JSONArray();
                for (GroupRequest request : group.getPendingJoinRequests()) {
                    JSONObject requestObj = new JSONObject();
                    requestObj.put("senderId", request.getUser());
                    requestObj.put("status", request.getStatus().toString());
                    pendingJoinArray.put(requestObj);
                }
                obj.put("pendingJoinRequests", pendingJoinArray);
                }

                // Add the group object to the json array
                jsonArray.put(obj);
            }

            // Write array contents into the file
            try (Writer writer = new FileWriter("Groups.json")) {
                writer.write(jsonArray.toString(4)); // Pretty print with an indent of 4 spaces
                System.out.println("Groups saved successfully to " + "Groups.json");
            }
        } catch (IOException e) {
            System.err.println("Error saving groups to file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

}
