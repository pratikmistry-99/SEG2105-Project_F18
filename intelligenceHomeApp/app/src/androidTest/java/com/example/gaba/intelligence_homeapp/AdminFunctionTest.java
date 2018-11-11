package com.example.gaba.intelligence_homeapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AdminFunctionTest {

    @Rule
    public ActivityTestRule<ServiceList> serviceListTestRule = new ActivityTestRule<ServiceList>(ServiceList.class);
    private ServiceList slist = null;
    private TextView serviceName;
    private TextView hourlyRate;
    private Button addButton;
    private MyDBHandler database=null;

    //clears database of all services and double checks that the serviceDatabase is clear
    @Before
    public void SetupTest()  {
        slist = serviceListTestRule.getActivity();

        database = new MyDBHandler(slist);
        database.clearServiceListTables();
        assertTrue(database.getAllServices().isEmpty());
    }

    //clears Database from what was added in test
    @After
    public void CleanupTest() {
        database.clearServiceListTables();
    }

    @Test
    @UiThreadTest
    public void checkAddService() throws Exception  {
        // TO DO: add service name, and add service hourly rate
        assertNotNull(slist.findViewById(R.id.editService));
        serviceName = slist.findViewById(R.id.editService);
        serviceName.setText("Harvey Cleaners");
        String service = serviceName.getText().toString();

        hourlyRate = slist.findViewById(R.id.editRate);
        hourlyRate.setText("5");
        String rate = hourlyRate.getText().toString();

        slist.addService(addButton);     // I want to press the button essentially and check if this service was added
//        addButton.performClick();

        //Service must have been added. Since serviceList is clear, Check database service list at index 0 for expected newly added service
        ArrayList<Service> updatedSlist =  database.getAllServices();
        Service s = updatedSlist.get(0);
        assertEquals(service, s.getName());
       // assertEquals(rate, s.getRate().toString());
        //TODO: fix this line above
        assertTrue(false);
    }

    //Create class instance update_delete_dialog and access buttonUpdateService and buttonDeleteService
//    @Test
//    public void checkEditService() throws Exception{
//        assertNotNull(slist.findViewById(R.id.editRate));
//
//
//    }
//
//    @Test
//    public void checkDeleteService() throws Exception{
//        assertNotNull(slist.findViewById(R.id.editRate));
//
//    }
}

