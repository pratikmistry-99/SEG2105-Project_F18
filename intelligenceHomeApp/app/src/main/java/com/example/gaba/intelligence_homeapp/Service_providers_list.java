package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Service_providers_list extends AppCompatActivity{
    ArrayList<User> serviceProvidersList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<User> adapter;
    MyDBHandler dbHandler;

    Button btnPickTimes;
    String serviceName;
    String username;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_SHORT).show();
                username = ((TextView) view).getText().toString();

                Intent intent = new Intent(getApplicationContext(), ViewBookRate_ServiceProvider.class);
                intent.putExtra("serviceName", serviceName);
                intent.putExtra("username", username);
                startActivityForResult(intent, 0);

            }
        });

    }

    public void viewProvider(View view){
        Intent intent = new Intent(getApplicationContext(), ViewBookRate_ServiceProvider.class);
        intent.putExtra("serviceName", serviceName);
        startActivityForResult(intent, 0);
    }

    public void viewRating(View view){
        showRatingSelectDialog();
    }

    private void showRatingSelectDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_rating_select_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button btnSearch = (Button) dialogView.findViewById(R.id.btnSearch);
        final EditText editRating = (EditText) dialogView.findViewById(R.id.editRating);

        dialogBuilder.setTitle("Rating Search");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Need to make a method that makes a list based on the given rating and displays it
                b.dismiss();
            }

        });
    }


    }
