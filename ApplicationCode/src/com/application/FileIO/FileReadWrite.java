package com.application.FileIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;


public class FileReadWrite {

	// Read from file saved in 'assets' folder
	public static String readFileFromInternalStorage(String fileName, Context context){
		AssetManager am = context.getAssets();
		try {
			InputStream is = am.open("MyFile.txt");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			String read = new String(buffer);
			return read;
		}
		catch(FileNotFoundException e){
			Log.d("CrawlCMU", "File "+fileName+" could not be found");
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
