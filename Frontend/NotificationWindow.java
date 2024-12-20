/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.notification.FriendRequestNotification;
import Backend.notification.FriendRequestStatusNotification;
import Backend.notification.GroupPostNotification;
import Backend.notification.Notification;
import Backend.notification.NotificationService;
import Backend.user.User;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Jana
 */
public class NotificationWindow extends javax.swing.JFrame {

    /**
     * Creates new form NotificationWindow
     */
    private User currentUser;
    private List<User> users;
    public NotificationWindow(User currentUser, List<User> users) {
        initComponents();
        
        this.currentUser = currentUser;
        this.users = users;
        loadNotifications();
    }
    
    private void loadNotifications() {
        notificationsPanel.removeAll();
        List<Notification> notifications = currentUser.getNotifications();

        for (Notification notification : notifications) {
            JPanel notificationPanel = new JPanel(new BorderLayout());
            notificationPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            notificationPanel.setPreferredSize(new Dimension(350, 80));

            // Retrieve message from the specific subclass
            String message = getNotificationMessage(notification);
            JLabel messageLabel = new JLabel(message);
            messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            notificationPanel.add(messageLabel, BorderLayout.CENTER);

            // Add action buttons if the notification is actionable
            if (notification instanceof FriendRequestNotification) {
                addFriendRequestActions(notificationPanel, (FriendRequestNotification) notification);
            }

            notificationsPanel.add(notificationPanel);
        }

        notificationsPanel.revalidate();
        notificationsPanel.repaint();
    }
    
    private String getNotificationMessage(Notification notification) {
        if (notification instanceof FriendRequestNotification) {
            return ((FriendRequestNotification) notification).getMessage();
        } else if (notification instanceof FriendRequestStatusNotification) {
            return ((FriendRequestStatusNotification) notification).getMessage();
        } else if (notification instanceof GroupPostNotification) {
            return ((GroupPostNotification) notification).getMessage();
        }
        return "Unknown notification type.";
    }
    
     private void addFriendRequestActions(JPanel notificationPanel, FriendRequestNotification notification) {
        if (!notification.isResponded()) {
            JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton acceptButton = new JButton("Accept");
            JButton declineButton = new JButton("Decline");

            acceptButton.addActionListener(e -> {
                NotificationService.getInstance().respondToFriendRequest(notification, true, users);
                markAsResponded(notification, actionsPanel, "Group invitation accepted.");
            });

            declineButton.addActionListener(e -> {
                NotificationService.getInstance().respondToFriendRequest(notification, false,users);
                markAsResponded(notification, actionsPanel, "Group invitation declined.");
            });

            actionsPanel.add(acceptButton);
            actionsPanel.add(declineButton);
            notificationPanel.add(actionsPanel, BorderLayout.SOUTH);
        } else {
            JLabel responseLabel = new JLabel("Already responded.");
            notificationPanel.add(responseLabel, BorderLayout.SOUTH);
        }
    }
     
     private void markAsResponded(Notification notification, JPanel actionsPanel, String message) {
        actionsPanel.removeAll();
        JLabel responseLabel = new JLabel(message);
        responseLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        actionsPanel.add(responseLabel);
        actionsPanel.revalidate();
        actionsPanel.repaint();
    }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        notificationsScrollPanel = new javax.swing.JScrollPane(notificationsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        notificationsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        notificationsPanel.setBackground(new java.awt.Color(255, 255, 255));
        notificationsPanel.setLayout(new javax.swing.BoxLayout(notificationsPanel, javax.swing.BoxLayout.Y_AXIS));
        notificationsScrollPanel.setViewportView(notificationsPanel);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setText("Notifications");

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/refresh2.png"))); // NOI18N
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(notificationsScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)))
                .addContainerGap(348, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(46, Short.MAX_VALUE)
                    .addComponent(notificationsScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        loadNotifications();
    }//GEN-LAST:event_refreshMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NotificationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotificationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotificationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotificationWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel notificationsPanel;
    private javax.swing.JScrollPane notificationsScrollPanel;
    private javax.swing.JLabel refresh;
    // End of variables declaration//GEN-END:variables
}
