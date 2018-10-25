package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.*;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static com.example.gaba.intelligence_homeapp.MyDBHandler.COLUMN_ROLE;
import static com.example.gaba.intelligence_homeapp.MyDBHandler.TABLE_USERS;


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

    }

    public void newUser (View view) {

        addUser = (EditText) findViewById(R.id.addUser);
        addPassword = (EditText) findViewById(R.id.addPassword);
        homeOwner = (RadioButton) findViewById(R.id.rb_HO);
        serviceProvider = (RadioButton) findViewById(R.id.rb_SP);
        admin = (RadioButton)findViewById(R.id.rb_A);
        role = "";
        TextView validateRole = findViewById(R.id.errorSignTxt);

        if (homeOwner.equals(true)){
            role = "Home Owner";
        }
        else if (serviceProvider.equals(true)) {
            role = "Service Provider";
        }
        //Doesn't let you click on admin if an admin role was already fulfilled
        else if (admin.equals(true) ){
            role = "admin";
        }
        user = new User(addUser.getText().toString(), addPassword.getText().toString(), role);
        //Users.addUser(user);


        // TODO: add to database
        MyDBHandler dbHandler = new MyDBHandler(this);

        //To avoid errors, im testing my condition here, if it passes i would have put it below as another condition for startActivity to work
        if(dbHandler.checkAdmin("admin") == false ){
        }else{
            validateRole.setText("An Admin has already been created.\n  Please select a different user role.");
        }

        dbHandler.addUser(user);
//        dbHandler.deleteUser(user.getUsername());
        addUser.setText("");
        addPassword.setText("");
        //dbHandler.getReadableDatabase();
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);


        //SignUp screen will only go through back to LoginScreen if a role has been selected AND if admin role is checked (see above)
        if (homeOwner.isChecked() || admin.isChecked() || serviceProvider.isChecked()){
            startActivityForResult(intent,0);
        }else{
            validateRole.setText("Please choose a valid user role above");
        }

    }
}

