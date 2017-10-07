package com.mrp.track.traqbirdbus;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


@SuppressLint("SdCardPath") 
public class MainActivity extends ActionBarActivity {

	Double Lat;
	Double Lon;
	ListView lv;
	ArrayAdapter<String> listAdapter ; 
	ArrayAdapter<String> adapter;
	ArrayList<String> adist = new ArrayList<String>();
	DefaultHttpClient client = new DefaultHttpClient();
	Timer timer;
	TimerTask timerTask;
	final Handler handler = new Handler();
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LocationListener locationListener = new MyLocationListener();
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, (float)1.5, locationListener);
		tv = (TextView) findViewById(R.id.status);
		lv = (ListView) findViewById( R.id.listView1 );  
		startTimer();

		/*        String[] values = new String[] { "Android List View", 
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android", 
                "Android Example", 
                "List View Source Code", 
                "List View Array Adapter", 
                "Android Example List View" 
               };*/

		adist.add("Locations");

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, adist);

		lv.setAdapter(adapter); 
		// writetofile("test");


	}

	public void startTimer() {
		timer = new Timer();
		initializeTimerTask();

		timer.schedule(timerTask,0,2000); //
	}

	public void initializeTimerTask() {

		timerTask = new TimerTask() {
			public void run() {

				handler.post(new Runnable() {
					public void run() {
						lv.invalidateViews();
						SendRequest task = new SendRequest();
						task.execute(new String[] { "" });

					}
				});
			}
		};
	}



	private final class MyLocationListener implements LocationListener {

		@Override
		public void onProviderDisabled(String provider) {
			// called when the GPS provider is turned off (user turning off the GPS on the phone)
			addlist("provider disabled");
		}

		@Override
		public void onProviderEnabled(String provider) {
			// called when the GPS provider is turned on (user turning on the GPS on the phone)
			addlist("provider enabled");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// called when the status of the GPS provider changes
			addlist("status changed to " + status);
		}

		@Override
		public void onLocationChanged(Location location) {
			addlist("location changed");
			Lat = location.getLatitude();
			Lon = location.getLongitude();
			addlist("Latitude: "+ Lat + "Longitude: "+Lon);

		}

		public void addlist(String s){
			final String st = s;
			new Handler().post(new Runnable() {
			    @Override
			    public void run() {
			    	tv.setText(st);
					adist.add(st);
					adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, adist);	
					lv.setAdapter(adapter); 
					lv.invalidateViews();
			    }
			});
			
		}

	}
		private class SendRequest extends AsyncTask<String, Void, String> {
			@Override
			protected String doInBackground(String... distance) {
				String response = "";
				HttpPut httppost = new HttpPut("http://www.traqbird.com/livetrack/update");
					try {
						if(Lat!=null && Lon!=null)
						{
						String body = "{\"IMEI\":\"358239050741950\",\"LAT\":\"" + Lat.toString() + "\",\"LON\":\""+ Lon.toString() + "\"}";
						StringEntity entity = new StringEntity(body,"UTF-8");
						httppost.setEntity(entity);
						HttpResponse execute = client.execute(httppost);
						execute.getEntity().getContent().close();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				
				return response;
			}

			@Override
			protected void onPostExecute(String result) {

			}
		}


		
}

