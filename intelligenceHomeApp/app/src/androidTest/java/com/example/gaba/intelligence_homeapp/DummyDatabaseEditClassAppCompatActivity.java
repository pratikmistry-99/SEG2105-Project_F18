package com.example.gaba.intelligence_homeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DummyDatabaseEditClassAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addUser(new User("Billy","password","Service Provider"));
    }
}