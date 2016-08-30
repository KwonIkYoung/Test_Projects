package com.kiy.app.test_project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.LogUtils;

public class PackageTestActivity extends AppCompatActivity {

	@BindView(R.id.btn_check)
	Button mBtnCheck;
	@BindView(R.id.et_package_name)
	EditText mEtPackageName;
	@BindView(R.id.tv_result)
	TextView mTvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_package_test);
		ButterKnife.bind(this);
	}

	private void getInstalledApps(boolean getSysPackages) {
		List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
		for(int i=0;i<packs.size();i++) {
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && (p.versionName == null)) {
				continue ;
			}
			LogUtils.logi("KIY" , " >> "+p.applicationInfo.loadLabel(getPackageManager()).toString());
			LogUtils.logi("KIY" , " >> "+p.packageName);
			LogUtils.d("KIY", "-----------------------");
		}
	}

	private boolean isPackageInstalled(String packagename, Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	@OnClick(R.id.btn_check)
	public void onClick() {
		String packageName = mEtPackageName.getText().toString();

		String result = "Package Check Result \n - "+packageName + " is "+ isPackageInstalled(packageName,this);

		mTvResult.setText(result);
		getInstalledApps(false);

//		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//		final List pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);
//
//		for (int i = 0; i < pkgAppsList.size(); i++) {
//			ResolveInfo pkgItem = (ResolveInfo)pkgAppsList.get(i);
//			LogUtils.i("KIY",pkgItem.activityInfo.packageName);
//			LogUtils.i("KIY",pkgItem.activityInfo.name);
//			LogUtils.i("KIY",pkgItem.activityInfo.processName);
//			LogUtils.d("KIY","--------------------------------------");
//		}
	}
}
