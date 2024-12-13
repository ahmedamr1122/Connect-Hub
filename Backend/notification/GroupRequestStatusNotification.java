/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.notification;

import Backend.friends.RequestStatus;
import Backend.user.User;
import java.time.LocalDateTime;

/**
 *
 * @author Jana
 */
public class GroupRequestStatusNotification extends Notification {
    private String groupName;
    private RequestStatus status;
    private String message;
    public GroupRequestStatusNotification(String notifId, User recipient, LocalDateTime timestamp, boolean responded, String groupName, RequestStatus status) {
        super(notifId, recipient, timestamp, responded);
        this.groupName = groupName;
        this.status = status;
        this.message = "Your request to join the group " + groupName + " has been " + status + ".";
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
