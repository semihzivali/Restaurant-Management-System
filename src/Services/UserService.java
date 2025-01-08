package Services;

import java.util.List;

import Repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public boolean removeUser(String username, String password) {
        return userRepository.removeUser(username, password);
    }

    public List<String> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public boolean writeToDatabase(String userName, String userPassword, String userRole) {
        return userRepository.writeToDatabase(userName, userPassword, userRole);
    }

    public int getUserId(String username, String password, String role) {
        return userRepository.getUserId(username, password, role);
    }
}
