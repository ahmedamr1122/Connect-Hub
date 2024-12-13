/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.notification;

import Backend.user.User;
import java.time.LocalDateTime;

/**
 *
 * @author Jana
 */
public class FriendRequestNotification extends Notification {
     private User sender;
     private String message;
     
     public FriendRequestNotification(User sender, String notifId, User recipient, LocalDateTime timestamp, boolean responded) {
        super(notifId, recipient, timestamp, responded);
        this.sender = sender;
        this.message = sender.getUsername() + " sent you a friend request.";  
     }

    public User getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
