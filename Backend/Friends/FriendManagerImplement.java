package Backend.friends;

import Backend.user.User;

public class FriendManagerImplement implements FriendManager{
    

    @Override
    public void sendFriendRequest(User sender, User receiver) {
        
        if(receiver.getBlocked().contains(sender))
        {
            System.out.println("ERROR: This User blocked you.");
            return;
        }
        
        FriendRequest request = new FriendRequest(sender , receiver, RequestStatus.PENDING);
        
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
    
    
            
    
}
