/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.Content;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ContentService {

    private final String filePath;

    public ContentService(String filePath) {
        this.filePath = filePath;
    }

    // this loads content from JSON file
    private ArrayList<Content> loadContent() throws IOException {
        File file = new File(filePath);
        ArrayList<Content> contentList = new ArrayList<>();
        if (!file.exists()) {
            return contentList; // this returns empty array list if file doesn't exist
        }

        try (Reader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);//Creates a JSONTokener that reads from the file
            JSONArray jsonArray = new JSONArray(tokener);//Parses the JSON file into a JSONArray that represents list of json objects

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String type = obj.getString("type");
                if ("POST".equals(type)) { //If it's a post, it creates a new Post and adds to contentList
                    contentList.add(new Post(
                            obj.getString("contentId"),
                            obj.getString("authorId"),
                            obj.getString("contentText"),
                            LocalDateTime.parse(obj.getString("timestamp")),
                            obj.optString("imagePath", null)
                    ));
                } else if ("STORY".equals(type)) { ////If it's a story, it creates a new Story and adds to contentList
                    contentList.add(new Story(
                            obj.getString("contentId"),
                            obj.getString("authorId"),
                            obj.getString("contentText"),
                            LocalDateTime.parse(obj.getString("timestamp")),
                            obj.optString("imagePath", null),
                            LocalDateTime.parse(obj.getString("expiryTime"))
                    ));
                }
            }
        }
        return contentList;
    }

    // Save content to JSON file
    private void saveContent(ArrayList<Content> contentList) throws IOException {
        JSONArray jsonArray = new JSONArray();

        for (Content content : contentList) {
            JSONObject obj = new JSONObject(); //Creates a JSONObject and adds key-value pairs that is the Content object’s attributes.
            obj.put("contentId", content.getContentId());
            obj.put("authorId", content.getAuthorId());
            obj.put("contentText", content.getContentText());
            obj.put("timestamp", content.getTimestamp().toString());
            obj.put("imagePath", content.getImagePath());
            obj.put("type", content.getType());

            if (content instanceof Story) {
                obj.put("expiryTime", ((Story) content).getExpiryTime().toString());
            }

            jsonArray.put(obj);
        }

        try (Writer writer = new FileWriter(filePath)) { //Opens the file for writing
            writer.write(jsonArray.toString(4)); //indentation of 4 spaces for readability.
        }
    }

    public void createPost(String userId, String contentText, String imagePath) throws IOException {
        ArrayList<Content> contentList = loadContent();
        String postId = "c" + (contentList.size() + 1);
        Post post = new Post(postId, userId, contentText, LocalDateTime.now(), imagePath);
        contentList.add(post);
        saveContent(contentList);
    }

    public void createStory(String userId, String contentText, String imagePath) throws IOException {
        ArrayList<Content> contentList = loadContent();
        String storyId = "s" + (contentList.size() + 1);
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(24);
        Story story = new Story(storyId, userId, contentText, LocalDateTime.now(), imagePath, expiryTime);
        contentList.add(story);
        saveContent(contentList);
    }

    public void deleteExpiredStories() throws IOException {
        ArrayList<Content> contentList = loadContent();
        LocalDateTime now = LocalDateTime.now();
        contentList.removeIf(content -> content instanceof Story && now.isAfter(((Story) content).getExpiryTime()));
        saveContent(contentList);
    }
}
