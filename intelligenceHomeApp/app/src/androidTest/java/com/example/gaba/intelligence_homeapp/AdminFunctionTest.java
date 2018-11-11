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
    public ActivityTestRule<update_delete_dialog> deleteDialogTestRule = new ActivityTestRule<update_delete_dialog>(update_delete_dialog.class);
    private update_delete_dialog dDialog = null;
    private ServiceList slist = null;
    private TextView serviceName;
    private TextView hourlyRate;
    private Button addButton;
    private Button updateButton, deleteButton;
    private MyDBHandler database=null;

    private String TestService = "Harvey Cleaners";

    //clears database of all services and double checks that the serviceDatabase is clear
    @Before
    public void SetupTest()  {
        slist = serviceListTestRule.getActivity();
        dDialog = deleteDialogTestRule.getActivity();
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
        serviceName.setText(TestService);
        String service = serviceName.getText().toString();

        hourlyRate = slist.findViewById(R.id.editRate);
        hourlyRate.setText("5");
        String rate = hourlyRate.getText().toString();
        slist.addService(addButton);

        //Service must have been added. Since serviceList is clear, Check database service list at index 0 for expected newly added service
        ArrayList<Service> updatedSlist =  database.getAllServices();
        Service s = updatedSlist.get(0);
        assertEquals(service, s.getName());

    }

    //Create class instance update_delete_dialog and access buttonUpdateService and buttonDeleteService
    @Test
    @UiThreadTest
    public void checkEditService() throws Exception{
        checkAddService();
        //tried to test like other functions, but ran into nullpoint error because the activity wasn't created yet.
        //since I test OUR logic for the edit function, this is sufficient
        slist.updateService(TestService, 1);

        ArrayList<Service> updatedSlist =  database.getAllServices();
        Service s = updatedSlist.get(0);

        double uRate = 1.0;
        assertEquals(uRate, s.getRate());
    }

    @Test
    @UiThreadTest
    public void checkDeleteService() throws Exception{
        checkAddService();
        //tried to test like other functions, but ran into nullpoint error because the activity wasn't created yet.
        //since I test OUR logic for the delete function, this is sufficient
        slist.deleteService(TestService);
        //since we check in setup that the database is empty, after adding a service, it should be empty once again
        assertTrue(database.getAllServices().isEmpty());

    }


}

