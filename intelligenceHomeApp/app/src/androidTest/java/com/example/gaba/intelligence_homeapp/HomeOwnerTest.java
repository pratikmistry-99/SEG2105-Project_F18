package com.example.gaba.intelligence_homeapp;

import android.content.Intent;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class HomeOwnerTest {

    @Rule
    public ActivityTestRule<WelcomeScreen> hOwnerTestRule = new ActivityTestRule<WelcomeScreen>(WelcomeScreen.class)
    {
//        @Override
//        protected void beforeActivityLaunched() {}

        @Override
        protected Intent getActivityIntent()
        {
            Intent intent = new Intent();
            intent.putExtra("Name","Billy");
            intent.putExtra("username","Billy");
            intent.putExtra("role","Home Owner");
            return intent;
        }

    };
    private MyDBHandler database;

    @BeforeClass
    // public static void setUpBefore(){
    //Create Service Lists with at least 2 service Providers
    //Service Provider with company title: "Bug Watcher"
    // service 1: Bug Watching with availability set to Wednesdays
    // 11 to 16 hrs
    // }

    @Before
    public void setUp(){
       WelcomeScreen hOwner = hOwnerTestRule.getActivity();
       database = new MyDBHandler(hOwner);

    }

    @AfterClass
    public static void cleanUpAfter(){
       //Clear all tables
//        MyDBHandler database = new MyDBHandler();
//        database.clearAllTables();
    }

    @Test
    @UiThreadTest
    public void checkServiceListAccess(){
        assertEquals(1,1);
    }
    @Test
    @UiThreadTest
    public void searchByType(){
        assertEquals(1,1);
    }
    @Test
    @UiThreadTest
    public void searchByRating(){
        assertEquals(1,1);
    }
    @Test
    @UiThreadTest
    public void searchByTiming(){
        assertEquals(1,1);
    }
    @Test
    @UiThreadTest
    public void checkBookingFeature(){
        assertEquals(1,1);
        //Simulate access a service provider ("Bug Watcher")
        // and their Availability page
        // where Wednesdays time slot is set to 12 to 13 hrs
        //Select that TimeSlot

    }

    @Test
    @UiThreadTest
    public void checkRateFeature(){
        assertEquals(1,1);
        //call checkBookingFeature so a service gets Booked (if not deleted)
        //Then rate that service and validate rating as the services only rating
    }
    @Test
    @UiThreadTest
    public void validateNumericalInput(){
        //Test if correct inputs set to search engine
        // assertNotNull(welcomeScreen.findViewById(R.id.searchEngine);
    }
    @Test
    @UiThreadTest
    public void checkServiceProvidersList(){}

}
