package com.example.gaba.intelligence_homeapp;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
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

public class add_delete_providersTest {
	
	@Rule
	public ActivityTestRule<add_delete_providers>add_deleteTestRule = new ActivityTestRule<add_delete_providers>(add_delete_providers.class);
    private add_delete_providers providers = null;
    private MyDBHandler database=null;
    private Button buttonAddProvider;
    private Button buttonDeleteProvider;
 
    
	@Before
	public void SetUp() {
		providers = add_deleteTestRule.getActivity();	
        database = new MyDBHandler(providers);
        database.clearAllTables();
	}
    @Test
    @UiThreadTest
    public void checkAddProvider() throws Exception  {
    	

    }
    
    @Test
    @UiThreadTest
    public void checkDeleteProvider() throws Exception  {
       

    }
	@After
	 public void CleanupTest() {
		database.clearAllTables();
    }
}
