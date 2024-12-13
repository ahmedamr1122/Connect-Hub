package Backend.Group;

import java.util.List;

public class FindGroup {
    
    public static Group findGroupById(String groupId) {
    for (Group group : groupService.getAllGroups()) {
        if (group.getGroupId().equals(groupId)) {
            return group;
        }
    }
    return null; // Group not found
}
    
}
