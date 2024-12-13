/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.notification;

/**
 *
 * @author Jana
 */
public class findNotification {
    public static Notification findNotificationById(String notifId) {
    for (Notification notification : NotificationService.getAllNotifications()) {
        if (notification.getId().equals(notifId)) {
            return notification;
        }
    }
    return null; // Group not found
}
}
