package DbClasses;

public class App {
    private int id;
    private String appName;
    private String description;
    private double price;
    private int numDownloads;

    /*
        Point of class: store a row of data from the database: apps

     */
    public void setId(int id) {
        this.id = id;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumDownloads(int numDownloads) {
        this.numDownloads = numDownloads;
    }

    public int getId() {
        return id;
    }

    public String getAppName() {
        return appName;
    }

    public double getPrice() {
        return price;
    }

    public int getNumDownloads() {
        return numDownloads;
    }

    public App(int id, String appName, String description, double price, int numDownloads)
    {
        this.id = id;
        this.appName = appName;
        this.description = description;
        this.price = price;
        this.numDownloads = numDownloads;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
