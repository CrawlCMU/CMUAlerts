package com.application.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.application.adapters.FeedArrayAdapter;
import com.application.managesubscriptions.FBSubscribeActivity;
import com.example.crawlcmu.R;

public class FBFeedActivity extends ListActivity 
{
	private static final String[] FEEDSOURCE = new String[] { "IGSA", "IGSA","IGSA","FB","FB","FB"};


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new FeedArrayAdapter(this, FEEDSOURCE));
	}

	
	@Override
	//TODO: This will have to take the user to the post with that ID
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_fb_feed, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		Intent i = new Intent(this,FBSubscribeActivity.class);
	    //respond to menu item selection
		switch (item.getItemId()) 
		{
	    	case R.id.subscribe_button:
	    		i.putExtra("subscription", "subscribed");
	    		startActivity(i);
	    		return true;
	    	case R.id.unsubscribe_button:
	    		i = new Intent(this,FBSubscribeActivity.class);
	    		i.putExtra("subscription", "unsubscribed");
	    		startActivity(i);
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
		}
	}

}