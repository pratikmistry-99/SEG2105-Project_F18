package com.example.gaba.intelligence_homeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class WelcomeScreen extends AppCompatActivity {

    TextView userNameDisplay;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        userNameDisplay = findViewById(R.id.userDisplay);
        str = getIntent().getExtras().getString("value");
        userNameDisplay.setText(str);

    }
}
