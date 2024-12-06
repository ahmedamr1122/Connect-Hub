/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Backend;

import java.io.IOException;
import java.util.List;

public interface IContentFetcher {
    List<Post> fetchUserPosts(String userId) throws IOException;
}
