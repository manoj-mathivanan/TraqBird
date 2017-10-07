package com.example.soaptest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	DefaultHttpClient client = new DefaultHttpClient();
	TextView response;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response = (TextView) findViewById(R.id.response);
        SendRequest task = new SendRequest();
		task.execute(new String[] { "" });
    }
    
    private class SendRequest extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... distance) {
			String strresponse = "";
			HttpPost httppost = new HttpPost("http://111.93.131.113:7070/GSRTCWS2/reservationservice");
				try {
					BasicHttpResponse send_call_response;
					httppost.addHeader("Authorization", "Basic cmFkd3NuaWM6cmFkd3NuaWM=");
					 String xml = "<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:com=\"com.gsrtc.prws.service.bo\">"+
							    "<x:Header/>\n"+
							    "<x:Body>\n"+
							        "<com:GetAvailableServiceDetails>\n"+
							            "<arg0>\n"+
							                "<counterCode>GSNIC</counterCode>\n"+
							                "<userID>2824</userID>\n"+
							                "<userName>GSNIC</userName>\n"+
							                "<startPlaceID>1</startPlaceID>\n"+
							                "<endPlaceID>110</endPlaceID>\n"+
							                "<journeyToTime>23:59</journeyToTime>\n"+
							                "<journeyFromTime>00:00</journeyFromTime>\n"+
							                "<journeyDate>25/04/2017</journeyDate>\n"+
							                "<noOfSeats>2</noOfSeats>\n"+
							                "<serviceClass>0</serviceClass>\n"+
							                "<totFemales>1</totFemales>\n"+
							                "<totMales>1</totMales>\n"+
							            "</arg0>\n"+
							        "</com:GetAvailableServiceDetails>\n"+
							   "</x:Body>\n"+
							"</x:Envelope>";
				     HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
				     httppost.setEntity(entity);
					send_call_response = (BasicHttpResponse) client.execute(httppost);
					strresponse = EntityUtils.toString(send_call_response.getEntity());
					
					System.out.println(strresponse);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			return strresponse;
		}

		@Override
		protected void onPostExecute(String result) {
			response.setText(result);
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
