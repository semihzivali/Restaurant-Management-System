package Services.Concrete;

import java.util.List;

import Repository.Abstract.IUserRepository;
import Services.Abstract.IUserService;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean removeUser(String username, String password) {
        return userRepository.removeUser(username, password);
    }

    public List<String> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public boolean addUser(String userName, String userPassword, String userRole) {
        return userRepository.addUser(userName, userPassword, userRole);
    }

    public int getUserId(String username, String password, String role) {
        return userRepository.getUserId(username, password, role);
    }
}
