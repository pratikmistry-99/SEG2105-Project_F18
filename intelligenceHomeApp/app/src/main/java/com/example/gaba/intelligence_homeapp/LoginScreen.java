package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void signUp(View view){
        Intent intent = new Intent(getApplicationContext(),SignupScreen.class);
        startActivityForResult(intent,0);
    }

    public void signIn(View view){

        String username = ((EditText)findViewById(R.id.username)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        // Add code to check credentials

        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivityForResult(intent,0);

    }

}
