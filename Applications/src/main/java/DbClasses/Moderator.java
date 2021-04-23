package DbClasses;

public class Moderator extends User {

	public Moderator(String username, String password, int balance,int accessLevel) {
		super(username, password,balance,accessLevel);
		setAccessLevel(1);
	}
	 
	
}