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
    private String groupId;
    private RequestStatus status;
    private String message;
    public GroupRequestStatusNotification(String notifId, User recipient, LocalDateTime timestamp, boolean responded, String groupId, RequestStatus status) {
        super(notifId, recipient, timestamp, responded);
        this.groupId = groupId;
        this.status = status;
        this.message = "Your request to join the group " + groupId + " has been " + status + ".";
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isResponded() {
        return responded;
    }

    public void setResponded(boolean responded) {
        this.responded = responded;
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
