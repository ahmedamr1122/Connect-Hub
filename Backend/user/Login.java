
package Backend.user;

/**
 *
 * @author ahmed
 */
public class Login{
    
    // Method to log in
    public static boolean login(String email, String password) {

        ContainUser containUser = new ContainUser();
        FindUser findUser = new FindUser();

        if (containUser.containEmail(email)) {
            User user = findUser.findUserByEmail(email);
            if (user.getPassword().equals(new PasswordHashing().hashedPass(password))) {
                user.setStatus(Status.ONLINE);
                return true;
            }
        }

        return false;
    }
    
}
