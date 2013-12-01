package com.rssreader.rssdataitem;

import java.util.List;

import com.application.networkstate.NetworkState;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListListener implements OnItemClickListener{

	private List<RssItem> rssList;
	private Activity activity;
	
	public ListListener(List<RssItem> rssList, Activity activity){
		this.rssList = rssList;
		this.activity = activity;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		
		if(!NetworkState.haveNetworkConnection(activity)){
			Toast.makeText(activity, "Sorry.No network connectivity", Toast.LENGTH_LONG).show();
			activity.finish();
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(rssList.get(pos).getLink()));
		activity.startActivity(i);
	}
}
