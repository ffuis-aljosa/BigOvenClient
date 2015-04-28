package bigovenclient;

import java.util.Date;

public class Recipe {
    private int id;
    private String title;
    private String cuisine;
    private String category;
    private double starRating;
    private String imageURL;
    private String posterName;
    private Date creationDate;

    public Recipe(int id, String title, String cuisine, String category, double starRating, String imageURL, String posterName, Date creationDate) {
        this.id = id;
        this.title = title;
        this.cuisine = cuisine;
        this.category = category;
        this.starRating = starRating;
        this.imageURL = imageURL;
        this.posterName = posterName;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getCategory() {
        return category;
    }

    public double getStarRating() {
        return starRating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getPosterName() {
        return posterName;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
