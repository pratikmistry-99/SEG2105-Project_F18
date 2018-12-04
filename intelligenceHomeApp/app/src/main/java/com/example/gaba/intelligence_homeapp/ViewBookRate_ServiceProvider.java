package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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

    String[] availabilities;
    
    @Override
    // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book_rate_service_providers);

        serviceName = getIntent().getStringExtra("serviceName");
        username = getIntent().getStringExtra("username");
        MyDBHandler dbHandler = new MyDBHandler(this);
        ((TextView)findViewById(R.id.profileInfo)).setText("Name: "+username+dbHandler.getServiceProviderInfo(username));


        bookBtn = findViewById(R.id.btnBook);
        rateBtn = findViewById(R.id.btnRate);

        mon_rb = findViewById(R.id.monAv);
        tue_rb = findViewById(R.id.tueAv);
        wed_rb = findViewById(R.id.wedAv);
        thu_rb = findViewById(R.id.thuAv);
        fri_rb = findViewById(R.id.friAv);
        sat_rb = findViewById(R.id.satAv);
        sun_rb = findViewById(R.id.sunAv);



        String av = dbHandler.getAvailabilities(username);


        if(mon_rb.getText().toString().trim().equals("-"))
            mon_rb.setVisibility(View.GONE);

        if(tue_rb.getText().toString().trim().equals("-"))
            tue_rb.setVisibility(View.GONE);

        if(wed_rb.getText().toString().trim().equals("-"))
            wed_rb.setVisibility(View.GONE);

        if(thu_rb.getText().toString().trim().equals("-"))
            thu_rb.setVisibility(View.GONE);

        if(fri_rb.getText().toString().trim().equals("-"))
            fri_rb.setVisibility(View.GONE);

        if(sat_rb.getText().toString().trim().equals("-"))
            sat_rb.setVisibility(View.GONE);

        if(sun_rb.getText().toString().trim().equals("-"))
            sun_rb.setVisibility(View.GONE);



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
        else{
            ((TextView) findViewById(R.id.profileInfo)).setText(((TextView) findViewById(R.id.profileInfo)).getText().toString() + "\n ** Not specified availiability **");
            findViewById(R.id.timePicker).setVisibility(View.GONE);
        }
    }

    public void book(View view){
        MyDBHandler dbHandler = new MyDBHandler(getApplicationContext());
        String time = "";
        if (mon_rb.isChecked())
            time = "Monday // " + availabilities[0].trim();
        else if (tue_rb.isChecked())
            time = "Tuesday // " + availabilities[1].trim();
        else if (wed_rb.isChecked())
            time = "Wednesday // " + availabilities[2].trim();
        else if (thu_rb.isChecked())
            time = "Thursday // " + availabilities[3].trim();
        else if (fri_rb.isChecked())
            time = "Friday // " + availabilities[4].trim();
        else if (sat_rb.isChecked())
            time = "Saturday // " + availabilities[5].trim();
        else if (sun_rb.isChecked())
            time = "Sunday // " + availabilities[6].trim();

        dbHandler.addBooking(getIntent().getStringExtra("accountOwner"),username, time);
        Toast.makeText(getApplicationContext(), "Service Booked!",Toast.LENGTH_SHORT).show();
    }

    public void rate(View view){
        Intent intent = new Intent(getApplicationContext(), rateProvider.class);
        intent.putExtra("username", username);
        startActivityForResult(intent, 0);
    }

}
