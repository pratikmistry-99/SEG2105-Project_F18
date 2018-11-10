package com.example.gaba.intelligence_homeapp;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.*;

public class ServiceList extends AppCompatActivity {


    final ArrayList<Service> serviceList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        // Get ListView object from xml layout
        listView = findViewById(R.id.serv);
        //TODO: create service database
        //For each item in database, add to serviceList

        //Create an ArrayAdapter and Set it on the ListView
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, serviceList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string
            }
        });
    }

    public void addService(Service service){
        serviceList.add(service);
    }




}
