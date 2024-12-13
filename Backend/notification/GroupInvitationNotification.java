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
public class GroupInvitationNotification extends Notification{
    private User sender;
    private String message;
    private String groupName;
     
    public GroupInvitationNotification(User sender, String notifId, User recipient, LocalDateTime timestamp, boolean responded, String groupName){
        super(notifId, recipient, timestamp, responded);
        this.sender = sender;
        this.groupName = groupName;
        this.message = sender.getUsername() + "invited you to join the group: " +groupName ;  
    }

    public User getSender() {
        return sender;
    }
    
    public String getGroupName() {
        return groupName;
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
