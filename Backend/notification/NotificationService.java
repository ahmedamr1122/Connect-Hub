package Backend.notification;

import Backend.friends.FriendManagerImplement;
import Backend.friends.RequestStatus;
import Backend.user.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private static List<Notification> allNotifications = new ArrayList<>();
    private static NotificationService instance;

    private NotificationService() {}

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    // Respond to a friend request
    public void respondToFriendRequest(FriendRequestNotification notification, boolean accepted, List<User> users) {
        if (accepted) {
            FriendManagerImplement.getInstance().respondToFriendRequest(notification.getSender(), notification.getRecipient(), RequestStatus.ACCEPTED, users);
        } else {
            FriendManagerImplement.getInstance().respondToFriendRequest(notification.getSender(), notification.getRecipient(), RequestStatus.DECLINED, users);
        }
        markResponded(notification);
    }

    // Send friend request notification
    public void sendFriendRequestNotification(User sender, User recipient) {
        String notificationId = "fn" + (allNotifications.size() + 1);
        Notification notification = new FriendRequestNotification(sender, notificationId, recipient, LocalDateTime.now(), false);
        recipient.addNotification(notification);
        allNotifications.add(notification);
    }

    // Send friend request status notification
    public void sendFriendRequestStatusNotification(User sender, User recipient, RequestStatus status) {
        String notificationId = "frs" + (allNotifications.size() + 1);
        Notification notification = new FriendRequestStatusNotification(notificationId, recipient, LocalDateTime.now(), false, status, sender.getUsername());
        recipient.addNotification(notification);
        allNotifications.add(notification);
    }

    public void sendGroupRequestStatusNotification(User recipient, String groupId, RequestStatus status) {
        String notificationId = "grs" + (allNotifications.size() + 1); // Generate unique ID
        Notification notification = new GroupRequestStatusNotification(notificationId, recipient, LocalDateTime.now(), false, groupId, status);
        recipient.addNotification(notification); // Add to recipient's notifications
        allNotifications.add(notification);
    }
    // Send group post notification
    public void sendGroupPostNotification(User recipient, String groupName) {
        String notificationId = "gpn" + (allNotifications.size() + 1);
        Notification notification = new GroupPostNotification(notificationId, recipient, LocalDateTime.now(), false, groupName);
        recipient.addNotification(notification);
        allNotifications.add(notification);
    }

    // Send newsfeed post notification
    public void sendNewsfeedPostNotification(User recipient, String userName) {
        String notificationId = "nf" + (allNotifications.size() + 1);
        Notification notification = new NewsfeedPostNotification(notificationId, recipient, LocalDateTime.now(), false, userName);
        recipient.addNotification(notification);
        allNotifications.add(notification);
    }

    // Delete a specific notification
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

    // Clear all notifications for a user
    public void clearAllNotifications(User user) {
        user.getNotifications().clear();
        System.out.println("All notifications have been cleared for " + user.getUsername());
    }

    // Mark a notification as responded
    public void markResponded(Notification notification) {
        notification.setResponded(true);
    }

    // Get all notifications
    public static List<Notification> getAllNotifications() {
        return allNotifications;
    }
}
