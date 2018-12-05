package com.example.gaba.intelligence_homeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookingsList extends AppCompatActivity {

    ArrayList<Booking> bookingsList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<Service> adapter;
    MyDBHandler dbHandler;
    String username;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_list);
        listView = findViewById(R.id.bookings);
        //For each item in database, add to serviceList
        dbHandler = new MyDBHandler(this);
        role = getIntent().getStringExtra("role");
        username = getIntent().getStringExtra("username");

        if (role.equals("Service Provider")){
            bookingsList =  dbHandler.getAllBookings(username);
        }
        else{
            bookingsList = dbHandler.getAllBookings_homeOwner(username);
        }
        System.out.println(bookingsList.size());
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bookingsList);
        listView.setAdapter(adapter);

    }
}
