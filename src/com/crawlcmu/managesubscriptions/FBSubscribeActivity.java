package com.crawlcmu.managesubscriptions;

import com.example.crawlcmu.R;
import com.example.crawlcmu.R.layout;
import com.example.crawlcmu.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FBSubscribeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_subscriptions);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_subscriptions, menu);
		return true;
	}

}