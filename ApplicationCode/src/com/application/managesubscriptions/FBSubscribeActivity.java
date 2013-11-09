package com.application.managesubscriptions;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.application.adapters.SubscriptionCheckboxListAdapter;
import com.crawlcmu.entities.CheckBoxDataModel;
import com.example.crawlcmu.R;

public class FBSubscribeActivity extends ListActivity 
{
	private List dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_subscriptions);
		initializeSubscriptionList();
		SubscriptionCheckboxListAdapter adapter = new SubscriptionCheckboxListAdapter(getLayoutInflater(),dataList);
 		getListView().setAdapter(adapter);
 		checkButtonClick();
	}

	// TODO: This function will actually query the preference DB
	// And initialize the list according to the ones that the user has subscribed to.
	private void initializeSubscriptionList() 
	{
		dataList = new ArrayList();
 		dataList.add(new CheckBoxDataModel("IGSA"));
		dataList.add(new CheckBoxDataModel("CMU School of Computer Science"));
		dataList.add(new CheckBoxDataModel("ECE masters advisory council"));
		dataList.add(new CheckBoxDataModel("CMU office of International Education"));
	}

	private void checkButtonClick() 
	{
		Button myButton = (Button) findViewById(R.id.findSelected);
		myButton.setOnClickListener(new OnClickListener() 
		{

				@Override
				public void onClick(View v) 
				{
					StringBuffer responseText = new StringBuffer();
					responseText.append("The following were selected...\n");

					for(int i=0;i<dataList.size();i++)
					{
						CheckBoxDataModel currRow = (CheckBoxDataModel) dataList.get(i);

					    if(currRow.isSelected())
					    {
					        responseText.append("\n" + currRow.getName());
					    }
					}

					Toast.makeText(getApplicationContext(),responseText, Toast.LENGTH_LONG).show();
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
