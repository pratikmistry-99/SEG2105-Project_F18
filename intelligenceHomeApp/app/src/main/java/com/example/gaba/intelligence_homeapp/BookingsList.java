package com.example.gaba.intelligence_homeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookingsList extends AppCompatActivity {

    ArrayList<Service> bookingsList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<Service> adapter;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_list);
        listView = findViewById(R.id.bookings);
        //For each item in database, add to serviceList
        dbHandler = new MyDBHandler(this);
        //getIntent
        //bookingsList =  (dbHandler.getAllBookings();
        //Create an ArrayAdapter and Set it on the BookingsList
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bookingsList);
        listView.setAdapter(adapter);


    }
}
