package DbClasses;

public class Admin extends Moderator {

	public Admin(String username, String password, int balance,int accessLevel) {
		super(username, password,balance,accessLevel);
		setAccessLevel(2);
	}
	 
	
}