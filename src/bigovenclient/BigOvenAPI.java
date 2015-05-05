package bigovenclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class BigOvenAPI {
    private final String apiKey;
    
    private final String baseURL = "http://api.bigoven.com/";

    public BigOvenAPI(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public Recipe getRecipe(int id) throws MalformedURLException, IOException {
        String urlString = baseURL + "recipe/" + id + "&api_key=" + apiKey;
        
        URL url = new URL(urlString);

        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        
        BufferedReader inReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String jsonString = inReader.readLine();
        //JSONObject json = new JSONObject(jsonString);
        
        return null;
    }
    
    public ArrayList<Recipe> searchRecipes(String searchTerm, int page, int resultsPerPage) throws MalformedURLException, IOException {
        String urlString = baseURL + "recipes?title_kw=" + searchTerm
                + "&api_key=" + apiKey 
                + "&pg=" + page 
                + "&rpp=" + resultsPerPage;

        URL url = new URL(urlString);

        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader inReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String jsonString = inReader.readLine();
        JSONObject json = new JSONObject(jsonString);

        JSONArray recepies = json.getJSONArray("Results");

        ArrayList<Recipe> recipes = new ArrayList<>();
        
        int d = recepies.length();
        for (int i = 0; i < d; i++) {
            JSONObject recipe = recepies.getJSONObject(i);
            recipes.add(new Recipe(recipe));
        }
        
        return recipes;
    }
}
