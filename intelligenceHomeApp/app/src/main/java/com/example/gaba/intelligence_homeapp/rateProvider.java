package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class rateProvider extends AppCompatActivity {

    String username;
    String serviceName;
    SeekBar rating;
    EditText comment;

    int review_rating;
    String review_comment;

    @Override
    // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_provider);

        username = getIntent().getStringExtra("username");
        serviceName = getIntent().getStringExtra("serviceName");
        rating = findViewById(R.id.seekBar);
        comment = findViewById(R.id.comment);

    }

    public void addReview(View view){
        review_rating = rating.getProgress();
        review_comment = comment.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addReview(username,review_rating,review_comment);
        Toast.makeText(getApplicationContext(),"Submitted. Thank you!",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), ViewBookRate_ServiceProvider.class);
        intent.putExtra("username", username);
        intent.putExtra("serviceName", serviceName);
        intent.putExtra("accountOwner", getIntent().getStringExtra("accountOwner"));
        finish();
        startActivityForResult(intent, 0);

    }
}
