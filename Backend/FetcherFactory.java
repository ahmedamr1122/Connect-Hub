/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author asus
 */

public class FetcherFactory {

    // Factory method to create ContentFetcher instance
    public static IContentFetcher createContentFetcher() {
        
        return new ContentFetcher();
    }

    // Factory method to create FriendFetcher instance
    public static IFriendFetcher createFriendFetcher() {
        return new FriendFetcher();
    }
}


