package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.*;


public class SignupScreen extends AppCompatActivity {

    DatabaseReference mDatabase;

    User user;

    EditText addUser;
    EditText addPassword;
    RadioButton homeOwner;
    RadioButton serviceProvider;
    Button createAcc;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        createAcc = (Button) findViewById(R.id.createAcc);
/**
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser = (EditText) findViewById(R.id.addUser);
                addPassword = (EditText) findViewById(R.id.addPassword);
                homeOwner = (RadioButton) findViewById(R.id.rb_HO);
                serviceProvider = (RadioButton) findViewById(R.id.rb_SP);
                role = "";

                if (homeOwner.equals(true)){
                    role = "Home Owner";
                }
                else if (serviceProvider.equals(true)) {
                    role = "Service Provider";
                }
                user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
                Users.addUser(user);


                // TODO: add to database
                MyDBHandler dbHandler = new MyDBHandler(this);
                dbHandler.addUser(user);
                addUser.setText("");
                addPassword.setText("");

                //skuBox.setText("");



                //
                Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                startActivityForResult(intent,0);
            }
        });
*/
    }

    public void newUser (View view) {

        addUser = (EditText) findViewById(R.id.addUser);
        addPassword = (EditText) findViewById(R.id.addPassword);
        homeOwner = (RadioButton) findViewById(R.id.rb_HO);
        serviceProvider = (RadioButton) findViewById(R.id.rb_SP);
        role = "";

        if (homeOwner.equals(true)){
            role = "Home Owner";
        }
        else if (serviceProvider.equals(true)) {
            role = "Service Provider";
        }
        user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
        //Users.addUser(user);


        // TODO: add to database
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addUser(user);
        //dbHandler.deleteUser(user.getUsername());
        addUser.setText("");
        addPassword.setText("");

        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivityForResult(intent,0);
    }


    public void writeNewUser(View view) {
        addUser = (EditText) findViewById(R.id.addUser);
        addPassword = (EditText) findViewById(R.id.addPassword);
        homeOwner = (RadioButton) findViewById(R.id.rb_HO);
        serviceProvider = (RadioButton) findViewById(R.id.rb_SP);
        role = "";

        if (homeOwner.equals(true)) {
            role = "Home Owner";
        } else if (serviceProvider.equals(true)) {
            role = "Service Provider";
        }

        user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
    }











        /**             *****Firebase code***
        mDatabase = FirebaseDatabase.getInstance().getReference("User");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDatabase.child("user02").setValue(user);
                Toast.makeText(SignupScreen.this, "Data Inserted", Toast.LENGTH_LONG).show();
                //Redirect to the login screen
                Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                startActivityForResult(intent,0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */
/**
        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userId = mDatabase.push().getKey();

        // creating user object
        User user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
        // pushing user to 'users' node using the userId
        mDatabase.child(userId).setValue(user.getRole());

*/
        //myRef.setValue(user);
/*
    public void login(View view){
        //writeNewUser();

        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivityForResult(intent,0);
    }*/
}
