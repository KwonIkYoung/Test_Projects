package com.kiy.app.test_project;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingChangeActivity extends AppCompatActivity {

	@BindView(R.id.btn_wifi)
	Button mBtnWifi;
	@BindView(R.id.btn_bluetooth)
	Button mBtnBluetooth;
	@BindView(R.id.btn_flash)
	Button mBtnFlash;
	@BindView(R.id.btn_sound)
	Button mBtnSound;
	@BindView(R.id.btn_1)
	Button mBtn1;
	@BindView(R.id.btn_2)
	Button mBtn2;
	@BindView(R.id.btn_3)
	Button mBtn3;
	@BindView(R.id.btn_4)
	Button mBtn4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_change);
		ButterKnife.bind(this);
	}

	@OnClick({R.id.btn_wifi, R.id.btn_bluetooth, R.id.btn_flash, R.id.btn_sound, R.id.ll_setting_1, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_wifi:
				WifiManager wManager = (WifiManager)getSystemService(Activity.WIFI_SERVICE);

				if(wManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED ||
						wManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING ) {
					wManager.setWifiEnabled(false);
				} else {
					wManager.setWifiEnabled(true);
				}

				break;
			case R.id.btn_bluetooth:
				BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

				//현재 Bluetooth가 켜져 있는지, 혹은 켜는 중인지 확인 한다.
				if(adapter.getState() == BluetoothAdapter.STATE_TURNING_ON ||
						adapter.getState() == BluetoothAdapter.STATE_ON) {
					adapter.disable();   // Bluetooth Off
				} else {
					adapter.enable();     // Bluetooth On
				}
				break;
			case R.id.btn_flash:
				break;
			case R.id.btn_sound:
				break;
			case R.id.btn_1:
				break;
			case R.id.btn_2:
				break;
			case R.id.btn_3:
				break;
			case R.id.btn_4:
				break;
		}
	}
}
