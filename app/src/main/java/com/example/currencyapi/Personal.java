package com.example.currencyapi;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Personal {
    public static final String ACCESS_KEY = "3cf5735082c228893b65ff2bfb959c5c";
    public static final String BASE_URL = "http://apilayer.net/api/";
    public static final String ENDPOINT = "live";
    public static String source = "";
    Personal (String source){
        this.source = source;
    }
    public static double getData(String currency) throws IOException, JSONException{
        URL url = new URL(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("GET");
//		System.out.println("Response Code: " + connection.getResponseCode());
        HttpRequest conn = new HttpRequest(url);
        JSONObject exchangeRates = new JSONObject();
        exchangeRates = conn.prepare(HttpRequest.Method.GET).sendAndReadJSON();
        Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String formattedDate = dateFormat.format(timeStampDate);
        String s = exchangeRates.getString("source");
        double s2 = exchangeRates.getJSONObject("quotes").getDouble(source + currency);
        return s2;
    }

}