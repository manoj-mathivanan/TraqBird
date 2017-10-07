package com.mrp.track.traqbird;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
	}
	
	public void register(View v)
	{
		Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
		
	}
	
	  private class Onboarding extends AsyncTask<String, Void, String> {
		  
		    @Override
		    protected String doInBackground(String... ags) {
				 try{
					 return null;
					
				} catch (Exception e) {
					
					return null;
				}
		    }

		    @Override
		    protected void onPostExecute(String result) {
		    	DataBaseHandler.db.setGCMID(helper.gcmid);
		    }
	  }


} 
