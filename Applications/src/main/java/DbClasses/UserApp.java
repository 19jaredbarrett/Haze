package DbClasses;

public class UserApp {

    private int appId;
    private String username;
    private String comment;

    public UserApp(int appId, String  username, String comment) {
        this.appId = appId;
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
        return username;
    }

    public void setAppName(String appName) {
        this.username = appName;
    }
}
