package com.example.demo.Service;

import com.example.demo.Model.CurrencyEnum;
import com.example.demo.Model.CurrencyModel;
import javafx.util.Pair;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class IndexService {

    public ArrayList<HashMap> conversionRequest(CurrencyModel currency) {
        ArrayList<HashMap> hashMapArrayList = new ArrayList<>();

        String base = currency.getBaseCurrency().toString();
        String exchange = currency.getExchangeCurrency().toString();
        Double money=currency.getMoney();

        HashMap<String, String> baseMap;
        HashMap<String, String> exchangeMap;

        baseMap = apiConnection(base,exchange,money);
        exchangeMap = apiConnection(exchange,base,money);

        hashMapArrayList.add(baseMap);
        hashMapArrayList.add(exchangeMap);

        return hashMapArrayList;

    }

    private HashMap<String,String> apiConnection(String base, String exchange, double money) {
        String inputLine;   //inputLine will read each line
        StringBuffer content = new StringBuffer();    //StringBuffer is used to append lines
        HashMap<String,String> connectionMap;

        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + base + "&symbols=" + exchange);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));  // response from a url connection is an inputstream
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            System.out.println("Printing Response Code");
            System.out.println(connection.getResponseCode());

            while ((inputLine = reader.readLine()) != null) { content.append(inputLine); }//As long as current line is not blank, append the line

            reader.close();//close the reader
            connectionMap= parseJson(content.toString(),exchange);

            if(connectionMap.get("success").equals("fail")) {
                return connectionMap;
            }
            Double d_conversion_rate=Double.parseDouble(connectionMap.get("conversion_rate"));
            String conversionAmount=String.valueOf(money/d_conversion_rate);
            connectionMap.put("conversion_amount",conversionAmount);
            return connectionMap;

        }
        catch (Exception e) {
            System.out.println("No connection");

            connectionMap=new HashMap<>();
            connectionMap.put("success","fail");
            connectionMap.put("conversion_rate","Could not connect to endpoint");

            return connectionMap;
        }
    }

    private static HashMap<String,String> parseJson(String responseBody, String conversion_abbr) {
        JSONObject jsonObject = new JSONObject(responseBody);
        HashMap<String,String> jsonMap=new HashMap<>();
        String success="pass";
        if(jsonObject.has("error"))
        {
            success="fail";
            jsonMap.put("success",success);
            jsonMap.put("conversion_rate",conversion_abbr + "is not a valid currency.");
            return jsonMap;
        }
        JSONObject rateObject=jsonObject.getJSONObject("rates");
        String rate=rateObject.get(conversion_abbr).toString();
        jsonMap.put("success",success);
        jsonMap.put("conversion_rate",rate);
        return jsonMap;
    }

    public CurrencyEnum[] allCurrency() {
        return CurrencyEnum.values();
    }


}
