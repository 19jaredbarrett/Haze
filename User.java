package Hazemain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class User {

	public String username;
	public String password;
		
	public User() {}
	
	/**
	 * Creates a user object using account credentials.
	 * @param user The username of the user account.
	 * @param pword The password for a target user account.
	 */	
	public User(String user, String passd) {	
		username = user;
		password = passd;	
	}
	
	/**
	 * Updates an application that the developer has access to.
	 * @param AOT The instance of the tool where the new user is being signed up to.
	 * @return true if the signup was sucessful, false if not.
	 */
	public boolean signUp(HazeApp AOT) {
		if (!AOT.users.containsKey(username)) {
			AOT.users.put(username, password);
			try { 
		        	PrintWriter writer = new PrintWriter(new FileWriter("login.txt",true));
		        	writer.write(username + "," + password + '\n');
		        	writer.close();
			} catch(IOException e) {
				System.out.println("login.txt file Not Found");
			}
			return true;
		}
		return false;
	}

	/**
	 * Allows a user to log in, and be stored as the user in the tool.
	 * @param AOT The instance of the tool where the new user is being logged in to.
	 * @param user The username of the desired user to log in.
	 * @param pword The password of the desired user to log in.
	 * @return true if the login was sucessful, false if not.
	 */
	public boolean login(HazeApp AOT, String user, String pword) {
		if (AOT.users.containsKey(user) && AOT.users.get(user).equals(pword)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Allows a user to log out, preventing a comment and rating from being written by their account.
	 * @param AOT The instance of the tool where the new user is being logged in to.
	 * @return true if the logout was sucessful.
	 */
	public boolean logout(HazeApp AOT) {
		AOT.currentUser = new User("","");
		return true;
	}
	
	
	
}