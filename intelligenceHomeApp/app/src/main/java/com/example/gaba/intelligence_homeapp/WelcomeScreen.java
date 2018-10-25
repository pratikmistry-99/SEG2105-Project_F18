package com.example.gaba.intelligence_homeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class WelcomeScreen extends AppCompatActivity {

    TextView userNameDisplay;
    TextView userRoleDisplay;
    String str;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        userRoleDisplay = findViewById(R.id.userRoleDisplay);
        userNameDisplay = findViewById(R.id.userNameDisplay);
        str = getIntent().getExtras().getString("value");
        userNameDisplay.setText(str);
        userRoleDisplay.setText(Users.getUser(str).getRole());

    }
}
