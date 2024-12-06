/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IFriendFetcher {
    List<Friend> fetchUserFriends(String userId) throws IOException;
}