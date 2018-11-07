package com.example.gaba.intelligence_homeapp;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;

public class ServiceList extends AppCompatActivity {

    MyDBHandler services = new MyDBHandler(this);
    LinkedList<Service> serviceList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
    }



}
