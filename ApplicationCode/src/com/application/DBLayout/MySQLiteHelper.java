package com.application.DBLayout;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "preferenceDB";
    // Table Name
    private static final String TABLE_NAME = "preferenceTable";

    // preference Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String KEY_STATUS = "status";
    
    
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create preference table
        String CREATE_preference_TABLE = "CREATE TABLE IF NOT EXISTS preferenceTable ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "type TEXT, " +
                "title TEXT," +
                "url TEXT, "+
                "status TEXT)";
 
        // create preference table
        db.execSQL("DROP TABLE IF EXISTS preferenceTable");
        db.execSQL(CREATE_preference_TABLE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older preference table if existed
        db.execSQL("DROP TABLE IF EXISTS preferenceTable");
 
        // create new preference table
        this.onCreate(db);
	}
	
	public void addpreference(Preferences preference){
        //Log the transaction
		Log.d("CrawlCMU", "MYSQLiteHelper : addPreference() called"); 
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, preference.getType()); // get type
		values.put(KEY_TITLE, preference.getTitle()); // get type
		values.put(KEY_URL, preference.getUrl()); // get url
		values.put(KEY_STATUS, preference.getStatus()); // get status
		
		// 3. insert
		db.insert(TABLE_NAME, // table
		        null, //nullColumnHack
		        values); // key/value -> keys = column names/ values = column values
		
		// 4. close
		db.close(); 
	}
	
	/**
	 * Drops the table if exists and creates a new one
	 */
	public void initializeDB(){
		SQLiteDatabase db = this.getWritableDatabase();
		onCreate(db);
	}
	
	public List<Preferences> getPreferences(String type, String status){
		   //Log the transaction
	       Log.d("CrawlCMU", "MYSQLiteHelper : getPreferences() called");
	       List<Preferences> preferences = new LinkedList<Preferences>();
	       
	       if(!type.equals("Facebook") && !type.equals("Twitter") && !type.equals("Alerts") && !type.equals("CMUFeed")){
	    	   Log.d("CrawlCMU", "MYSQLiteHelper : getPreferences() Invalid type");
	       	   return null;
	       }else if(!status.equals("subscribed") && !status.equals("unsubscribed")){
	    	   Log.d("CrawlCMU", "MYSQLiteHelper : getPreferences() Invalid status");
	       	   return null;
	       }
	       
	       // 1. build the query
	       String query = "SELECT  * FROM " + TABLE_NAME+" WHERE type=\""+type+"\" and status=\""+status+"\"";
	 
	       // 2. get reference to writable DB
	       SQLiteDatabase db = this.getReadableDatabase();
	       Cursor cursor = db.rawQuery(query, null);
	 
	       // 3. go over each row, build preference and add it to list
	       Preferences preference = null;
	       if (cursor.moveToFirst()) {
	           do {
	               preference = new Preferences();
	               preference.setType(cursor.getString(1));
	               preference.setTitle(cursor.getString(2));
	               preference.setUrl(cursor.getString(3));
	               preference.setStatus(cursor.getString(4));
	 
	               // Add preference to preferences
	               preferences.add(preference);
	           } while (cursor.moveToNext());
	       }
	 
	       db.close();
	       // return preferences
	       return preferences;
	}
	
	
	// This may not be very efficient.We are writing only one row.
	// We should ideally batch requests up 
	public void setPreference(String title, String status){
		   //Log the transaction
	       Log.d("CrawlCMU", "MYSQLiteHelper : setPreference() called");
	       
	       if(!status.equals("unsubscribed") && !status.equals("subscribed")){
	    	   Log.d("CrawlCMU", "MYSQLiteHelper : setPreference() Incorrect status");
	    	   return;
	       }
	       
	       // 1. build the query
	       String query = "UPDATE "+TABLE_NAME+" SET status=\""+status+"\" where title=\""+title+"\"";
	 
	       // 2. get reference to writable DB
	       SQLiteDatabase db = this.getWritableDatabase();
	       // 3. execute query 
	       db.execSQL(query);
	       db.close();
	}
	
	
	// Returns all values in the table
	public List<Preferences> getAllpreferences() {
		   //Log the transaction
	       Log.d("CrawlCMU", "MYSQLiteHelper : getAllPreferences() called");
	       
	       List<Preferences> preferences = new LinkedList<Preferences>();
	 
	       // 1. build the query
	       String query = "SELECT  * FROM " + TABLE_NAME;
	 
	       // 2. get reference to writable DB
	       SQLiteDatabase db = this.getReadableDatabase();
	       Cursor cursor = db.rawQuery(query, null);
	 
	       // 3. go over each row, build preference and add it to list
	       Preferences preference = null;
	       if (cursor.moveToFirst()) {
	           do {
	               preference = new Preferences();
	               preference.setType(cursor.getString(1));
	               preference.setTitle(cursor.getString(2));
	               preference.setUrl(cursor.getString(3));
	               preference.setStatus(cursor.getString(4));
	 
	               // Add preference to preferences
	               preferences.add(preference);
	           } while (cursor.moveToNext());
	       }
	       db.close();
	       // return preferences
	       return preferences;
	   }
}
