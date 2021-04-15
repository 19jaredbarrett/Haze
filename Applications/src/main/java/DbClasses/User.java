package DbClasses;

public class User {
    private String username;
    private String password;
    private double balance;
    // 0-2, 2 is admin
    private int accessLevel;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // default 50 bucks and regular member
        this.balance = 50.00;
        this.accessLevel = 0;
    }



    public User(String username, char [] passwordChar) {
        this.username = username;
        this.password = passwordChar.toString();
        // default 50 bucks and regular member
        this.balance = 50.00;
        this.accessLevel = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }





    // purpose of isExist
    public int getAccessLevelInt() {
        return accessLevel;
    }

    public String getAccessLevel() {
        switch (accessLevel) {
            case 0:
                return "Member";
            case 1:
                return "Moderator";
            case 2:
                return "Admin";
        }
        return "not accessible";
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * Creates a user object using account credentials.
     * @param username The username of the user account.
     * @param password The password for a target user account.
     */
    public User(String username, String password, double balance, int accesslevel) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.accessLevel = accesslevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }



}
