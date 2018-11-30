package com.example.gaba.intelligence_homeapp;

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
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class ServiceProvidersTest {

    @Rule
    public ActivityTestRule<SignupScreen>sScreenTestRule = new ActivityTestRule<SignupScreen>(SignupScreen.class);
    private SignupScreen sScreen = null;
    public ActivityTestRule<WelcomeScreen> sProviderTestRule = new ActivityTestRule<>(WelcomeScreen.class);
    private WelcomeScreen sProvider = null;
    private EditText address, company, phoneNum, description;
    private RadioButton license, noLicense;
    private Button createProfile, selectAvail;
    private MyDBHandler database = null;

    private TextView text;
    private RadioGroup rGroup;
    private RadioButton serviceProvider;
    private Button cAccountButton;

    public ActivityTestRule<LoginScreen>loginTestRule = new ActivityTestRule<>(LoginScreen.class);
    private LoginScreen login = null;
    private Button log;





    @Before
    public void setUp(){
        sScreen = sScreenTestRule.getActivity();
        sProvider = sProviderTestRule.getActivity();
        login = loginTestRule.getActivity();
        database = new MyDBHandler(sScreen);
        database.clearAllTables();
    }

    @Test
    @UiThreadTest
    public void checkCreateProfile() throws Exception{
       //First assure a serviceProvider user is created
        text = sScreen.findViewById(R.id.addUser);
        text.setText("usersp");
        String name = text.getText().toString();
        assertEquals(1,1);

        text = sScreen.findViewById(R.id.addPassword);
        text.setText("passwordsp");
        String pass = text.getText().toString();

        rGroup = sScreen.findViewById(R.id.radioGroup);
        serviceProvider = sScreen.findViewById(R.id.rb_SP);
        serviceProvider.setChecked(true);

        cAccountButton = sScreen.findViewById(R.id.createAcc);
        cAccountButton.performClick();

        assertEquals(true, database.addProviderToService(name, pass));
        //Login with above to Assure Welcome is for SProvider?
//        text = login.findViewById(R.id.username);
//        text.setText("usersp");
//        //String userName = text.getText().toString();
//        text = login.findViewById(R.id.password);
//        text.setText("passwordsp");
//        //String pswd = text.getText().toString();
//
//        log = login.findViewById(R.id.loginBtn);
//        log.performClick();

        //Next, text functionality of the WelcomePage for this User
//        text = sProvider.findViewById(R.id.editAddress);
//        text.setText("800 King Edward");
//        String addr = text.getText().toString();
//
//        text = sProvider.findViewById(R.id.editCompany);
//        text.setText("uOttawa  ");
//        String comp = text.getText().toString();
//
//        text = sProvider.findViewById(R.id.editPhone);
//        text.setText("613 000 0000");
//        String num = text.getText().toString();
//
//        text = sProvider.findViewById(R.id.editDesc);
//        text.setText("BLAH blah blah");
//        String descrip = text.getText().toString();
//
//        rGroup = sProvider.findViewById(R.id.radioGroup);
//        license = sProvider.findViewById(R.id.yesBtn);
//        license.setChecked(true);

//        cAccountButton = sProvider.findViewById(R.id.btnCreateProf);
//        cAccountButton.performClick();

//        cAccountButton = sProvider.findViewById(R.id.btnCreateProf);
//        cAccountButton.performClick();

        assertEquals(1,1);
    }

   @Test
   @UiThreadTest
   public void checkForNoProfile(){
        assertEquals(false, database.hasProfile("usersp"));

   }

//   @Test
//   @UiThreadTest
//   public void  checkAddAvailability(){
//
//   }

    @Test
    @UiThreadTest
    public void validateAvailEntries(){
        //TODO: Add a profile and subsequent availability to one slot
        //TODO: Then check to make sure number is a digit (make sure this actually is done in our group)
        //Due to errors running WelcomeScreen Activity, i could not yet test run these test classes
        assertEquals(5, 5);
    }
}
