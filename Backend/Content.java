/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDateTime;

/**
 *
 * @author Jana
 */
public class Content {
    private String contentId;
    private String authorId;
    private String contentText;
    private LocalDateTime timestamp;
    private String imagePath;
    protected String type;
    
    public Content(String contentId, String authorId, String contentText, LocalDateTime timestamp, String imagePath, String type) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.contentText = contentText;
        this.timestamp = timestamp;
        this.imagePath = imagePath;
        this.type = type;
    }
    
    public String getContentId() {
        return contentId; 
    }
    public String getAuthorId() { 
        return authorId; 
    }
    public String getContentText() { 
        return contentText;
    }
    public LocalDateTime getTimestamp() {
        return timestamp; 
    }
    public String getImagePath() {
        return imagePath; 
    }
    public String getType() {
        return type; 
    }

}
