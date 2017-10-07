package com.mrp.track.traqbird;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.graphics.PorterDuff;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("NewApi")
public class MapActivity extends Fragment {
  static LatLng START = new LatLng(12.9800818,77.713329);
  private GoogleMap map;
  Timer timer;
	TimerTask timerTask;
	static MarkerOptions start;
	final Handler handler = new Handler();
	DefaultHttpClient client = new DefaultHttpClient();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.map_activity, container, false);
		MapFragment mf = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
		if(mf==null)
			mf = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		map = mf.getMap();
    start = new MarkerOptions();
   
    startTimer();

    // Move the camera instantly to hamburg with a zoom of 15.
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(START, 15));

    // Zoom in, animating the camera.
    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    
    Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner1);
    
    // Spinner click listener
   
    // Spinner Drop down elements
    List<String> categories = new ArrayList<String>();
    categories.add("Automobile");
    categories.add("Business Services");
    
   
    
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, categories);
    
    spinner.setAdapter(dataAdapter);
    
    return rootView;
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
						SendRequest task = new SendRequest();
						task.execute(new String[] { "" });

					}
				});
			}
		};
	}
	
	private class SendRequest extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... distance) {
			String response = "";
			HttpGet httpget = new HttpGet("http://www.traqbird.com/livetrack/getlive");
				try {
					BasicHttpResponse send_call_response;
					httpget.addHeader("IMEI", "358239050741950");
					send_call_response = (BasicHttpResponse) client.execute(httpget);
					response = EntityUtils.toString(send_call_response.getEntity());
					JSONObject reader = new JSONObject(response);
					JSONArray livedata = reader.getJSONArray("live_data");
					JSONObject sys  = livedata.getJSONObject(0);
					String lat = sys.getString("lat");
					String lon = sys.getString("lon");
					START = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) {

			start.position(START);
			start.title("");

			map.clear();
			map.addMarker(start);

			map.moveCamera(CameraUpdateFactory.newLatLng(START));
		}
	}


} 
