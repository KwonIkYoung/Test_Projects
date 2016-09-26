package com.kiy.app.test_project;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

import facebook.NativeAdListActivity;
import video.ExoPlayerActivity;
import video.UniversalVideoViewActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.LogUtils;
import video.VideoViewActivity;

public class TestMainActivity extends AppCompatActivity {

	@BindView(R.id.btn_check_package)
	Button mBtnCheckPackage;
	@BindView(R.id.btn_sub_string)
	Button mBtnSubString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_main);
		ButterKnife.bind(this);
	}

	@OnClick({R.id.btn_check_package, R.id.btn_sub_string, R.id.btn_video_test, R.id.btn_base64,R.id.btn_reg,R.id.btn_captcha, R.id.btn_exo_player,R.id.btn_exo_player2, R.id.btn_service_start,
				R.id.btn_service_start_noti , R.id.btn_cal_check,R.id.btn_facebook_native_ad, R.id.btn_doze_check})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_check_package:
				startActivity(new Intent(TestMainActivity.this, PackageTestActivity.class));
				break;
			case R.id.btn_sub_string:
				String str = "가나라";
				//		String str = "가,나,다,라,";

				String result = str.substring(0, str.lastIndexOf(","));

				LogUtils.i(">> sub Str : " + result);
				break;
			case R.id.btn_video_test:
				new AlertDialog.Builder(this).setItems(new String[]{"UniversalVideoView","VideoViewClass"}, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which){
							case 0: {
								Intent intent = new Intent(TestMainActivity.this, UniversalVideoViewActivity.class);
								startActivity(intent);
								break;
							}
							case 1: {
								Intent intent = new Intent(TestMainActivity.this, VideoViewActivity.class);
								startActivity(intent);
								break;
							}
							default:
								break;
						}
					}
				}).show();
				break;
			case R.id.btn_base64:
				Intent intent = new Intent(TestMainActivity.this, Base64Activity.class);
				startActivity(intent);
				break;
			case R.id.btn_reg:
				String period = "";
				boolean flag = TextUtils.isEmpty(period) == false &&  Pattern.matches("^[0-9]*$", period) ;
				Toast.makeText(TestMainActivity.this, "flag : "+ flag, Toast.LENGTH_SHORT).show();
				break;
			case R.id.btn_captcha:
				startActivity(new Intent(TestMainActivity.this, CaptchaActivity.class));
				break;
			case R.id.btn_exo_player:
				startActivity(new Intent(TestMainActivity.this, ExoPlayerActivity.class));
				break;
			case R.id.btn_exo_player2:
				startActivity(new Intent(TestMainActivity.this, EXOPlayer2Activity.class));
				break;
			case R.id.btn_service_start:
				// 서비스 가동중인지 체크해서 Setting 화면 띄우기
				if(!isContainedInAccessbility(getBaseContext())) {
					// Alert으로 대략 내가 왜 이걸 한다라는걸 보여주면 좋음.
					Intent serviceIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
					startActivityForResult(serviceIntent, 111);
				}
				break;
			case R.id.btn_service_start_noti:
				// 서비스 가동중인지 체크해서 Setting 화면 띄우기
					// Alert으로 대략 내가 왜 이걸 한다라는걸 보여주면 좋음.
					Intent serviceIntent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
					startActivityForResult(serviceIntent, 222);
				break;
			case R.id.btn_cal_check:
				startActivity(new Intent(TestMainActivity.this, CalendarActivity.class));
				break;
			case R.id.btn_facebook_native_ad:
				startActivity(new Intent(TestMainActivity.this, NativeAdListActivity.class));
				break;
			case R.id.btn_doze_check:
				startActivity(new Intent(TestMainActivity.this, DozeCheckActivity.class));
				break;
			default:
				break;
		}
	}

	// 서비스 가동중인지 체크 소스
	public static boolean isContainedInAccessbility(Context context) {
		AccessibilityManager accessibilityManager = (AccessibilityManager)context.getSystemService(Context.ACCESSIBILITY_SERVICE);
		List<AccessibilityServiceInfo> serviceList = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
		return serviceList.toString().contains(context.getPackageName());
	}

//	@OnClick(R.id.btn_check_package)
//	public void onClick() {
//		startActivity(new Intent(TestMainActivity.this, PackageTestActivity.class));
//	}
//
//	@OnClick(R.id.btn_sub_string)
//	public void onClickSubString() {
//		String str = "가나라";
////		String str = "가,나,다,라,";
//
//		String result = str.substring(0, str.lastIndexOf(","));
//
//		LogUtils.i(">> sub Str : " + result);
//	}
}
