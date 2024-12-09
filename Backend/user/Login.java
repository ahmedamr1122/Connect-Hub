
package Backend.user;

import java.util.List;

/**
 *
 * @author ahmed
 */
public class Login{
    
    // Method to log in
    public boolean login(String email, String password,List<User> users) {

        ContainUser containUser = new ContainUser();
        FindUser findUser = new FindUser();
     //   UserAccountManagement UM = new UserAccountManagement();

        if (containUser.containEmail(email,users)) {
            User user = findUser.findUserByEmail(email,users);
            System.out.println("Found user "+ user);
            System.out.println(user.getPassword() +" " + new PasswordHashing().hashedPass(password));
            if (user.getPassword().equals(new PasswordHashing().hashedPass(password))) {
                            System.out.println(user.getPassword() +" " + new PasswordHashing().hashedPass(password));

                user.setStatus(Status.ONLINE);
                return true;
            }
        }

        return false;
    }
    
}
