package com.example.gaba.intelligence_homeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.*;


public class SignupScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

    }

    private void writeNewUser() {
        EditText addUser = (EditText) findViewById(R.id.addUser);
        EditText addPassword = (EditText) findViewById(R.id.addPassword);
        RadioButton homeOwner = (RadioButton) findViewById(R.id.rb_HO);
        RadioButton serviceProvider = (RadioButton) findViewById(R.id.rb_SP);
        String role = "";

        if (homeOwner.equals(true)){
            role = "Home Owner";
        }
        else if (serviceProvider.equals(true)){
            role = "Service Provider";
        }

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userId = mDatabase.push().getKey();

        // creating user object
        User user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
        // pushing user to 'users' node using the userId
        mDatabase.child(userId).setValue(user);


        //myRef.setValue(user);
    }
}
