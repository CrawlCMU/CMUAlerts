/**
 * 
 */
package com.example.crawlcmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author ishan
 *
 */
public class FeedArrayAdapter extends ArrayAdapter<String> 
{
	private final Context context;
	private final String[] values;
	private String[] dummyContent = new String[]{"Spacious single room available for female temporary stayee/sublessee on Bayard and Melwood. Free wi-fi. Please message for more details."
												,"Does anyone have a folding table they want to get rid off?"
												, "Kudos to IGSA for such a great event !!!! cheers for the organizers !!! Its was a great experience in midst of the busy monotonous schedule !!!"
												, "Is there anyone pursuing a Computational Finance minor?"
												, "Can 80-210 Logic and Proofs be counted as a Logics & Languages elective?"
												, "How is 15-415 Database Applications in terms of course load?"
												};
												
	
	
	public FeedArrayAdapter(Context context, String[] values) 
	{
		super(context, R.layout.custom_list_feed, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.custom_list_feed, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.fbFeed);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(dummyContent[position]);
 
		// Change icon based on name
		String s = values[position];
 
		System.out.println(s);
 
		if (s.equals("FB")) {
			imageView.setImageResource(R.drawable.ic_launcher_fb);
		} else if (s.equals("IGSA")) {
			imageView.setImageResource(R.drawable.ic_launcher_igsa);
		}

 
		return rowView;
	}

}
