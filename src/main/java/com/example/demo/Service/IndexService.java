package com.example.demo.Service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class IndexService {

    public void testHTTP (String targetURL)
    {
        String inputLine;
        try {
            URL url = new URL(targetURL);
            BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            System.out.println("Printing Response Code");
            System.out.println(connection.getResponseCode());

            while(reader.readLine() != null){
                inputLine = reader.readLine();
                System.out.println(inputLine);
            }
            reader.close();
        }

        catch (Exception e){
            System.out.println("No connection");
        }
    }
}
