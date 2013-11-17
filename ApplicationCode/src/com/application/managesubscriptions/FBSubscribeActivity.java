package com.application.managesubscriptions;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.application.DBLayout.MySQLiteHelper;
import com.application.DBLayout.Preferences;
import com.application.adapters.SubscriptionCheckboxListAdapter;
import com.crawlcmu.entities.CheckBoxDataModel;
import com.example.crawlcmu.R;

public class FBSubscribeActivity extends ListActivity 
{

	private List<Preferences> listPreferences;
	private List<CheckBoxDataModel> dataList;
	boolean isSubscribe = false;
	MySQLiteHelper sqlHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_subscriptions);
		sqlHelper = new MySQLiteHelper(getApplicationContext());
		initializeSubscriptionList();
		SubscriptionCheckboxListAdapter adapter = new SubscriptionCheckboxListAdapter(getLayoutInflater(),dataList);
 		getListView().setAdapter(adapter);
 		checkButtonClick();
	}

	// TODO: This function will actually query the preference DB
	// And initialize the list according to the ones that the user has subscribed to.
	private void initializeSubscriptionList() 
	{
		List<Preferences> listPreferences;
		checkIntentforSubscription();
		dataList = new ArrayList<CheckBoxDataModel>();
		// If user wants to subscribe, get list of items where he is currently unsubscribed
		if(isSubscribe)
			listPreferences = sqlHelper.getPreferences("Facebook","unsubscribed");
		else
			listPreferences = sqlHelper.getPreferences("Facebook","subscribed");
		
		for(Preferences p : listPreferences){
			String title = p.getTitle();
			dataList.add(new CheckBoxDataModel(title));
		}
	}

	private void checkIntentforSubscription() {
		Intent i = getIntent();
		String subscriptionStatus = i.getStringExtra("subscription");
		
		if(subscriptionStatus.equals("subscribed"))
			isSubscribe = true;
		else
			isSubscribe = false;
	}

	private void checkButtonClick() 
	{
		Button myButton = (Button) findViewById(R.id.findSelected);
		myButton.setOnClickListener(new OnClickListener() 
		{

				@Override
				public void onClick(View v) 
				{
					for(int i=0;i<dataList.size();i++)
					{
						CheckBoxDataModel currRow = (CheckBoxDataModel) dataList.get(i);

					    if(currRow.isSelected())
					    {
					        if(isSubscribe)
					        	sqlHelper.setPreference(currRow.getName(), "subscribed");
					        else
					        	sqlHelper.setPreference(currRow.getName(), "unsubscribed");
					        	
					    }
					}
					finish();
				}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_subscriptions, menu);
		return true;
	}

}
