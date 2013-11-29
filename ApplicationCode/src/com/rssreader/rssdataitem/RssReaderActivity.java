package com.rssreader.rssdataitem;

import java.util.List;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.crawlcmu.R;

public class RssReaderActivity extends Activity {

	private Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_reader);
		activity = this;
		
	    RSSDataTask task = new RSSDataTask();
        task.execute("http://www.cmu.edu/RSS/stories.rss");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rss_reader, menu);
		return true;
	}
	
	private class RSSDataTask extends AsyncTask<String, Void, List<RssItem> > {
        @Override
        protected List<RssItem> doInBackground(String... urls) {
            try {
                RssReader rssReader = new RssReader(urls[0]);
                return rssReader.getItems();
             
            } catch (Exception e) {
                Log.e("CrawlCMU", "CrawlCMU : RssReaderActivity exception");
            }
             
            return null;
        }
         
        @Override
        protected void onPostExecute(List<RssItem> result) {
             
            // Get a ListView from the RSS Channel view
            ListView listItems = (ListView) findViewById(R.id.listView1);
                         
            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(activity,android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            listItems.setAdapter(adapter);
                         
            // Set list view item click listener
            listItems.setOnItemClickListener(new ListListener(result, activity));
        }
    }
	
	

}
