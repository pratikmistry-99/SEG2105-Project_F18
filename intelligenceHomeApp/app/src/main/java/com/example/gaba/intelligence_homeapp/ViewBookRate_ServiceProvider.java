package com.example.gaba.intelligence_homeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ViewBookRate_ServiceProvider extends AppCompatActivity{
    String serviceName;
    String username;

    RadioButton mon_rb;
    RadioButton tue_rb;
    RadioButton wed_rb;
    RadioButton thu_rb;
    RadioButton fri_rb;
    RadioButton sat_rb;
    RadioButton sun_rb;

    Button bookBtn;
    Button rateBtn;
    
    @Override
    // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book_rate_service_providers);

        serviceName = getIntent().getStringExtra("serviceName");
        username = getIntent().getStringExtra("username");

        bookBtn = findViewById(R.id.btnBook);
        rateBtn = findViewById(R.id.btnRate);

        mon_rb = findViewById(R.id.monAv);
        tue_rb = findViewById(R.id.tueAv);
        wed_rb = findViewById(R.id.wedAv);
        thu_rb = findViewById(R.id.thuAv);
        fri_rb = findViewById(R.id.friAv);
        sat_rb = findViewById(R.id.satAv);
        sun_rb = findViewById(R.id.sunAv);

        String[] availabilities;
        MyDBHandler dbHandler = new MyDBHandler(this);
        String av = dbHandler.getAvailabilities(username);

        if (av != null) {
            availabilities = av.split("//");
            if (availabilities.length == 7) {
                mon_rb.setText("Monday: "+availabilities[0].trim());
                tue_rb.setText("Tuesday: "+availabilities[1].trim());
                wed_rb.setText("Wednesday: "+availabilities[2].trim());
                thu_rb.setText("Thursday: "+availabilities[3].trim());
                fri_rb.setText("Friday: "+availabilities[4].trim());
                sat_rb.setText("Saturday: "+ availabilities[5].trim());
                sun_rb.setText("Sunday: "+availabilities[6].trim());

            }
        }


    }

    public void book(View view){

    }

    public void rate(View view){

    }

}
