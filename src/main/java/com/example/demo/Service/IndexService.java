package com.example.demo.Service;

import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class IndexService {

    public void testHTTP (String targetURL)
    {
        try {
            URL url = new URL(targetURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            System.out.println("Printing Response Code");
            System.out.println(connection.getResponseCode());
        }

        catch (Exception e){
            System.out.println("No connection");
        }
    }
}
