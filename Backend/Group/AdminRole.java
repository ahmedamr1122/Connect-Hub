package Backend.Group;

import Backend.user.User;
import java.util.List;

public interface AdminRole {
    Group createGroup(String name, String description, String photoPath, User creator);
    void deleteGroup(Group group, User requester);
    void promoteToAdmin(Group group, User requester, User member);
    void demoteAdmin(Group group, User requester, User admin);
    void acceptJoinRequest(User admin, GroupRequest request, List<User> users);
    void declineJoinRequest(User admin, GroupRequest request, List<User> users);
}
