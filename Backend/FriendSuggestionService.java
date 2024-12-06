/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author asus
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendSuggestionService {

    private final IUserAccountManagement userAccountManagement;
    private final FriendFetcher friendFetcher;

    public FriendSuggestionService(IUserAccountManagement userAccountManagement, FriendFetcher friendFetcher) {
        this.userAccountManagement = userAccountManagement;
        this.friendFetcher = friendFetcher;
    }

    // Method to fetch friend suggestions for a user
    public List<FriendSuggestion> fetchFriendSuggestions(String userId) throws IOException {
        List<FriendSuggestion> suggestions = new ArrayList<>();
        List<User> allUsers = userAccountManagement.loadUsers("Users.json");
        List<Friend> friends = friendFetcher.fetchUserFriends(userId);  // Get the user's current friends
        
        // Loop through all users to check for friend suggestions
        for (User potentialFriend : allUsers) {
            // Skip if the potential friend is already in the user's friend list or is the user itself
            boolean isAlreadyFriend = friends.stream()
                .anyMatch(friend -> friend.getFriendId().equals(potentialFriend.getUserId()));

            if (!isAlreadyFriend && !potentialFriend.getUserId().equals(userId)) {
                FriendSuggestion suggestion = new FriendSuggestion(potentialFriend.getUserId(), potentialFriend.getUsername());
                suggestions.add(suggestion);
            }
        }
        return suggestions;
    }
}
