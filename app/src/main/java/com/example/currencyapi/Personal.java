package com.example.currencyapi;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Personal {
    public static final String ACCESS_KEY = "3cf5735082c228893b65ff2bfb959c5c";
    public static final String BASE_URL = "https://apilayer.net/api/";
    public static final String ENDPOINT = "live";
    static URL url;
    static String source = "";

    Personal (String source) throws MalformedURLException {
        this.source = source;
        setURL();
    }

    void setURL() throws MalformedURLException {
        URL url = new URL(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);
        this.url = url;
    }

    public static Date getTimeUpdate() throws IOException, JSONException{
        HttpRequest conn = new HttpRequest(url);
        JSONObject exchangeRates = new JSONObject();
        exchangeRates = conn.prepare(HttpRequest.Method.GET).sendAndReadJSON();
        Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        return timeStampDate;
    }

    public double getCurrency(String currency) throws IOException, JSONException{
        URL url = new URL(this.url + "&source=" + this.source);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("GET");
//		System.out.println("Response Code: " + connection.getResponseCode());
        HttpRequest conn = new HttpRequest(url);
        JSONObject exchangeRates = new JSONObject();
        exchangeRates = conn.prepare(HttpRequest.Method.GET).sendAndReadJSON();
        double s2 = exchangeRates.getJSONObject("quotes").getDouble(source + currency);
        return s2;
    }

}