package DbClasses;

public class App {
    private int id;
    private String appName;
    private String description;
    private double price;
    private int numDownloads;
    private int rating;

    /**
     * Creates an App object using App credentials.
     * Store a row of data from the database: apps.
     * @param id Int. The id of each App, must be larger than 0, otherwise will throw a IllegalArgument Exception.
     * @param appName String. The name for each app.
     * @param description String. The description for each app.
     * @param price Double. The price for each app.
     * @param numDownloads Int. The download number for each app.
     * @param rating Int. The rating of each app, must be in the range between 0 to 10, otherwise will throw a IllegalArgument Exception.
     */
    public App(int id, String appName, String description, double price, int numDownloads, int rating)
    {
        this.id = id;
        this.appName = appName;
        this.description = description;
        this.price = price;
        this.numDownloads = numDownloads;
        this.rating = rating;
    }
    public int getRating() {
        // rating must be in the range between 0 to 10
        if (rating < -1 || rating > 10) {
            throw new IllegalArgumentException();
        }
        else return rating;
    }

    public void setRating(int rating) {
        // rating must be in the range between 0 to 10
        if (rating < -1 || rating > 10) {
            throw new IllegalArgumentException("This app's rating is error, rating must be between 0 to 10: "+ appName );
        }
        this.rating = rating;
    }


    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("This app's id is wrong, id must be large than 0: "+ appName );
        }
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
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        else return id;
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



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
