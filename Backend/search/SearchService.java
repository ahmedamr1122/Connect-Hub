/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.search;

import Backend.Group.Group;
import Backend.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jana
 */
public class SearchService {
    
    private static SearchService instance;
    
    public static synchronized SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }
    
    public List<User> searchUsers(String query, List<User> allUsers, List<User> blocked, User currentUser)
    {
        List<User> matchingUsers = new ArrayList<>();
        for (User user : allUsers) {
            if (user.getUsername().toLowerCase().contains(query.toLowerCase()) && !blocked.contains(user) && !user.equals(currentUser) ) {
                matchingUsers.add(user);
            }
        }
        return matchingUsers;
        
    }
    
    public List<Group> searchGroups(String query, List<Group> allGroups) {
        List<Group> matchingGroups = new ArrayList<>();
        for (Group group : allGroups) {
            if (group.getName().toLowerCase().contains(query.toLowerCase())) {
                matchingGroups.add(group);
            }
        }
        return matchingGroups;
    }
}
