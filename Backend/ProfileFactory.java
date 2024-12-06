package Backend;

public class ProfileFactory {

    public static Profile createProfile(IUserAccountManagement userAccountManagement) {
        ProfileUpdater profileUpdater = new ProfileUpdater(userAccountManagement);
        IContentFetcher contentFetcher = FetcherFactory.createContentFetcher();
        IFriendFetcher friendFetcher = FetcherFactory.createFriendFetcher();

        return new Profile(profileUpdater, userAccountManagement);
    }
}
