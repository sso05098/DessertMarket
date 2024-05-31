package bookmarket.model;

public class Admin extends User {
<<<<<<< HEAD
	private String id="admin";
	private String password="1234";
	
	public boolean login(String id, String password) {
		if (this.id.equals(id) && this.password.equals(password))
			return true;
		return false;
	}
=======
	private String id = "admin";
	private String password = "1234";
	
	public boolean login(String id, String password) {
		if (this.id.equals(id) && this.password.equals(password)) return true;
		return false;
	}
	
>>>>>>> refs/remotes/origin/master
}
