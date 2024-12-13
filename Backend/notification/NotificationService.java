/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.notification;

import Backend.friends.RequestStatus;
import Backend.user.User;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author asus
 */
public class NotificationService {

    public void sendFriendRequestNotification(User sender, User recipient) {
        String notificationId = "fn" + (recipient.getNotifications().size() + 1);
        FriendRequestNotification notification = new FriendRequestNotification(sender, notificationId, recipient, LocalDateTime.now(), false);
        recipient.addNotification(notification);
    }

    public void sendGroupInvitationNotification(User sender, User recipient, String groupName) {
        String notificationId = "gn" + (recipient.getNotifications().size() + 1);
        GroupInvitationNotification notification = new GroupInvitationNotification(sender, notificationId, recipient, LocalDateTime.now(), false, groupName);
        recipient.addNotification(notification);
    }

    public void sendFriendRequestStatusNotification(User sender, User recipient, RequestStatus status) {
        String notificationId = "frs" + (recipient.getNotifications().size() + 1);
        FriendRequestStatusNotification notification = new FriendRequestStatusNotification(notificationId, recipient, LocalDateTime.now(), false, status, sender.getUsername());
        recipient.addNotification(notification);

    }

    public void sendGroupRequestStatusNotification(User recipient, String groupName, RequestStatus status) {
        String notificationId = "grs" + (recipient.getNotifications().size() + 1);
        GroupRequestStatusNotification notification = new GroupRequestStatusNotification(notificationId, recipient, LocalDateTime.now(), false, groupName, status );
        recipient.addNotification(notification);

    }
    public void sendGroupPostNotification(User recipient, String groupName) {
        String notificationId = "gpn" + (recipient.getNotifications().size() + 1);
        GroupPostNotification notification = new GroupPostNotification(notificationId, recipient, LocalDateTime.now(), false, groupName);
        recipient.addNotification(notification);
    }
    public void sendNewsfeedPostNotification(User recipient, String userName) {
        String notificationId = "nf" + (recipient.getNotifications().size() + 1);
        NewsfeedPostNotification notification = new NewsfeedPostNotification(notificationId, recipient, LocalDateTime.now(), false, userName );
        recipient.addNotification(notification);
    }
    
    public boolean deleteNotification(User user, String notificationId) {
        List<Notification> notifications = user.getNotifications();
        for (int i = 0; i < notifications.size(); i++) {
            if (notifications.get(i).getId().equals(notificationId)) {
                notifications.remove(i);
                System.out.println("Notification with ID " + notificationId + " has been deleted.");
                return true;
            }
        }
        System.out.println("Notification with ID " + notificationId + " not found.");
        return false;
    }
    // method to clear all notification
    public void clearAllNotifications(User user) {
    user.getNotifications().clear();
    System.out.println("All notifications have been cleared for " + user.getUsername());
}

    public void markResponded(Notification notification) {//true if user responded
        notification.setResponded(true);
    }
   

}
