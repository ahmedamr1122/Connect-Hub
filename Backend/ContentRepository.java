package Backend;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;
import java.util.ArrayList;

/**
 * This class handles loading and saving content from/to a JSON file.
 */
public class ContentRepository {

    private final String filePath;

    public ContentRepository(String filePath) {
        this.filePath = filePath;
    }

    //Loads content from the JSON file into an ArrayList of Content objects.
    public ArrayList<Content> loadContent() throws IOException {
        File file = new File(filePath);
        ArrayList<Content> contentList = new ArrayList<>();

        if (!file.exists()) {
            return contentList; // Return empty list if the file doesn't exist.
        }

        try (Reader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader); // Creates a JSONTokener to read from the file.
            JSONArray jsonArray = new JSONArray(tokener);  // Parses the JSON file into a JSONArray.

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Content content = ContentFactory.createContent(obj); // Delegates content creation to the factory.
                contentList.add(content); // Adds the created content to the list.
            }
        }

        return contentList;
    }

    // Saves the given list of Content objects to the JSON file.
    public void saveContent(ArrayList<Content> contentList) throws IOException {
        JSONArray jsonArray = new JSONArray();

        for (Content content : contentList) {
            JSONObject obj = new JSONObject(); // Creates a JSONObject to represent each Content object.
            obj.put("contentId", content.getContentId());
            obj.put("authorId", content.getAuthorId());
            obj.put("contentText", content.getContentText());
            obj.put("timestamp", content.getTimestamp().toString());
            obj.put("imagePath", content.getImagePath());
            obj.put("type", content.getType());

            if (content instanceof Story) {
                obj.put("expiryTime", ((Story) content).getExpiryTime().toString());
            }

            jsonArray.put(obj); // Adds the JSONObject to the JSONArray.
        }

        try (Writer writer = new FileWriter(filePath)) { // Opens the file for writing.
            writer.write(jsonArray.toString(4)); // Writes the JSONArray to the file with an indentation of 4 spaces.
        }
    }
}
