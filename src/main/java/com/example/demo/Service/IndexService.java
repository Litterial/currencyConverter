package com.example.demo.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    @Cacheable("currency_api")
    public void testHTTP (String targetURL)
    {

        //read all lines
        String inputLine;
        //StringBuffer is used to append lines
        StringBuffer content= new StringBuffer();
        try {
            URL url = new URL(targetURL);
            // response from a url connection is an inputstream
            BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cache-Control","max-age=3600");
            System.out.println("Printing Response Code");
            System.out.println(connection.getResponseCode());

            while((inputLine = reader.readLine()) != null){
                content.append(inputLine);
            }
            reader.close();
//            System.out.println(content.toString());
            parseJson(content.toString());
        }

        catch (Exception e){
            System.out.println("No connection");
        }
    }

    public static void parseJson (String responseBody)
    {
        JSONObject jsonObject = new JSONObject(responseBody);
        System.out.println(jsonObject);
    }
}
