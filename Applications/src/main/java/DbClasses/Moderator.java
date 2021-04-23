package DbClasses;

public class Moderator extends User {

	public Moderator(String username, String password, double balance,int accessLevel) {
		super(username, password,balance,accessLevel);
		setAccessLevel(1);
	}
	 
	
}