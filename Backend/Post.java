/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.Content;
import java.time.LocalDateTime;

/**
 *
 * @author Jana
 */
public class Post extends Content{
    
    public Post(String contentId, String authorId, String contentText, LocalDateTime timestamp, String imagePath) {
        super(contentId, authorId, contentText, timestamp, imagePath,"POST");
    }

}
