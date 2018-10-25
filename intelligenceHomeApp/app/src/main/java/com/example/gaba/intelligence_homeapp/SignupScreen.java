package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.*;


public class SignupScreen extends AppCompatActivity {

    DatabaseReference mDatabase;

    User user;

    EditText addUser;
    EditText addPassword;
    RadioButton homeOwner;
    RadioButton serviceProvider;
    RadioButton admin;
    Button createAcc;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        createAcc = (Button) findViewById(R.id.createAcc);
        admin = (RadioButton) findViewById(R.id.rb_AD);
        MyDBHandler dbHandler = new MyDBHandler(this);

        if(dbHandler.findAdmin() != null){
            admin.setEnabled(false);
            admin.setText("Administrator (already exists)");
        }

    }

    public void newUser (View view) {

        addUser = (EditText) findViewById(R.id.addUser);
        addPassword = (EditText) findViewById(R.id.addPassword);
        homeOwner = (RadioButton) findViewById(R.id.rb_HO);
        serviceProvider = (RadioButton) findViewById(R.id.rb_SP);
        //admin = (RadioButton) findViewById(R.id.rb_AD);
        role = "";

        if (homeOwner.isChecked()){
            role = "Home Owner";
        }
        else if (serviceProvider.isChecked()) {
            role = "Service Provider";
        }
        else if (admin.isChecked()){
            role = "Administrator";
        }
        user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
        //Users.addUser(user);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        final String roleUser = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();


        // TODO: add to database
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addUser(user);
        //dbHandler.deleteUser(user.getUsername());
        addUser.setText("");
        addPassword.setText("");


        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        //intent.putExtra("Role",roleUser);
        startActivityForResult(intent,0);
    }
}
