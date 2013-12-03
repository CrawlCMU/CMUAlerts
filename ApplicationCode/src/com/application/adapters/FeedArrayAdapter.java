/**
 * 
 */
package com.application.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crawlcmu.entities.FBFeed;
import com.example.crawlcmu.R;

/**
 * @author ishan
 *
 */
public class FeedArrayAdapter extends ArrayAdapter<FBFeed> 
{
	private final Context context;
	private ArrayList<FBFeed> feeds = new ArrayList<FBFeed>();
	
	public FeedArrayAdapter(Context context, ArrayList<FBFeed> Feeds) 
	{
		super(context, R.layout.custom_list_feed, Feeds);
		this.context = context;
		this.feeds = Feeds;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		Log.d("FeedArrayAdapter","in Get View");
		
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.custom_list_feed, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.fbFeed);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		
		
		
		textView.setText(feeds.get(position).getFeedText());
 
		// Change icon based on name
		String s = feeds.get(position).getId();
 
		System.out.println(s);
 
		// IGSA
		if (s.contains("107242810108")) 
		{
			imageView.setImageResource(R.drawable.ic_launcher_igsa);
		}
		
		// Overheard at CMU
		else if(s.contains("168160887879"))
		{
			imageView.setImageResource(R.drawable.ic_overheard);
		} 
		
		// CMU ECE MAC
		else if(s.contains("153359654710529"))
		{
			imageView.setImageResource(R.drawable.ic_ece);
		}

		return rowView;
	}

}
