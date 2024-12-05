package Backend;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IUserAccountManagement {
    User signup(String email, String username, String password, LocalDate dateOfBirth);
    User login(String username, String password);
    void logout(User user);
    List<User> loadUsers(String filePath) throws IOException;
    void saveUsers(List<User> users, String filePath) throws IOException;
}
