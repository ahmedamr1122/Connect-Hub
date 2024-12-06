package Backend.friends;

import Backend.user.User;

public class FriendRequest {
    
    private User sender;
    private User receiver;
    private RequestStatus status;

    public FriendRequest(User sender, User receiver, RequestStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
    public FriendRequest findRequest(User sender,User receiver){
            FriendRequest request = null;
            for (int i=0;i< sender.getSentRequests().size();i++){
                User user = sender.getSentRequests().get(i).getReceiver();
                if(user.getUserId().equals(receiver.getUserId())){
                    request = sender.getSentRequests().get(i);
                    break;
                }
            }
            return request;
    }
    
    
    
}
