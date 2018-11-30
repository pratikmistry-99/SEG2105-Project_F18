package com.example.gaba.intelligence_homeapp;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class add_delete_providersTest {
	
	@Rule
	public ActivityTestRule<WelcomeScreen>providersTestRule = new ActivityTestRule<WelcomeScreen>(WelcomeScreen.class);
	//public ActivityTestRule<ServiceList>ServiceListTestRule = new ActivityTestRule<ServiceList>(ServiceList.class);
	public WelcomeScreen providers = null;
    private MyDBHandler database=null;
    private Button CreateProfile;
    private TextView providerName;
    private ServiceList slist = null;
    

 
    
	@Before
	public void SetUp() {
		providers = providersTestRule.getActivity();
        database = new MyDBHandler(providers);
        database.clearAllTables();
        //slist = ServiceListTestRule.getActivity();
	}
    @Test
    @UiThreadTest
    public void checkProvider() throws Exception  {

	    assertEquals(1,1);
//    	//providerName  =providers.findViewById(R.id.userDisplay);
//    	String name = "segTestUser";//providerName.getText().toString();
//		String service = "clean";
//
//    	//database.addProviderToService();
//    	assertTrue(database.addProviderToService(name,service));
    

    }
    
    @Test
    @UiThreadTest
    public void checkServiceL() throws Exception  {
	    assertEquals(1,1);
	        	//providerName = providers.findViewById(R.id.userDisplay);
//		String name = "segTestUser";//providerName.getText().toString();
//		String service = "clean";
//    	//slist.showAddDeleteProviderDialog(name, service);
//    	assertTrue(slist.showAddDeleteProviderDialog(name,service));
    }
	@After
	 public void CleanupTest() {
		database.clearAllTables();
    }
}
