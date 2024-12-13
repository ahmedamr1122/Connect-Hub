package Backend.Group;

import Backend.friends.RequestStatus;
import Backend.user.FindUser;
import Backend.user.User;
import java.util.*;

public class UserGroupRole implements UserRole {
    
    private static UserGroupRole instance;

    public static UserGroupRole getInstance() {
        if (instance == null) {
            instance = new UserGroupRole();
        }
        return instance;
    }

    @Override
    // Add a join request
    public void sendJoinRequest(User user, Group group) {
        if (group.isMember(user)) {
            throw new IllegalArgumentException("User is already a member.");
        } else if (group.getPendingJoinRequests().contains(user)) {
            throw new IllegalArgumentException("User already has a pending request.");
        } else {
            GroupRequest request = new GroupRequest(user.getUserId(), group.getGroupId(), RequestStatus.PENDING);
            group.getPendingJoinRequests().add(request);
        }
    }

    @Override
    // User leaves a group
    public void leaveGroup(Group group, User user) {
        if (group.isMember(user)) {
            if (group.getPrimaryAdmin().equals(user)) {
                throw new IllegalArgumentException("The Primary Admin cannot leave the group.");
            }
            group.removeMember(user);
            user.getJoinedGroups().remove(group);
            System.out.println(user.getUsername() + " has left the group.");
        } else {
            System.out.println(user.getUsername() + " is not a member of the group.");
        }
    }

    @Override
    // Suggest groups to a user
    public List<Group> suggestGroups(User user) {
        List<Group> suggestedGroups = new ArrayList<>();
        for (Group group : groupService.getAllGroups()) {
            if (!group.isMember(user)) {
                suggestedGroups.add(group);
            }
        }
        return suggestedGroups;
    }

    @Override
    // Accept join request
    public void acceptInvitation(GroupRequest request, List<User> users) {
        
        String groupId = request.getGroup();
        String receiverId = request.getUser();
        
        FindUser find = new FindUser();
        User receiver = find.findUserById(receiverId, users);
        Group group = FindGroup.findGroupById(groupId);
        
       
        group.getMembers().add(receiver);
        request.setStatus(RequestStatus.ACCEPTED);
        group.getPendingJoinRequests().remove(request);
        receiver.getJoinedGroups().add(group);
        System.out.println(receiver.getUsername() + " has joined the group.");
        

    }

    @Override
    // Decline a join request
    public void declineInvitation(GroupRequest request) {
        String groupId = request.getGroup();
        Group group = FindGroup.findGroupById(groupId);
        request.setStatus(RequestStatus.DECLINED);
        group.getPendingJoinRequests().remove(request);
    }

}
