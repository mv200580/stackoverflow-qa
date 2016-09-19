import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by smv on 19/09/16.
 */
public class JsonObjectTest {

    @Test
    public void run() throws IOException {
        InputStream req = this.getClass().getResourceAsStream("req.json");

        String data = IOUtils.toString(req, "UTF-8");

        JSONObject json = new JSONObject(data);
        JSONArray articles = json.getJSONArray("articles");
        System.out.println("Number of articles: " + articles.length());
        System.out.println("Structure json - " + articles);
        for(int i = 0 ; i < articles.length() ; i++){
            //JSONObject article = new JSONObject(articles.optJSONObject(i));   wrong way
            JSONObject article = articles.optJSONObject(i);
            System.out.println("Struttura json - " + article);
        }

    }

}
