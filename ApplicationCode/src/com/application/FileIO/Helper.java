package com.application.FileIO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.application.DBLayout.MySQLiteHelper;
import com.application.DBLayout.Preferences;

public class Helper {
	private static boolean done = false;
	
	// Helper function to read from file and store the information in DB
	// Used only once, whenever the app is started.
	public static void writeFromFileToDB(String filename, Context context){
		//Log the invocation
	    Log.d("CrawlCMU", "WriteFromFileTODB : writeFromFileToDB() called");
	    
	    // Call this function only the first time app is launched.
	    // Guard flag to prevent writing to DB on subsequent app launches.
	    if(done)  return;
	    
		String fileContents = FileReadWrite.readFileFromInternalStorage(filename,context);
		if(fileContents==null){
			Toast.makeText(context, "Sorry.Could not read from file", Toast.LENGTH_LONG).show();
			return;
		}
		MySQLiteHelper db = new MySQLiteHelper(context);
		// Delete table before adding contents.
//		db.destroyDB();
		String[] lines = fileContents.split(";");
		for(String line : lines){
			String[] fields = line.split(",");
			Preferences p = new Preferences(fields[0].trim(),fields[1].trim(),fields[2].trim(),"unsubscribed");
			db.addpreference(p);
		}
		done = true;	// Set the flag
		db.close();	// CLose the database connection
	}
}
