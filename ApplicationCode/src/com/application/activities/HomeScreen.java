package com.application.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.application.DBLayout.MySQLiteHelper;
import com.application.FileIO.Helper;
import com.example.crawlcmu.R;
import com.rssreader.rssdataitem.RssReaderActivity;
import com.shuttletimings.RouteListActivity;

public class HomeScreen extends Activity 
{
	private ImageButton cmuFeedButton;
	private ImageButton twitterFeedButton;
	private ImageButton alertFeedButton;
	private ImageButton fbFeedButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		addListenerOnButton();
		MySQLiteHelper sqlHelper = new MySQLiteHelper(getApplicationContext());
		sqlHelper.initializeDB();
		Helper.writeFromFileToDB("MyFile.txt", getApplicationContext());
	}

	private void addListenerOnButton() 
	{
		cmuFeedButton = (ImageButton)findViewById(R.id.CMUFeedButton);
		fbFeedButton  = (ImageButton)findViewById(R.id.FBFeedButton);
		alertFeedButton  = (ImageButton)findViewById(R.id.AlertFeedButton);
		twitterFeedButton  = (ImageButton)findViewById(R.id.TwitterFeedButton);
		
		cmuFeedButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(getApplicationContext(), RssReaderActivity.class);
				startActivity(i);
			}
		});
		
		fbFeedButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent loginIntent = new Intent(getBaseContext(),FBLoginActivity.class);
				startActivity(loginIntent);
				
//				Toast.makeText(HomeScreen.this,"FBFeed is clicked!", Toast.LENGTH_SHORT).show();
//				Intent fbIntent = new Intent(getBaseContext(),FBFeedActivity.class);
//				startActivity(fbIntent);
			}
		});
		
		alertFeedButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				
				Intent i = new Intent(getApplicationContext(), RouteListActivity.class);
				startActivity(i);
			}
		});
		
		
		twitterFeedButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent twitterIntent = new Intent(getBaseContext(),MainActivity.class);
				startActivity(twitterIntent);
				
				Toast.makeText(HomeScreen.this,"twitterFeedButton is clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.action_settings:
	        Intent i = new Intent(this, HelpActivity.class);
	        startActivity(i);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

}
