package bigovenclient;

import java.util.Date;
import org.json.JSONObject;

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
    
    public Recipe(JSONObject json) {
        this.id = json.getInt("RecipeID");
        this.title = json.getString("Title");
        this.category = json.getString("Category");
        this.starRating = json.getDouble("StarRating");
        this.imageURL = json.getString("ImageURL");

        this.cuisine = json.isNull("Cuisine") ? "N/A" : json.getString("Cuisine");

        if (!json.isNull("Poster")) {
            JSONObject poster = json.getJSONObject("Poster");

            if (!poster.isNull("UserName"))
                this.posterName = poster.getString("UserName");
            else
                this.posterName = "N/A";
        } else {
            this.posterName = "N/A";
        }

        String date = json.isNull("CreationDate") ? "" : json.getString("CreationDate");

        if (!date.isEmpty()) {
            int from = date.indexOf('(') + 1;
            int to = date.indexOf(')');

            String dateString = date.substring(from, to);

            this.creationDate = new Date(Long.parseLong(dateString));
        } else {
            this.creationDate = null;
        }
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

    @Override
    public String toString() {
        return title;
    }
    
    
}
