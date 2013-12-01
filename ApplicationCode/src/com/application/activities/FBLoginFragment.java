package com.application.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.DBLayout.MySQLiteHelper;
import com.application.DBLayout.Preferences;
import com.application.managesubscriptions.FBSubscribeActivity;
import com.example.crawlcmu.R;
import com.example.crawlcmu.R.id;
import com.example.crawlcmu.R.layout;
import com.facebook.Request;
import com.facebook.RequestBatch;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class FBLoginFragment extends Fragment
{
	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;
	MySQLiteHelper sqlHelper;
	private HashMap<String,String> groupIDMap;
	private boolean hasLoggedIn = false;
	
	private ProgressBar bar;
	
	private static final int NumFeedsPerGroup = 3; 
	
	private static int groupID;
	private int numSubscribed;
	
	// this is a hashmap of String (Group Name) and its Feeds are stored in ArrayList<Strings>
	private HashMap<String, ArrayList<String>> crawlFeeds;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	    sqlHelper = new MySQLiteHelper(getActivity());
	    groupIDMap = new HashMap<String, String>();
	    crawlFeeds = new HashMap<String, ArrayList<String>>();
	    
	    
	    initializeSubscriptionList();
	}
	
	private void initializeSubscriptionList() 
	{
		
		Log.d(TAG, "Inititalize called");
		groupID = 0;
		numSubscribed = 0;
		List<Preferences> listPreferences;
		listPreferences = sqlHelper.getPreferences("Facebook","subscribed");
		
		crawlFeeds.clear();
		// Clear the hashmap, because this function will be called every time a group is subsribed/unsubscribed
		groupIDMap.clear();
		
		if(listPreferences.size() == 0)
		{
			
			// no groups have been subsribed to!
			// Redirect to subscription Activity and let it handle the rest
			
			Log.d(TAG, "******* Going to subscription activity");
			
			Toast.makeText(getActivity(),"No groups subscribed, redirecting to subscription page!", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(getActivity(),FBSubscribeActivity.class);
			i.putExtra("subscription", "subscribed");
    		i.putExtra("NoSubscriptions", true);
			startActivity(i);
			
		}
		
		else
		{
		
			for(Preferences p : listPreferences)
			{
				numSubscribed++;
				// Build the hashmap of preferences
				String title = p.getTitle();
				String url = p.getUrl();
				
				String[] urlSplit = url.split("/");
				String grpID = "";
				boolean isGroupID = false;
				
				for(String str:urlSplit)
				{
					if(isGroupID)
						grpID = str;
					if(str.contains("groups"))
						isGroupID = true;
				}
				
				Log.d(TAG,"Subscribed List = "+title + "Grp ID"+grpID);
				
				// groupIDMap contains Name - GroupID mapping
				// Of all groups that the user has subscribed to
				groupIDMap.put(title, grpID);
			}
			
		}
	}
	
	
	
	
	
	@Override
	public void onResume() {
	    super.onResume();
	    
	    // For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    
	    Log.d(TAG, "*****ON RESUME");
	    
	    initializeSubscriptionList();
	    
	    
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }

	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) 
	    {
	        Log.i(TAG, "Logged in...");
	        bar.setVisibility(View.VISIBLE);
	        doBatchRequest();
	        //batchRequestButton.setVisibility(View.VISIBLE);
	    } 
	    else if (state.isClosed()) 
	    {
	        Log.i(TAG, "Logged out...");
	        bar.setVisibility(View.INVISIBLE);
	        //batchRequestButton.setVisibility(View.INVISIBLE);
	    }
	}
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	
	
	private void doBatchRequest() 
	{
	   
		Log.d(TAG, "DO BATCH");
		if(groupIDMap.isEmpty())
		{
			Log.d(TAG, "Nothing initialized so inititalizing");
			initializeSubscriptionList();
		}
	    
	    // populate the group IDs from the groupID hashmap
	    String[] requestIds = new String[groupIDMap.size()];
	    
	    int i = 0;
	    for (Map.Entry<String, String> entry : groupIDMap.entrySet()) 
	    {
			Log.d(TAG,"Grp Name = "+entry.getKey() + " Grp ID = "+entry.getValue());
			requestIds[i++] = entry.getValue() + "/feed";
		}
	    
	    
	    RequestBatch requestBatch = new RequestBatch();
	    for (final String requestId : requestIds) {
	        requestBatch.add(new Request(Session.getActiveSession(), 
	                requestId, null, null, new Request.Callback() {
	            public void onCompleted(Response response) 
	            {
	            	groupID++;
	            	StringBuilder myuserInfo = new StringBuilder("");
	                GraphObject graphObject = response.getGraphObject();
	                
	                if (graphObject != null) 
	                {
	                        ArrayList<String> feeds = new ArrayList<String>();
	                        JSONArray groupFeeds = (JSONArray) graphObject.getProperty("data");
	                        
	                        if(groupFeeds!=null)
	                        {
	                        	Log.d(TAG, "FEEDS are available");
	                        	for (int i=0; i < NumFeedsPerGroup; i++) {
	            	                JSONObject grp = groupFeeds.optJSONObject(i);
	            	                 
	            	                feeds.add(grp.optString("message"));
	            	            }   
	            	    		
	                        	Log.d(TAG, "requestID = " + requestId + " Feeds = " + feeds.toString());
	                        	crawlFeeds.put(requestId, feeds);
	                        	myuserInfo.append(String.format("groups: %s\n\n", feeds.toString()));
	                        }
	                    	
	                 }
	                
	                if(groupID == numSubscribed)
	                {
	                	Log.d(TAG,"Feed Fetching Completed!");
	                	
	                	if(crawlFeeds == null || crawlFeeds.size() == 0)
	                	{
	                		// Close the current session
	                		Session session = Session.getActiveSession();
	                		session.close();
	                		
	                		// Show network error toast
	                		Toast.makeText(getActivity(),"Please check your network connection and try again later ", Toast.LENGTH_LONG).show();
	            			
	                		
	                	    uiHelper.onDestroy();
	                		
	                		// Go back to homescreen
	                		Intent i = new Intent(getActivity(),HomeScreen.class);
	            			startActivity(i);
	                	}
	                	else
	                	{
		                	// now send the populated HashMap to the FBFeedActivity
		                	Intent showFeedsIntent = new Intent(getActivity(),FBFeedActivity.class);
		                	showFeedsIntent.putExtra("FeedsMap", crawlFeeds);
		                	
		                	bar.setVisibility(View.INVISIBLE);
		                	
		                	Log.d(TAG,"Going to FBFeed Activity");
		                	startActivity(showFeedsIntent);
	                	}
	                }
	                
	            }
	        }));
	    }
	    requestBatch.executeAsync();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.main, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    
	    bar = (ProgressBar) view.findViewById(R.id.loading_spinner);
	    
	    
	    // Set user permissions for access token
	    authButton.setReadPermissions(Arrays.asList("user_location", "user_birthday", "user_likes","user_groups"));
	    
	    //doBatchRequest();
	    
	    return view;
	}
}
