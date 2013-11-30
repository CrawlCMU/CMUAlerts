package com.shuttletimings;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.application.networkstate.NetworkState;
import com.example.crawlcmu.R;
import com.shuttletimings.dummy.DummyContent;
import com.shuttletimings.parser.HTMLParser;

/**
 * A fragment representing a single Route detail screen. This fragment is either
 * contained in a {@link RouteListActivity} in two-pane mode (on tablets) or a
 * {@link RouteDetailActivity} on handsets.
 */
public class RouteDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";
	
	/**
	 * The dummy content this fragment is presenting.
	 * Contains the url
	 */
	private String mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public RouteDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(!NetworkState.haveNetworkConnection(getActivity())){
			Toast.makeText(getActivity(), "Sorry.No network connectivity", Toast.LENGTH_LONG).show();
		}
		
		View rootView = inflater.inflate(R.layout.fragment_route_detail, container, false);
		new ParseHTMLTask(getActivity(),rootView).execute(mItem);			
		return rootView;
	}
	
	
	private static class ParseHTMLTask extends AsyncTask<String, Void, String> {
		private Context context;
		private View rootView;
		
		public ParseHTMLTask(Context context, View view){
			this.context = context;
			this.rootView = view;
		}
		
		
        @Override
        protected String doInBackground(String... urls) {
          StringBuilder response = new StringBuilder();
          
          for (String url : urls) {
            try {
            	HTMLParser parser = new HTMLParser();
            	ArrayList<String> result = parser.parse(url);
            	for(String str : result)
            		response.append(str+"\n");
            	
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          return response.toString();
        }
        
        @Override
        protected void onPostExecute(String result) {
        	((TextView) rootView.findViewById(R.id.route_detail)).setText(result);
        }
  }   
	
}
