package DbClasses;

public class UserApp {

    private int appId;
    private String appName;
    private String comment;

    public UserApp(int appId, String  appName, String comment) {
        this.appId = appId;
        this.appName = appName;
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
}
