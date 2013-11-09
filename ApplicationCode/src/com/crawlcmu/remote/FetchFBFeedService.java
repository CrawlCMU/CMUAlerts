/**
 * 
 */
package com.crawlcmu.remote;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.crawlcmu.entities.FeedModel;

/**
 * TODO: This class will take care of how to fetch Facebook feeds
 * It implements the feed input interface, which defines a common contract  
 * Any feed class that implements FeedInput has 
 * @author ishan
 *
 */
public class FetchFBFeedService extends Service implements FeedInput 
{

	/**
	 * Any feed class that implements FeedInput has to implement this method
	 * This method will get the newest feeds from the URL and store them in the ArrayList<Feeds>
	 */
	public void getFeeds(String URL, ArrayList<FeedModel> feeds) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Any feed class that implements FeedInput has to implement this method
	 * This method will update the newest feeds from the URL
	 */
	public void updateFeeds(String URL, ArrayList<FeedModel> feeds) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method will check if a given URL has any updates
	 */
	public boolean checkUpdates(String URL) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * This method will go through the list of feeds and invalidate those which 
	 * are out of date
	 */
	public void invalidateFeed(ArrayList<FeedModel> feeds) 
	{
		
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
