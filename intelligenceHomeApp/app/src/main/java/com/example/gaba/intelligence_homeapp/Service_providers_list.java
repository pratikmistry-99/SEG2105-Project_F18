package com.example.gaba.intelligence_homeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Service_providers_list extends AppCompatActivity{
    ArrayList<User> serviceProvidersList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<User> adapter;
    MyDBHandler dbHandler;

    Button btnPickTimes;
    String serviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_providers_list);
        serviceName = getIntent().getStringExtra("serviceName");
        btnPickTimes = (Button) findViewById(R.id.pickTimes);
        // Get ListView object from xml layout
        listView = findViewById(R.id.servProv);
        //For each item in database, add to serviceList
        dbHandler = new MyDBHandler(this);
        serviceProvidersList =  (dbHandler.getServiceProviders(serviceName));
        //Create an ArrayAdapter and Set it on the ListView
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, serviceProvidersList);
        listView.setAdapter(adapter);
        btnPickTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime();
            }
        });

    }

    public void selectTime(){

    }

}
