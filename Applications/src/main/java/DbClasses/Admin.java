package DbClasses;

public class Admin extends Moderator {

	public Admin(String username, String password, double balance,int accessLevel) {
		super(username, password,balance,accessLevel);
		setAccessLevel(2);
	}
	 
	
}