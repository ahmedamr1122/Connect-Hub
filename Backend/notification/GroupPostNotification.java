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
public class GroupPostNotification extends Notification{
    private String groupName;
    private String message;

    public GroupPostNotification(String notifId, User recipient, LocalDateTime timestamp, boolean responded, String groupName) {
        super(notifId, recipient, timestamp, responded);
        this.groupName = groupName;
        this.message = "There is a new post in the group: " + groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
