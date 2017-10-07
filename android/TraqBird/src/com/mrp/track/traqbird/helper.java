package com.mrp.track.traqbird;

import java.util.HashMap;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class helper {
	
	public static HashMap<String, String> tracks = new HashMap<String, String>();
	public static String selctedtrackid;
	public static Context context;
	public static Activity activity;
	public static Application application;
	public static DataBaseHandler dbh;
	public static String gcmid;

}
