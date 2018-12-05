package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


// class that is used to create a sign-up screen, where you can create users and admin
public class SignupScreen extends AppCompatActivity{


    User user;

    EditText addUser;
    EditText addPassword;
    RadioButton homeOwner;
    RadioButton serviceProvider;
    RadioButton admin;
    Button createAcc;
    String role;
    TextView nameBag;
    TextView passwordBag;
    TextView generalBag;

    boolean usernameError;

    /**
     * Methods create an admin account if the account
     * If admin account was already created throws exception
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        nameBag = (TextView) findViewById(R.id.Empty_Name);
        passwordBag = (TextView) findViewById(R.id.Empty_Pass);
        generalBag = (TextView) findViewById(R.id.Empty_All);
        nameBag.setVisibility(nameBag.INVISIBLE);
        passwordBag.setVisibility(passwordBag.INVISIBLE);
        generalBag.setVisibility(generalBag.INVISIBLE);
        createAcc = (Button) findViewById(R.id.createAcc);
        //createAcc.setOnClickListener(this);
        admin = (RadioButton) findViewById(R.id.rb_AD);
        MyDBHandler dbHandler = new MyDBHandler(this);

        if (dbHandler.findAdmin() != null) {
            admin.setVisibility(View.GONE);
            admin.setText("Administrator (already exists)");
        }
        usernameError = false;
    }

    /**
     * Methods creates a new user account
     *
     * @param view View
     */
    public void newUser(View view) {

        addUser = (EditText) findViewById(R.id.addUser);
        addPassword = (EditText) findViewById(R.id.addPassword);
        homeOwner = (RadioButton) findViewById(R.id.rb_HO);
        serviceProvider = (RadioButton) findViewById(R.id.rb_SP);
        //admin = (RadioButton) findViewById(R.id.rb_AD);
        if((addUser.getText().toString().length()==0 || addPassword.getText().toString().length()==0)&&!usernameError){
            finish();
            Intent intent = new Intent(getApplicationContext(),SignupScreen.class);
            Toast.makeText(getApplicationContext(), "Please fill all fields!",Toast.LENGTH_SHORT).show();
            startActivityForResult(intent,0);
        }
        else
        {
            usernameError = false;
            role = "";

            if (homeOwner.isChecked()) {
                role = "Home Owner";
            } else if (serviceProvider.isChecked()) {
                role = "Service Provider";
            } else if (admin.isChecked()) {
                role = "Administrator";
            }
            try {
                user = new User(addUser.getText().toString(), PasswordEncryption.encrypt(addPassword.getText().toString()), role);
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(), "Error Creating Account!",Toast.LENGTH_SHORT).show();
                return;
            }
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);


            MyDBHandler dbHandler = new MyDBHandler(this);
            if(dbHandler.findUser(user.getUsername())==null)
            {
                dbHandler.addUser(user);
                addUser.setText("");
                addPassword.setText("");

                finish();// this makes sure that the signup screen activity ends before logging in.
                Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                startActivityForResult(intent,0);
            }
            else
            {
                addUser.setText("");
                addPassword.setText("");
                Toast.makeText(getApplicationContext(), "This Username Already Exists!",Toast.LENGTH_SHORT).show();
                usernameError = true;
                finish();
                Intent intent = new Intent(getApplicationContext(),SignupScreen.class);
                startActivityForResult(intent,0);
            }
        }
    }

}
