package com.mrp.track.traqbird;

import java.util.HashMap;

import com.google.android.gcm.GCMRegistrar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHandler extends SQLiteOpenHelper {
	
	public static DataBaseHandler db;
	public static String gcmRegId="";
	Integer attempgcm = 0;
	public static String SENDERID = "312227851237";

private static final int DATABASE_VERSION = 1;

private static final String DATABASE_NAME = "traqbird";

private static final String TABLE_DETAILS = "details";

private static final String NAME = "name";
private static final String MOBILE = "mobile";
private static final String EMAIL = "email";
private static final String GCMID = "gcmid";
private static final String TRACKID = "trackid";
private static final String TRACKNAME = "trackname";
private static final String SIGNUPDATE = "signupdate";
private static final String SELECTEDTRACKID = "selectedtrackid";
private static final String DEMOTRACKID = "123456789";

public DataBaseHandler(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DETAILS + "("+TRACKID+" TEXT PRIMARY KEY,"+ NAME + " TEXT,"+ EMAIL + " TEXT," + MOBILE + " TEXT," + GCMID + " TEXT," + TRACKNAME + " TEXT," + SIGNUPDATE + " TEXT," + SELECTEDTRACKID + " TEXT)";
db.execSQL(CREATE_CONTACTS_TABLE);
}

public Boolean isLoggedIn()
{
	String selectQuery = "SELECT * FROM details";
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) 
	{
		return true;
	}
	return false;
}

public String getMobileNumber()
{
	String selectQuery = "SELECT * FROM details";
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) 
	{
		return cursor.getString(cursor.getColumnIndex(MOBILE));
	}
	return null;
}

public String getName()
{
	String selectQuery = "SELECT * FROM details";
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) 
	{
		return cursor.getString(cursor.getColumnIndex(NAME));
	}
	return null;
}

public String getGCMID()
{
	String selectQuery = "SELECT * FROM details";
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) 
	{
		return cursor.getString(cursor.getColumnIndex(GCMID));
	}
	return null;
}

public String getEmail()
{
	String selectQuery = "SELECT * FROM details";
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) 
	{
		return cursor.getString(cursor.getColumnIndex(EMAIL));
	}
	return null;
}

public String getSignupDate()
{
	String selectQuery = "SELECT * FROM details";
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) 
	{
		return cursor.getString(cursor.getColumnIndex(SIGNUPDATE));
	}
	return null;
}

public String getSelectedTrack()
{
	String selectQuery = "SELECT * FROM details";
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	if (cursor.moveToFirst()) 
	{
		return cursor.getString(cursor.getColumnIndex(SELECTEDTRACKID));
	}
	return null;
}

public HashMap<String, String> getTracks()
{
	String selectQuery = "SELECT * FROM details";
	HashMap<String, String> tracks = new HashMap<String, String>();
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor = db.rawQuery(selectQuery, null);
	while(!cursor.isAfterLast()) 
	{
		tracks.put(cursor.getString(cursor.getColumnIndex(TRACKNAME)), cursor.getString(cursor.getColumnIndex(TRACKID)));
	}
	return tracks;
}

public void cleardb()
{
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
		onCreate(db);
}

public void adduser(String name, String email,String mobile)
{
	SQLiteDatabase db = this.getWritableDatabase();
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
	onCreate(db);
	ContentValues values = new ContentValues();
	values.put(NAME, name);
	values.put(EMAIL, email);
	values.put(MOBILE, mobile);
	values.put(TRACKID, DEMOTRACKID);
	values.put(TRACKNAME, "DEMO");
	values.put(SIGNUPDATE, "" + System.currentTimeMillis());
	values.put(SELECTEDTRACKID, DEMOTRACKID);
	db.insert(TABLE_DETAILS, null, values);
	db.close();
}
public void addTrack(String trackid, String trackname)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(NAME, getName());
	values.put(EMAIL, getEmail());
	values.put(MOBILE, getMobileNumber());
	values.put(GCMID, getGCMID());
	values.put(TRACKID, trackid);
	values.put(TRACKNAME, trackname);
	values.put(SIGNUPDATE, getSignupDate());
	values.put(SELECTEDTRACKID, trackid);
	db.insert(TABLE_DETAILS, null, values);
	db.close();
}

public void setGCMID(String gcmid)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(GCMID, gcmid);
	db.update(TABLE_DETAILS, values, MOBILE + " = ?",new String[] { getMobileNumber() });
	db.close();
}

public void setSelectedTrackid(String trackid)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(SELECTEDTRACKID, trackid);
	db.update(TABLE_DETAILS, values, MOBILE + " = ?",new String[] { getMobileNumber() });
	db.close();
}

public void deleteTrackid(String trackid)
{
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(TRACKID, trackid);
	db.delete(TABLE_DETAILS, TRACKID + " = ?",new String[] { trackid });
	db.close();
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
	onCreate(db);
}

public String getnewgcmid() {
	GCMRegistrar.checkDevice(helper.activity.getBaseContext());
    GCMRegistrar.checkManifest(helper.activity.getBaseContext());
    String gcmRegIDNew = GCMRegistrar.getRegistrationId(helper.activity.getBaseContext());
    if(!gcmRegIDNew.equals(gcmRegId)){
    	gcmRegId = gcmRegIDNew;
    }
      GCMRegistrar.register(helper.activity.getBaseContext(), SENDERID);
    if((gcmRegId.isEmpty())&&(attempgcm<3))
    {
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	attempgcm++;
    	getnewgcmid();
    }
    return gcmRegIDNew;
}


}

