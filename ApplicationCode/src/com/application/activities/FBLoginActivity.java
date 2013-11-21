package com.application.activities;

import com.example.crawlcmu.R;
import com.example.crawlcmu.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class FBLoginActivity extends FragmentActivity {
	private FBLoginFragment mainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new FBLoginFragment();
	        getSupportFragmentManager()
	        .beginTransaction()
	        .add(android.R.id.content, mainFragment)
	        .commit();
	    } else {
	        // Or set the fragment from restored state info
	        mainFragment = (FBLoginFragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
