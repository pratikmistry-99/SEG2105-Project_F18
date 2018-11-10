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

//import com.google.firebase.database.*;


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

/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createAcc:
                if (addUser.getText().toString() == "" && addPassword.getText().toString() == "") {
                    generalBag.setVisibility(generalBag.VISIBLE);
                } else if (addUser.getText().toString() == "") {
                    nameBag.setVisibility(nameBag.VISIBLE);
                } else if (addPassword.getText().toString() == "") {
                    passwordBag.setVisibility(passwordBag.VISIBLE);
                } else {
                    startActivity(new Intent(this, LoginScreen.class));
                }

                break;
        }
    }
*/
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
            admin.setEnabled(false);
            admin.setText("Administrator (already exists)");
        }

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
        if(addUser.getText().toString().length()==0 || addPassword.getText().toString().length()==0){
            finish();
            Intent intent = new Intent(getApplicationContext(),SignupScreen.class);
            Toast.makeText(getApplicationContext(), "Please fill all fields!",Toast.LENGTH_SHORT).show();
            startActivityForResult(intent,0);
        }
        else
        {
            role = "";

            if (homeOwner.isChecked()) {
                role = "Home Owner";
            } else if (serviceProvider.isChecked()) {
                role = "Service Provider";
            } else if (admin.isChecked()) {
                role = "Administrator";
            }
            user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
            //Users.addUser(user);

            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
            //final String roleUser = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();

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

}
