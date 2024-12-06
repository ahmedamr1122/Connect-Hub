package Backend.friends;

import Backend.user.User;
import java.util.ArrayList;
import java.util.List;

public class FriendManagerImplement implements FriendManager{
    

    @Override
    public void sendFriendRequest(User sender, User receiver) {
        
        if(receiver.getBlocked().contains(sender))
        {
            System.out.println("ERROR: This User blocked you.");
            return;
        }
        
        FriendRequest request = new FriendRequest(sender.getUserId() , receiver.getUserId(), RequestStatus.PENDING);
        
        sender.getSentRequests().add(request);
        receiver.getReceivedRequests().add(request);
                                
    }

    @Override
    public void respondToFriendRequest(User sender, User receiver, RequestStatus response) {
        FriendRequest request = FriendRequest.findRequest(sender, receiver);
        if(response == RequestStatus.ACCEPTED)
        {
            sender.getFriends().add(receiver);
            receiver.getFriends().add(sender);
        }
        
            
            sender.getSentRequests().remove(request);
            receiver.getReceivedRequests().remove(request);  
        
        
        
    }

    @Override
    public void removeFriend(User sender, User friend) {
        sender.getFriends().remove(friend);
        friend.getFriends().remove(sender);
    }

    @Override
    public List<User> suggestFriends(User user, List<User> users) {
        
        List<User> suggestions = new ArrayList<>();
        for(User findUser : users)
        {
            if(!user.equals(findUser) && !user.getBlocked().contains(findUser) && !user.getFriends().contains(findUser))
                suggestions.add(findUser);
            
        }
        return suggestions;
    }
}
