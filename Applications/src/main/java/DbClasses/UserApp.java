package DbClasses;

public class UserApp {

    private int appId;
    private String appName;
    private int userId;
    private String username;
    private String comment;

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
