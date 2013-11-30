package com.shuttletimings.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shuttletimings.parser.HTMLParser;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
	private static int index=0;
	private static String[] routes = {"Route A","Route B","Route AB"}; 
	private static String[] urls = {"http://www.cmu.edu/police/shuttleandescort/a-route-departure-times.html",
			"http://www.cmu.edu/police/shuttleandescort/b-route-departure-times.html",
			"http://www.cmu.edu/police/shuttleandescort/ab-departure-times.html"};
	
	public static Map<String, String> ITEM_MAP = new HashMap<String, String>();
	
	static {
		for(int i=0; i<routes.length; i++){
			DummyItem item = new DummyItem(routes[i],urls[i]);
			addItem(item);
		}
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.routeName, item.url);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String routeName;
		public String url;

		public DummyItem(String id, String url) {
			this.routeName = id;
			this.url = url;
		}

		@Override
		public String toString() {
			return routeName;
		}
		
		public String getURL(){
			return url;
		}
	}
	
}
