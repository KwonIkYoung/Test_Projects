package com.kiy.app.test_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import Adapter.SimpleTextAdapter;
import Bean.NotiViewVo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationCheckActivity extends AppCompatActivity {

	@BindView(R.id.recyclerview)
	RecyclerView recyclerview;

	private SimpleTextAdapter mAdapter ;
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null) {
				Bundle bundle = intent.getBundleExtra("bundle");

				String msg = bundle.getString("1");

				NotiViewVo row = new NotiViewVo();
				row.title = msg;
				row.intent = intent.getParcelableExtra("pending");
				mAdapter.addItem(row);
				mAdapter.notifyDataSetChanged();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_check);
		ButterKnife.bind(this);

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.kiy_testproject.noti");
		registerReceiver(mReceiver, intentFilter);

		mAdapter = new SimpleTextAdapter(this);
		recyclerview.setLayoutManager(new LinearLayoutManager(this));
		recyclerview.setAdapter(mAdapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

}
