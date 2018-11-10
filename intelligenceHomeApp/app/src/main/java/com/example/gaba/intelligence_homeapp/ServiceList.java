package com.example.gaba.intelligence_homeapp;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class ServiceList extends AppCompatActivity {

    //MyDBHandler dbHandler = new MyDBHandler(this);
    ArrayList<Service> serviceList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<Service> adapter;
    MyDBHandler dbHandler;

    EditText editService;
    EditText editRate;
    Button buttonAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        editService = (EditText) findViewById(R.id.editService);
        editRate = (EditText) findViewById(R.id.editRate);
        buttonAddService = (Button) findViewById(R.id.addButton);
        // Get ListView object from xml layout
        listView = findViewById(R.id.serv);
        //TODO: create service database
        //For each item in database, add to serviceList
        dbHandler = new MyDBHandler(this);
        serviceList =  (dbHandler.getAllServices());
        //Create an ArrayAdapter and Set it on the ListView
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, serviceList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                //Do something with the string
            }
        });
    }

    private boolean deleteService(String id){
        return true;
    }

    private void updateService(String id, String service, double rate){

    }

    public void addService(View view){
        String name = editService.getText().toString().trim();
        double rate = Double.parseDouble(String.valueOf(editRate.getText().toString()));
        if(!TextUtils.isEmpty(name)){
            Toast.makeText(this,"Product added",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Please enter a name",Toast.LENGTH_LONG).show();
        }
        Service service1 = new Service(name,rate);
        //serviceList.add(service1);
        //TODO: Add the service to the database
        dbHandler.addService(service1);
        //dbHandler.clearAllTables();
        serviceList = dbHandler.getAllServices();
        //finish();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, serviceList);
        listView.setAdapter(adapter);


        editRate.setText("");
        editService.setText("");
        //Intent intent = new Intent(getApplicationContext(),ServiceList.class);
        //startActivityForResult(intent,0);
    }




}
