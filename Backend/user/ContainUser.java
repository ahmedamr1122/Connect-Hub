package Backend.user;

import java.util.List;

public class ContainUser extends UserAccountManagement {

    public boolean containEmail(String email, List<User> users) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {

                return true;
            }
        }
        return false;
    }

    public boolean containUserName(String username) {
        for (User user : super.getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {

                return true;
            }
        }
        return false;
    }

}
