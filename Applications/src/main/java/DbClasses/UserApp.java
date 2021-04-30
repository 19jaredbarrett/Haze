package DbClasses;

public class UserApp {

    private int appId;
    private String appName;
    private int userId;
    private String username;
    private String comment;

    /**
     * displays user's comment when a user clicks on the table
     * @param appId Int. The id for the app.
     * @param appName String. The name for the app.
     * @param userId Int. The id number for the user.
     * @param username String. The username for the user.
     * @param comment String. The comment to the app, written by the user.
     */
    public UserApp(int appId, String appName, int userId, String  username, String comment) {
        this.appId = appId;
        this.appName = appName;
        this.userId = userId;
        this.username = username;
        this.comment = comment;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
