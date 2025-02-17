package Services.Abstract;

import java.util.List;

public interface IUserService {
	
	public boolean removeUser(String username, String password);
	
	 public List<String> getAllUsers();
	 
	 public boolean addUser(String userName, String userPassword, String userRole);
	 
	 public int getUserId(String username, String password, String role);

}
