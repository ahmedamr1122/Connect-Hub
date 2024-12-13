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
public abstract class Notification {
    private String id;
    //private String message;
    private User recipient;
    //private String type;
    private LocalDateTime timestamp;
    boolean responded;

    public Notification(String notifId, User recipient, LocalDateTime timestamp, boolean responded) {
        this.id = id;
        //this.message = message;
        this.recipient = recipient;
        //this.type = type;
        this.timestamp = timestamp;
        this.responded = responded;
    }

    public String getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }

 
    
    
}
