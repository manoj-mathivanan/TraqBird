package com.mrp.track.traqbird;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		helper.activity = this;
		helper.application = getApplication();
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	DataBaseHandler.db = new DataBaseHandler(getApplicationContext());
            	helper.dbh = DataBaseHandler.db;
            	helper.context = getApplicationContext();
            	helper.gcmid = DataBaseHandler.db.getnewgcmid();
            	
            	if(!DataBaseHandler.db.isLoggedIn())
            	{
            		Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            		startActivity(i);
            	}
            	else
            	{
            		helper.tracks = DataBaseHandler.db.getTracks();
            		helper.selctedtrackid = DataBaseHandler.db.getSelectedTrack();
            		Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
            	}

                finish();
            }
        }, 2000);
	}



} 
