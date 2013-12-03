package com.application.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.application.adapters.FeedArrayAdapter;
import com.application.managesubscriptions.FBSubscribeActivity;
import com.crawlcmu.entities.FBFeed;
import com.example.crawlcmu.R;

public class FBFeedActivity extends ListActivity 
{
	private static final String TAG = "FBFeedActivity";
	private static final String URL = "https://www.facebook.com";
	
	private ArrayList<FBFeed> feeds;
	private HashMap<String, ArrayList<String>> crawlFeeds = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> crawlFeedsURLs = new HashMap<String, ArrayList<String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Intent showFeedsIntent = getIntent();
		crawlFeeds.clear();
		crawlFeedsURLs.clear();
		crawlFeeds = (HashMap<String, ArrayList<String>>)showFeedsIntent.getSerializableExtra("FeedsMap");
		crawlFeedsURLs = (HashMap<String, ArrayList<String>>)showFeedsIntent.getSerializableExtra("URLMap");
		Log.v("HashMapTest", crawlFeeds.toString());
		Log.v("URLMapTest", crawlFeedsURLs.toString());
	  
	    feeds = new ArrayList<FBFeed>();
	    
	    populateFeedData();
		
		setListAdapter(new FeedArrayAdapter(this, feeds));
	}

	
	private void populateFeedData() 
	{
		ArrayList<String> URLs = new ArrayList<String>();
		for (Map.Entry<String, ArrayList<String>> entry : crawlFeeds.entrySet()) 
		{
			Log.d(TAG,"Key : " + entry.getKey() + " Value : "+ entry.getValue());
			
			URLs = crawlFeedsURLs.get(entry.getKey());
			int i = 0;
			for(String str:entry.getValue())
			{
				FBFeed feed = new FBFeed(str, entry.getKey(), URLs.get(i++));
				feeds.add(feed);
			}
		}
	}


	@Override
	//TODO: This will have to take the user to the post with that ID
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		//String selectedValue = (String) getListAdapter().getItem(position);
		FBFeed feedSelected = (FBFeed) getListAdapter().getItem(position);
		Toast.makeText(this, feedSelected.getURL(), Toast.LENGTH_SHORT).show();
//		Intent i = new Intent(Intent.ACTION_VIEW);
//		i.setData(Uri.parse(feedSelected.getURL()));
//		startActivity(i);
		shareTextUrl(feedSelected.getURL());
	}
	
	/*
     * Method to share either text or URL.
     */
    private void shareTextUrl(String url) {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, url);

        startActivity(Intent.createChooser(share, "Share link!"));
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
