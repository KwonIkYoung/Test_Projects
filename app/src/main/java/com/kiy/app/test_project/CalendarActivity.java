package com.kiy.app.test_project;

import android.support.v4.util.LogWriter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

import util.LogUtils;

public class CalendarActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender);

		Calendar nowCal = Calendar.getInstance();
		nowCal.setTime(new Date());

		LogUtils.i("nowCal.get(Calendar.HOUR_OF_DAY)  : " + nowCal.get(Calendar.HOUR_OF_DAY) );
		if ( nowCal.get(Calendar.HOUR_OF_DAY) > 2 && nowCal.get(Calendar.HOUR_OF_DAY) < 7 ) {
			LogUtils.d("KIY"," cal 2 < hour < 7");
		}
	}
}
