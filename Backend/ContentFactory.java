package Backend;

import org.json.JSONObject;
import java.time.LocalDateTime;

public class ContentFactory {

    public static Content createContent(JSONObject obj) {
        String type = obj.getString("type");

        switch (type) {
            case "POST":
                return new Post(
                        obj.getString("contentId"),
                        obj.getString("authorId"),
                        obj.getString("contentText"),
                        LocalDateTime.parse(obj.getString("timestamp")),
                        obj.optString("imagePath", null)
                );
            case "STORY":
                return new Story(
                        obj.getString("contentId"),
                        obj.getString("authorId"),
                        obj.getString("contentText"),
                        LocalDateTime.parse(obj.getString("timestamp")),
                        obj.optString("imagePath", null),
                        LocalDateTime.parse(obj.getString("expiryTime"))
                );
            default:
                throw new IllegalArgumentException("Unknown content type: " + type);
        }
    }
}