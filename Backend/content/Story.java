/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.content;

import java.time.LocalDateTime;

/**
 *
 * @author Jana
 */
public class Story extends Content{
    
    private LocalDateTime expiryTime;
    
    public Story(String contentId, String authorId, String contentText, LocalDateTime timestamp, String imagePath, LocalDateTime expiryTime) {
        super(contentId, authorId, contentText, timestamp, imagePath, "STORY");
        this.expiryTime = expiryTime;
    }
    
    public LocalDateTime getExpiryTime() {
        return expiryTime; 
    }
}
