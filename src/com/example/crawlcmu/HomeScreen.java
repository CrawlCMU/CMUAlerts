package com.example.crawlcmu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

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
				Toast.makeText(HomeScreen.this,"CMUFeed is clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		
		fbFeedButton.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				
				Toast.makeText(HomeScreen.this,"FBFeed is clicked!", Toast.LENGTH_SHORT).show();
				Intent fbIntent = new Intent(getBaseContext(),FBFeedActivity.class);
				startActivity(fbIntent);
			}
		});
		
		alertFeedButton.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				
				Toast.makeText(HomeScreen.this,"alertFeedButton is clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		twitterFeedButton.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				
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

}
