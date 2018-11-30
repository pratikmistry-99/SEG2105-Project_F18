package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                mon_f.setText(availabilities[0].trim().split("-")[0].trim());
                tue_f.setText(availabilities[1].trim().split("-")[0].trim());
                wed_f.setText(availabilities[2].trim().split("-")[0].trim());
                thu_f.setText(availabilities[3].trim().split("-")[0].trim());
                fri_f.setText(availabilities[4].trim().split("-")[0].trim());
                sat_f.setText(availabilities[5].trim().split("-")[0].trim());
                sun_f.setText(availabilities[6].trim().split("-")[0].trim());

                mon_t.setText(availabilities[0].trim().split("-")[1].trim());
                tue_t.setText(availabilities[1].trim().split("-")[1].trim());
                wed_t.setText(availabilities[2].trim().split("-")[1].trim());
                thu_t.setText(availabilities[3].trim().split("-")[1].trim());
                fri_t.setText(availabilities[4].trim().split("-")[1].trim());
                sat_t.setText(availabilities[5].trim().split("-")[1].trim());
                sun_t.setText(availabilities[6].trim().split("-")[1].trim());

            }
        }

    }

    public void saveBtn(View view){
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

}
