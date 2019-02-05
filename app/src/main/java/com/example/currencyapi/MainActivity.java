package com.example.currencyapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                try {
                    Personal currency = new Personal("RON");
                    currency = new Personal("USD");
                    Double value = currency.getCurrency("GBP");
                    textView.setText(value.toString());
                }
                catch (Exception e){

                }
            }
        });

    }
}
