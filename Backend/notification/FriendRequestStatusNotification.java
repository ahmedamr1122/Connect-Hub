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
public class FriendRequestStatusNotification extends Notification{
    private RequestStatus status;
    private String message;
    private String userName;
    public FriendRequestStatusNotification(String notifId, User recipient, LocalDateTime timestamp, boolean responded, RequestStatus status, String userName)
    {
        super(notifId, recipient, timestamp, responded);
        this.status = status;
        this.message = "Your friend request to " + userName  + " has been " + status + ".";
        this.userName = userName;
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
