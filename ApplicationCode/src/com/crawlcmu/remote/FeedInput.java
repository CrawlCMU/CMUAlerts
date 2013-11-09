/**
 * 
 */
package com.crawlcmu.remote;

import java.util.ArrayList;

import com.crawlcmu.entities.FeedModel;

/**
 * @author ishan
 *
 */
public interface FeedInput 
{
	public void getFeeds(String URL,ArrayList<FeedModel> feeds);
	public void updateFeeds(String URL,ArrayList<FeedModel> feeds);
	public boolean checkUpdates(String URL);
	public void invalidateFeed(ArrayList<FeedModel> feed);
}
