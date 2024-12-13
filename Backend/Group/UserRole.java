package Backend.Group;

import Backend.user.User;
import java.util.List;


public interface UserRole {
    void sendJoinRequest(User user, Group group);
    void leaveGroup(Group group, User user);
    List<Group> suggestGroups(User user);
    void acceptInvitation(GroupRequest request, List<User> users);
    void declineInvitation(GroupRequest request);
}
