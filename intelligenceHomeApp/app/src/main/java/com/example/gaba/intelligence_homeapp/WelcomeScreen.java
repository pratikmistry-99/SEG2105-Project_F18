package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// class used to create the welcome screen once you log in
public class WelcomeScreen extends AppCompatActivity {

    TextView userNameDisplay;
    TextView userRoleDisplay;
    TextView adminRoleDisplay;
    String str;
    User user;
    String role;

    @Override
    // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        userRoleDisplay = findViewById(R.id.roleTxt);
        userNameDisplay = findViewById(R.id.userDisplay);
        adminRoleDisplay = findViewById(R.id.adminDisplay);

        MyDBHandler dbHandler = new MyDBHandler(this);
        User user = dbHandler.findUser(getIntent().getStringExtra("Name"));
        str = "Welcome "+user.getUsername()+"!";
        userNameDisplay.setText(str);
        userRoleDisplay.setText(user.getRole() + " Account");
        role = user.getRole();
        String ad = "";
        if (user.getRole().equals("Administrator")){
            ad = dbHandler.getAllUsernames();
            adminRoleDisplay.setText(ad);

        }
        else
            adminRoleDisplay.setText("");
        findViewById(R.id.servList).setVisibility(View.VISIBLE);
    }

    // Method to start the activity
    public void sList(View view){

        Intent intent = new Intent(getApplicationContext(),ServiceList.class);
        intent.putExtra("role", role);
        startActivityForResult(intent,0);
    }
}
