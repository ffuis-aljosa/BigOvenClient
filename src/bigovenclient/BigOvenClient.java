package bigovenclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BigOvenClient {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://api.bigoven.com/recipes?title_kw=pizza&api_key=dvx3l5Ep57z942tATiUC7x8E9J8E50vz&pg=1&rpp=10");
            
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            
            BufferedReader inReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line;
            
            while((line = inReader.readLine()) != null)
            {
                System.out.println(line);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(BigOvenClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BigOvenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
