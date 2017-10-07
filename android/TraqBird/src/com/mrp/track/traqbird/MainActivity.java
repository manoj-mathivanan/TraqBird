package com.mrp.track.traqbird;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

@SuppressLint("NewApi") 
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.header);
		@SuppressWarnings("deprecation")
		BitmapDrawable background = new BitmapDrawable(bmp);
		background.setGravity(0x77);
		//background.setTileModeX(android.graphics.Shader.TileMode.CLAMP);
		//background.setTileModeY(android.graphics.Shader.TileMode.REPEAT); 
		background.setGravity(119);
		bar.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent))); 
		bar.setBackgroundDrawable(background);
		
		bar.setDisplayShowTitleEnabled(false);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        Fragment fragment = new MapActivity();
    	FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, fragment).commit();
    }

    @SuppressLint("NewApi") @Override
    public void onNavigationDrawerItemSelected(int position) {
    	if(position==0)
    	{
    	Fragment fragment = new MapActivity();
    	FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, fragment).commit();
    	}
    	else if(position ==1)
    	{
    		Fragment fragment = new SettingsActivity();
        	FragmentManager fragmentManager = getFragmentManager();
    		fragmentManager.beginTransaction()
    				.replace(R.id.container, fragment).commit();
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


}
