package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class availiability extends AppCompatActivity {

    EditText mon_f;
    EditText tue_f;
    EditText wed_f;
    EditText thu_f;
    EditText fri_f;
    EditText sat_f;
    EditText sun_f;
    EditText mon_t;
    EditText tue_t;
    EditText wed_t;
    EditText thu_t;
    EditText fri_t;
    EditText sat_t;
    EditText sun_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availiability);
        mon_f = findViewById(R.id.mf);
        tue_f = findViewById(R.id.tuf);
        wed_f = findViewById(R.id.wf);
        thu_f = findViewById(R.id.thf);
        fri_f = findViewById(R.id.ff);
        sat_f = findViewById(R.id.saf);
        sun_f = findViewById(R.id.suf);
        mon_t = findViewById(R.id.mt);
        tue_t = findViewById(R.id.tut);
        wed_t = findViewById(R.id.wt);
        thu_t = findViewById(R.id.tht);
        fri_t = findViewById(R.id.ft);
        sat_t = findViewById(R.id.sat);
        sun_t = findViewById(R.id.sut);

        String[] availabilities;
        MyDBHandler dbHandler = new MyDBHandler(this);
        String av = dbHandler.getAvailabilities(getIntent().getStringExtra("username"));


        if (av != null) {
            availabilities = av.split("//");
            if (availabilities.length == 7) {
                mon_f.setText(availabilities[0].split("-")[0].trim());
                tue_f.setText(availabilities[1].split("-")[0].trim());
                wed_f.setText(availabilities[2].split("-")[0].trim());
                thu_f.setText(availabilities[3].split("-")[0].trim());
                fri_f.setText(availabilities[4].split("-")[0].trim());
                sat_f.setText(availabilities[5].split("-")[0].trim());
                sun_f.setText(availabilities[6].split("-")[0].trim());

                mon_t.setText(availabilities[0].split("-")[1].trim());
                tue_t.setText(availabilities[1].split("-")[1].trim());
                wed_t.setText(availabilities[2].split("-")[1].trim());
                thu_t.setText(availabilities[3].split("-")[1].trim());
                fri_t.setText(availabilities[4].split("-")[1].trim());
                sat_t.setText(availabilities[5].split("-")[1].trim());
                sun_t.setText(availabilities[6].split("-")[1].trim());

            }
        }
    }

    public void saveBtn(View view){
        boolean validInput = true;
        try {
            if (Integer.parseInt(mon_f.getText().toString()) > 23)
                validInput = false;
            else if (Integer.parseInt(mon_t.getText().toString()) > 23 || Integer.parseInt(mon_f.getText().toString())>= Integer.parseInt(mon_t.getText().toString()))
                validInput = false;
            else if (Integer.parseInt(tue_f.getText().toString()) > 23)
                validInput = false;
            else if (Integer.parseInt(tue_t.getText().toString()) > 23 || Integer.parseInt(tue_f.getText().toString())>=Integer.parseInt(tue_t.getText().toString()))
                validInput = false;
            else if (Integer.parseInt(wed_f.getText().toString()) > 23)
                validInput = false;
            else if (Integer.parseInt(wed_t.getText().toString()) > 23 || Integer.parseInt(wed_f.getText().toString())>= Integer.parseInt(wed_t.getText().toString()))
                validInput = false;
            else if (Integer.parseInt(thu_f.getText().toString()) > 23)
                validInput = false;
            else if (Integer.parseInt(thu_t.getText().toString()) > 23 || Integer.parseInt(thu_f.getText().toString())>= Integer.parseInt(thu_t.getText().toString()))
                validInput = false;
            else if (Integer.parseInt(fri_f.getText().toString()) > 23)
                validInput = false;
            else if (Integer.parseInt(fri_t.getText().toString()) > 23 || Integer.parseInt(fri_f.getText().toString())>= Integer.parseInt(fri_t.getText().toString()))
                validInput = false;
            else if (Integer.parseInt(sat_f.getText().toString()) > 23)
                validInput = false;
            else if (Integer.parseInt(sat_t.getText().toString()) > 23 || Integer.parseInt(sat_f.getText().toString())>= Integer.parseInt(sat_t.getText().toString()))
                validInput = false;
            else if (Integer.parseInt(sun_f.getText().toString()) > 23)
                validInput = false;
            else if (Integer.parseInt(sun_t.getText().toString()) > 23 || Integer.parseInt(sun_f.getText().toString())>= Integer.parseInt(sun_t.getText().toString()))
                validInput = false;
        }
        catch (Exception e){
            
        }
        if(validInput)
        {
        String availability = mon_f.getText().toString()+" - "+mon_t.getText().toString()+" // "+tue_f.getText().toString()+" - "+
                tue_t.getText().toString()+" // "+wed_f.getText().toString()+" - "+wed_t.getText().toString()+" // "+
                thu_f.getText().toString() + " - "+ thu_t.getText().toString()+" // "+fri_f.getText().toString()+" - "+fri_t.getText().toString()+" // "+
                sat_f.getText().toString()+" - "+sat_t.getText().toString()+" // "+sun_f.getText().toString()+" - "+
                sun_t.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        String username = getIntent().getStringExtra("username");
        dbHandler.updateAvailability(username, availability);
        finish();
        }
        else
            Toast.makeText(this,"Invalid times",Toast.LENGTH_LONG).show();
    }
}
