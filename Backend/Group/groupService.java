package Backend.Group;

import Backend.user.User;
import java.util.ArrayList;
import java.util.List;

public class groupService {
    
    private static List<Group> allGroups;
    private static groupService instance;

    private groupService() {
        allGroups = new ArrayList<>();
    }
    
    public static groupService getInstance() {
        if (instance == null) {
            instance = new groupService();
        }
        return instance;
    }

    public static List<Group> getAllGroups() {
        return allGroups;
    }

}

