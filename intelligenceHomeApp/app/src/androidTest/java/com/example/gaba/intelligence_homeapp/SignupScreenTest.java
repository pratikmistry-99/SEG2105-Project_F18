package com.example.gaba.intelligence_homeapp;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;


public class SignupScreenTest {

    @Rule
    public ActivityTestRule<SignupScreen>sScreenTestRule = new ActivityTestRule<SignupScreen>(SignupScreen.class);
    private SignupScreen sScreen = null;
    private TextView text;
    private RadioButton homeOwner;
    private RadioGroup rGroup;

    @Before
    public void setUp() {
        sScreen = sScreenTestRule.getActivity();

    }

    @Test
    @UiThreadTest
    public void checkUserLogin() throws Exception{
        assertNotNull(sScreen.findViewById(R.id.addUser));
        text = sScreen.findViewById(R.id.addUser);
        text.setText("user1");
        String name = text.getText().toString();
        assertNotEquals("user", name);
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception{
        assertNotNull(sScreen.findViewById(R.id.addPassword));
        text = sScreen.findViewById(R.id.addPassword);
        text.setText("password1");
        String pass = text.getText().toString();
        assertNotEquals("password", pass);
    }

    @Test
    @UiThreadTest
    public void checkUserTypeSelection() throws Exception{
        assertNotNull(sScreen.findViewById(R.id.radioGroup));
        rGroup = sScreen.findViewById(R.id.radioGroup);
        homeOwner = sScreen.findViewById(R.id.rb_HO);
        homeOwner.setChecked(true);
        assertNotEquals(rGroup.getCheckedRadioButtonId(), -1);
    }
}
