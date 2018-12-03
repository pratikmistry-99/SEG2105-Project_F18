package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// class used to create a login screen, to allow users and admin to login
public class LoginScreen extends AppCompatActivity {

    EditText userName;
    EditText pswrd;
    Button loginButton;
    MyDBHandler dbHandler = new MyDBHandler(this);

    /**
     *
     * @param savedInstanceState Bundle
     */
    @Override
    // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginButton = findViewById(R.id.loginBtn);
        userName = findViewById(R.id.username);
        pswrd = findViewById(R.id.password);
    }
    /**
     * Method opens signup activity after User press button sign up
     * @param view View
     */
    public void signUp(View view){
        Intent intent = new Intent(getApplicationContext(),SignupScreen.class);
        startActivityForResult(intent,0);
    }

    //Ended up having to override and make a onClick() method above inside the onCreate that basically does what signIn does
    /**
     * Method checks User name and password and links it welcome page if information was found in database
     * method throws mistake if information is invalid
     * @param view View
     */
    public void login (View view) {

        User user = dbHandler.findUser(userName.getText().toString());
        //findViewById(R.id.imageView).setVisibility(View.GONE);
        if (user!=null && user.getPassword().toString().equals(pswrd.getText().toString())) {
            pswrd.setText("");
            Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);

            String nameValue = userName.getText().toString();
            intent.putExtra("Name",nameValue);

            String role = getIntent().getStringExtra("Role");
            intent.putExtra("ROLE",role);
            //finish();// this makes sure that the login screen activity ends before logging in.
            startActivityForResult(intent,0);

        } else {
            TextView err = findViewById(R.id.invalid);
            err.setVisibility(View.VISIBLE);
            pswrd.setText("");
        }
    }

}
