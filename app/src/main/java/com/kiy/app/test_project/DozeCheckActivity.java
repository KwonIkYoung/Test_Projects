package com.kiy.app.test_project;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DozeCheckActivity extends AppCompatActivity {
	private final static String TAG = "KIY";

	private BroadcastReceiver mDozeModeReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doze_check);

		if (mDozeModeReceiver != null) {
			unregisterReceiver(mDozeModeReceiver);
		}

		mDozeModeReceiver = new BroadcastReceiver() {
			@TargetApi(23)
			@Override
			public void onReceive(Context context, Intent intent) {
				Log.e(TAG, intent.toString());
				PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
				if (Build.VERSION.SDK_INT >= (Build.VERSION_CODES.M)) {
					if (pm.isDeviceIdleMode()) {
						Log.e(TAG, "Device on Doze Mode");
						Toast.makeText(DozeCheckActivity.this,"Device on Doze Mode !!" , Toast.LENGTH_SHORT).show();
					} else {
						Log.e(TAG, "Device on Active Mode");
						Toast.makeText(DozeCheckActivity.this,"Device on Active Mode" , Toast.LENGTH_SHORT).show();
					}
				}
			}
		};

		IntentFilter filter = new IntentFilter();
		filter.addAction(PowerManager.ACTION_DEVICE_IDLE_MODE_CHANGED);
		getApplicationContext().registerReceiver(mDozeModeReceiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDozeModeReceiver != null) {
			unregisterReceiver(mDozeModeReceiver);
		}
	}
}
