package Backend.Group;

import Backend.friends.RequestStatus;
import Backend.notification.NotificationService;
import Backend.user.FindUser;
import Backend.user.User;
import java.util.ArrayList;
import java.util.List;

public class AdminGroupRole implements AdminRole{
    
    @Override
    // Create a group
    public Group createGroup(String name, String description, String photoPath, User creator) {
        String groupId = "g" + (groupService.getAllGroups().size() + 1);
        Group group = new Group(groupId, name, description, photoPath, creator);
        creator.addOwnedGroup(group);
        groupService.getAllGroups().add(group);
        return group;
    }
    
    @Override
    // Delete a group
    public void deleteGroup(Group group, User requester) {
        if (group.getPrimaryAdmin().equals(requester)) {
            
            requester.removeOwnedGroup(group);
            
            // Remove the group from all members and admins
            // Static snapshot in for loop while the original group is being modified
            //to avoid ConcurrentModificationException as the iteration expects the list structure to remain unchanged
            for (User member : new ArrayList<>(group.getMembers())) {
                member.getJoinedGroups().remove(group);
                group.removeMember(member);
            }

            // Remove the group from the global list of groups
            groupService.getAllGroups().remove(group);

            // Clear all posts associated with the group
            group.getPosts().clear(); 
            
            System.out.println("Group deleted successfully.");

        } else {
            throw new IllegalArgumentException("Only the Primary Admin can delete the group.");
        }
        
        
    }

    @Override
    // Promote a member to admin
    public void promoteToAdmin(Group group, User requester, User member) {
        if (requester.equals(group.getPrimaryAdmin())) {
            if (group.isMember(member) && !group.isAdmin(member)) {
                group.addAdmin(member);
                System.out.println(member.getUsername() + " has been promoted to admin.");
            } else {
                System.out.println(member.getUsername() + " is already an admin or not a member.");
            }
        } else {
            throw new IllegalArgumentException("Only the Primary Admin can promote members to admin.");
        }
    }

    @Override
    // Demote an admin
    public void demoteAdmin(Group group, User requester, User admin) {
        if (requester.equals(group.getPrimaryAdmin())) {
            if (group.isAdmin(admin) && !group.getPrimaryAdmin().equals(admin)) {
                group.removeAdmin(admin);
                System.out.println(admin.getUsername() + " has been demoted.");
            } else {
                System.out.println(admin.getUsername() + " cannot be demoted.");
            }
        } else {
            throw new IllegalArgumentException("Only the Primary Admin can demote admins.");
        }
    }
    
    @Override
    // Accept join request
    public void acceptJoinRequest(User admin, GroupRequest request, List<User> users)
    {
        String groupId = request.getGroup();
        String senderId = request.getUser();
        NotificationService notificationService = NotificationService.getInstance();

        
        FindUser find = new FindUser();
        User sender = find.findUserById(senderId, users);
        Group group = FindGroup.findGroupById(groupId);
        
        if(group.isAdmin(admin) || group.getPrimaryAdmin().equals(admin))
        {
            if(group.getPendingJoinRequests().contains(request))
            {
                group.getMembers().add(sender);
                request.setStatus(RequestStatus.ACCEPTED);
                group.getPendingJoinRequests().remove(request);
                sender.getJoinedGroups().add(group);
                System.out.println(sender.getUsername() + " has joined the group.");
                notificationService.sendGroupRequestStatusNotification(sender,groupId,RequestStatus.ACCEPTED );
            } else {
                throw new IllegalArgumentException("No pending request exists from this user.");
            }
            
        } else {
            throw new IllegalArgumentException("Only an admin or the primary admin can accept requests.");
        }
    }
    
    @Override
    // Decline a join request
    public void declineJoinRequest(User admin, GroupRequest request,List<User> users) {
        
        String groupId = request.getGroup();
        String senderId = request.getUser();
        FindUser find = new FindUser();
        User sender = find.findUserById(senderId, users);
        Group group = FindGroup.findGroupById(groupId);
        NotificationService notificationService = NotificationService.getInstance();
        
        if (group.isAdmin(admin) || group.getPrimaryAdmin().equals(admin)) {
            request.setStatus(RequestStatus.DECLINED);
            group.getPendingJoinRequests().remove(request);
            notificationService.sendGroupRequestStatusNotification(sender,groupId,RequestStatus.DECLINED );
        } else {
            throw new IllegalArgumentException("Only an admin or the primary admin can decline requests.");
        }
    }
   
    
}
