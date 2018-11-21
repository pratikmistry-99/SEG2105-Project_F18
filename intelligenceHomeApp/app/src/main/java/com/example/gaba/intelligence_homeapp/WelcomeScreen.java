package com.example.gaba.intelligence_homeapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

// class used to create the welcome screen once you log in
public class WelcomeScreen extends AppCompatActivity {

    TextView userNameDisplay;
    TextView userRoleDisplay;
    TextView adminRoleDisplay;
    String str;
    User user;
    String role;




    @Override
    // onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        userRoleDisplay = findViewById(R.id.roleTxt);
        userNameDisplay = findViewById(R.id.userDisplay);
        adminRoleDisplay = findViewById(R.id.adminDisplay);

        MyDBHandler dbHandler = new MyDBHandler(this);
        user = dbHandler.findUser(getIntent().getStringExtra("Name"));
        str = "Welcome "+user.getUsername()+"!";
        userNameDisplay.setText(str);
        userRoleDisplay.setText(user.getRole() + " Account");
        role = user.getRole();
        String ad = "";
        if (user.getRole().equals("Administrator")){
            ad = dbHandler.getAllUsernames();
            adminRoleDisplay.setText(ad);

        }
        else
            adminRoleDisplay.setText("");
        findViewById(R.id.servList).setVisibility(View.VISIBLE);
        boolean a = dbHandler.hasProfile(user.getUsername());
        if(user.getRole().equals("Service Provider") && !a){
            findViewById(R.id.yesBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.noBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.editAddress).setVisibility(View.VISIBLE);
            findViewById(R.id.editDesc).setVisibility(View.VISIBLE);
            findViewById(R.id.editCompany).setVisibility(View.VISIBLE);
            findViewById(R.id.txtLicensed).setVisibility(View.VISIBLE);
            findViewById(R.id.txtProfile).setVisibility(View.VISIBLE);
            findViewById(R.id.btnAvail).setVisibility(View.VISIBLE);
            findViewById(R.id.rg).setVisibility(View.VISIBLE);
        }
        public void avail(View view) {
            Intent intent = new Intent(getApplicationContext(), availiability.class);
            startActivityForResult(intent, 0);
        }

    }

    // Method to start the activity
    public void sList(View view){

        Intent intent = new Intent(getApplicationContext(),ServiceList.class);
        intent.putExtra("role", role);
        intent.putExtra("username", user.getUsername());
        startActivityForResult(intent,0);
    }
    /*
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        dayF = day;

        Calendar c = Calendar.getInstance();
        hour1 = c.get(Calendar.HOUR_OF_DAY);
        minute1 = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(WelcomeScreen.this,WelcomeScreen.this,
                hour1,minute1, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

       //TimePickerDialog timePickerDialog1 = new TimePickerDialog(WelcomeScreen.this,WelcomeScreen.this,
       //         hour2,minute2, DateFormat.is24HourFormat(this));
       //timePickerDialog1.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour1F = hourOfDay;
        minute1F = minute;
    }
    */
}
