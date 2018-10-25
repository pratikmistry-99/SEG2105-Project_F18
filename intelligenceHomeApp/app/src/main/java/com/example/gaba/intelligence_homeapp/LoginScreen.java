package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    EditText userName;
    EditText pswrd;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginButton = findViewById(R.id.loginBtn);
        userName = findViewById(R.id.username);
        pswrd = findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this,WelcomeScreen.class);
                String strUser = userName.getText().toString();
                String strPass = pswrd.getText().toString();
                intent.putExtra("value", strUser);

                if (Users.getUser(strUser) != null & Users.getPass(strPass) != null){
                    startActivity(intent);
                }else{
                    TextView txt = findViewById(R.id.loginErrorTxt);
                    txt.setText("Please Type in Valid Login Credentials");
                }
            }
        });
    }

    public void signUp(View view){
        Intent intent = new Intent(getApplicationContext(),SignupScreen.class);
        startActivityForResult(intent,0);
    }

    //Ended up having to override and make a onClick() method above inside the onCreate that basically does what signIn does
    public void signIn(View view){

//        String username = ((EditText)findViewById(R.id.username)).getText().toString();
//        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        // Add code to check credentials

//        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
//        startActivityForResult(intent,0);

    }

}
