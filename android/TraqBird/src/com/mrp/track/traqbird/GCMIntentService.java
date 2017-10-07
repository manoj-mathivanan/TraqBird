
package com.mrp.track.traqbird;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService{
	private static final String TAG = "GCM service";
	public String lat,lon,event,msg;
	@SuppressWarnings("static-access")
	public GCMIntentService()
	{
		super(DataBaseHandler.db.SENDERID);
        Log.i( "GCM", "GCMIntentService constructor called" );
	}

	@Override
	protected void onError(Context arg0, String errorId) {
		Log.i(TAG, "Received error: " + errorId);
	}

	@Override
	protected void onMessage(Context arg0, Intent intent) {

		String data = intent.getStringExtra( "message" );
		Log.i(TAG, "new message= "+ intent.getStringExtra( "cdata" ));
		//NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		int icon = R.drawable.ic_launcher;
		//CharSequence tickerText = "TRAQBIRD";

		Context context = getApplicationContext();
		CharSequence contentTitle = "SIS Tracko";
		CharSequence contentText = data;
		Intent notificationIntent = new Intent(this, com.mrp.track.traqbird.LoginActivity.class);
		int mNotificationId = 001;
		 
		PendingIntent resultPendingIntent =
		        PendingIntent.getActivity(
		                context,
		                0,
		                notificationIntent,
		                PendingIntent.FLAG_CANCEL_CURRENT
		        );
		 
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
		        context);
		Notification notification = mBuilder.setSmallIcon(icon).setTicker(contentTitle).setWhen(0)
		        .setAutoCancel(true)
		        .setContentTitle(contentTitle)
		        .setStyle(new NotificationCompat.BigTextStyle().bigText(contentText))
		        .setContentIntent(resultPendingIntent)
		        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
		        .setContentText(contentText).build();
		 
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(mNotificationId, notification);
		
		
		Log.i("GCM"," showing Notification" );
		
		
	}

	@Override
	protected void onRegistered(Context arg0, String registrationId) {
		 Log.i(TAG, "Device registered: regId = " + registrationId);
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		 Log.i(TAG, "unregistered = " + arg1);
	}

}
