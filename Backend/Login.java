/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author ahmed
 */
public class Login {
    private UserAccountManagement UAM;
    // Method to login a user

    public Login() {
        UAM = new UserAccountManagement();
    }
    
    public boolean login(String email, String password) {
        if(UAM.contain(email)){
            User user = UAM.findUserByEmail(email);
              if(user.getPassword().equals(new PasswordHashing().hashedPass(password))){
                  user.setStatus(Status.ONLINE);
                  return true;
        }
        }
       
        return false;
    }
}
