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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class ServiceProvidersTest {

    @Rule
   // public User user = new User("Bob", "password", "Service Provider");
    public ActivityTestRule<WelcomeScreen> sProviderTestRule = new ActivityTestRule<WelcomeScreen>(WelcomeScreen.class)
    {
//        @Override
//        protected void beforeActivityLaunched()
//        {
//            userSetUp();
//        }

        @Override
        protected Intent getActivityIntent()
        {
            Intent intent = new Intent();
            intent.putExtra("username","Bob");
            intent.putExtra("role","Service Provider");
            return intent;
        }

    };
    private WelcomeScreen sProvider = null;
    private MyDBHandler database = null;

    private RadioButton licensing;
    private Button createProfile, selectAvail;
    private TextView textInput, userNameDisplay, userRoleDisplay;
    private RadioGroup rGroup;

    @BeforeClass
    public static void setUpBefore(){
        User user = new User("Bob","password","Service Provider");
        MyDBHandler database = new MyDBHandler(user);
        database.addUser(user);
    }

    @Before
    public void setUp(){
        sProvider = sProviderTestRule.getActivity();
        database = new MyDBHandler(sProvider);
        //database.clearServiceListTables();
    }

    @Test
    @UiThreadTest
    public void checkProfileCreation(){
        assertNotNull(sProvider.findViewById(R.id.editAddress));
        textInput = sProvider.findViewById(R.id.editAddress);
        textInput.setText("800 King Edward");

        textInput = sProvider.findViewById(R.id.editDesc);
        textInput.setText("To assure students do not drown in their studies. We are not Insurance.");

        textInput = sProvider.findViewById(R.id.editCompany);
        textInput.setText("Student Assurance");

        textInput = sProvider.findViewById(R.id.editPhone);
        textInput.setText("613 000 0000");

        licensing = sProvider.findViewById(R.id.yesBtn);
        licensing.setChecked(true);

        createProfile = sProvider.findViewById(R.id.btnCreateProf);
        createProfile.performClick();

        User u = database.findUser("Bob");
        assertEquals("Bob", u.getUsername());
        assertEquals(true, u.getHasProfile());
    }

//    /** Checks to make sure when an account/user is deleted, their profile is gone as well*/
//   @Test
//   @UiThreadTest
//   public void checkForNoProfile(){
//        assertEquals(false, database.hasProfile("Bob"));
//   }
//
//   @Test
//   @UiThreadTest
//   public void checkAddAvailabilityButton(){
//        selectAvail = sProviders.findViewById(R.id.avail);
//        selectAvail.isClicked();
//   }
//
//    @Test
//    @UiThreadTest
//    public void validateAvailEntries(){
//        //TODO: Add availability to one slot
//        //TODO: Then check to make sure number is a digit (make sure this actually is done in our group)
//        //Due to errors running WelcomeScreen Activity, i could not yet test run these test classes
//        assertEquals(5, 5);
//    }
}
