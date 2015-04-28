package bigovenclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class BigOvenClient {

    public static void main(String[] args) {
        
        String search = "pizza";
        String apiKey = "dvx3l5Ep57z942tATiUC7x8E9J8E50vz";
        int currentPage = 1;
        int resultsPerPage = 20;
        
        try {
            String urlString = "http://api.bigoven.com/recipes?title_kw=" + search
                    + "&api_key=" + apiKey 
                    + "&pg=" + currentPage 
                    + "&rpp=" + resultsPerPage;
            
            URL url = new URL(urlString);
            
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            
            BufferedReader inReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String jsonString = inReader.readLine();
            JSONObject json = new JSONObject(jsonString);
            
            JSONArray recepies = json.getJSONArray("Results");
            
            int d = recepies.length();
            
            for (int i = 0; i < d; i++) {
                JSONObject recipe = recepies.getJSONObject(i);
                
                int id = recipe.getInt("RecipeID");
                String title = recipe.getString("Title");
                String category = recipe.getString("Category");
                double starRating = recipe.getDouble("StarRating");
                String imageURL = recipe.getString("ImageURL");
                
                String cuisine = recipe.isNull("Cuisine") ? "N/A" : recipe.getString("Cuisine");
                String posterName;
                
                if (!recipe.isNull("Poster")) {
                    JSONObject poster = recipe.getJSONObject("Poster");
                    
                    if (!poster.isNull("UserName"))
                        posterName = poster.getString("UserName");
                    else
                        posterName = "N/A";
                } else {
                    posterName = "N/A";
                }
                
                String date = recipe.isNull("CreationDate") ? "" : recipe.getString("CreationDate");
                Date time;
                
                if (!date.isEmpty()) {
                    int from = date.indexOf('(') + 1;
                    int to = date.indexOf(')');
                    
                    String dateString = date.substring(from, to);
                    
                    time = new Date(Long.parseLong(dateString));
                } else {
                    time = null;
                }
                
                Recipe r = new Recipe(id, title, cuisine, category, starRating, imageURL, posterName, time);
                
                System.out.println(r.getTitle());
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(BigOvenClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BigOvenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
