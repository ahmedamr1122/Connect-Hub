package Backend.Group;

import Backend.friends.RequestStatus;
import Backend.user.User;

public class GroupRequest {
    private String user;
    private String group;
    private RequestStatus status;

    public GroupRequest(String user, String group, RequestStatus status) {
        this.user = user;
        this.group = group;
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
    
    
}
