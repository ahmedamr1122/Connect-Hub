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
public class NewsfeedPostNotification extends Notification{
    private String userName;
    private String message;
    
    public NewsfeedPostNotification(String notifId, User recipient, LocalDateTime timestamp, boolean responded, String userName)
    {
        super(notifId, recipient, timestamp, responded);
        this.message = "There is a new post from " + userName;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
    }
    
}
