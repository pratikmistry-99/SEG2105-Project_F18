package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class availiability extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availiability);
    }

    public void saveBtn(View view){
        //String getIntent().getStringExtra("role");
        //String username = intent.putExtra("username", user.getUsername());
        //String company = intent.putExtra("company", user.getUsername());
        //String intent.putExtra("address", user.getUsername());
        //intent.putExtra("description", user.getUsername());
        //intent.putExtra("license", user.getUsername());
        finish();
    }

}
