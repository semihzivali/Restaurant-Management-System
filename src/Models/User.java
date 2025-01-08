package Models;

public class User {
	    
	    private static int userId;
	    private String userName;
	    private String password;
	    private String role;

	    // Constructor
	    public User(String userName, String password, String role) {
	    	
	        this.userName = userName;
	        this.password = password;
	        this.role = role;
	    }
	    
	    public static void setWaiterId(int id) {
	    	userId = id;
	    }

	    public static int getWaiterId() {
	        return userId;
	    }

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	    
	    
}
