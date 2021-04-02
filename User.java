package DbClasses;

public class User {
    private String username;
    private String password;
    // purpose of isExist
    private boolean isUserLoggedIn;



    /**
     * Creates a user object using account credentials.
     * @param username The username of the user account.
     * @param password The password for a target user account.
     */
    public User(String username, String password, boolean isUserLoggedIn) {
        this.username = username;
        this.password = password;
        this.isUserLoggedIn = isUserLoggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isUserExists() {
        return isUserLoggedIn;
    }

    public void setUserExists(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }


}
