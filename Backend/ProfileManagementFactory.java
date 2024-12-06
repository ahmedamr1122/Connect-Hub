/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author asus
 */
public class ProfileManagementFactory {
  
    public static ProfileManagement createProfileManagement(IUserAccountManagement userAccountManagement) {
        return new ProfileManagement(userAccountManagement);
    }
}

