package Backend.friends;

import Backend.user.User;

public class FriendRequest {
    
    private String sender;
    private String receiver;
    private RequestStatus status;

    public FriendRequest(String sender, String receiver, RequestStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
    public static FriendRequest findRequest(User sender,User receiver){
            FriendRequest request = null;
            for (int i=0;i< sender.getSentRequests().size();i++){
                String userId = sender.getSentRequests().get(i).getReceiver();
                if(userId.equals(receiver.getUserId())){
                    request = sender.getSentRequests().get(i);
                    break;
                }
            }
            return request;
    }
    
    
    
}
