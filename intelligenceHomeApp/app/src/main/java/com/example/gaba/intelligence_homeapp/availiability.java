package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class availiability extends AppCompatActivity {

    EditText mon;
    EditText tue;
    EditText wed;
    EditText thu;
    EditText fri;
    EditText sat;
    EditText sun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availiability);
        mon = findViewById(R.id.Monday);
        tue = findViewById(R.id.Tuesday);
        wed = findViewById(R.id.Wednesday);
        thu = findViewById(R.id.Thursday);
        fri = findViewById(R.id.Friday);
        sat = findViewById(R.id.Saturday);
        sun = findViewById(R.id.Sunday);

        String[] availabilities;
        MyDBHandler dbHandler = new MyDBHandler(this);
        String av = dbHandler.getAvailabilities(getIntent().getStringExtra("username"));

        if (av != null) {
            availabilities = av.split("/");
            if (availabilities.length == 7) {
                mon.setText(availabilities[0]);
                tue.setText(availabilities[1]);
                wed.setText(availabilities[2]);
                thu.setText(availabilities[3]);
                fri.setText(availabilities[4]);
                sat.setText(availabilities[5]);
                sun.setText(availabilities[6]);

            }
        }

    }

    public void saveBtn(View view){
        String availability = mon.getText().toString()+" / "+tue.getText().toString()+" / "+wed.getText().toString()+" / "+thu.getText().toString()+" / "+fri.getText().toString()+" / "+sat.getText().toString()+" / "+sun.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        String username = getIntent().getStringExtra("username");
        dbHandler.updateAvailability(username, availability);
        finish();
    }

}
