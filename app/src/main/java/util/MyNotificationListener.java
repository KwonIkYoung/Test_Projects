package util;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyNotificationListener extends NotificationListenerService {
	@Override
	public void onCreate() {
		// 디바이스 설정 -> 보안 -> 알림에서 해당 앱을 체크
		// 여기서는 NLservice가 시작하였을때 사용자가 원하는 동작을 정의
		LogUtils.i("TEST" , "NotificationListenerService Oncreate");

	}

	@Override
	public void onNotificationPosted(StatusBarNotification sbn) {
		// Notification 추가시 Callback
		// Notifiacation이 왔을때 사용자가 원하는 동작을 정의

		LogUtils.i("TEST","onNotificationPosted : " + sbn.getPackageName() + "");
		LogUtils.i("TEST","onNotificationPosted : " + sbn.getNotification() + "");
		LogUtils.i("TEST","onNotificationPosted : " + sbn.getTag() + "");
	}

	@Override
	public void onNotificationRemoved(StatusBarNotification sbn) {
		// Notification 제거시 Callback 메소드가 호출된다.
		// Notification 제거시 사용자가 원하는 동작을 정의

	}

	@Override
	public StatusBarNotification[] getActiveNotifications() {
		return super.getActiveNotifications();

	}
}
