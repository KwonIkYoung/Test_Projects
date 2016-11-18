package util;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

@SuppressLint({"NewApi"})
public class NotifiListenerService extends NotificationListenerService {
	public NotifiListenerService mNotificationService;
	private StatusBarNotification mNotification;

	public NotifiListenerService() {
		mNotificationService = this;
	}
	@SuppressLint({"NewApi"})
	@Override
	public void onNotificationPosted(StatusBarNotification sbn) {
//		super.onNotificationPosted(sbn);
		mNotification = sbn;

		if (mNotification != null) {
			Notification notification = mNotification.getNotification();
			StringBuilder msg = new StringBuilder();
			msg.append("\n");
			msg.append("=========== onNotificationPosted ===========" );
			msg.append("\n");
			msg.append("Noti ID : " + sbn.getId() );
			msg.append("\n");
			msg.append("Noti TickerText : " + notification.tickerText );
			msg.append("\n");
			msg.append( "Noti PackageName : " + sbn.getPackageName());
			msg.append("\n");
			msg.append( "Noti PostTime : " + sbn.getPostTime());
			msg.append("\n");
			msg.append("Noti TAG : " + sbn.getTag());
			msg.append("\n");
			msg.append("Noti Title : " + notification.extras.getString(Notification.EXTRA_TITLE) );
			msg.append("\n");
			msg.append("Noti Text : " + notification.extras.getString(Notification.EXTRA_TEXT));
			msg.append("\n");
			msg.append("Noti Big Titlt : " + notification.extras.getString(Notification.EXTRA_BIG_TEXT));
			msg.append("\n");
			msg.append("Noti Big Text : " + notification.extras.getString(Notification.EXTRA_BIG_TEXT));
			msg.append("\n");
			msg.append("Noti describeContents : " + mNotification.describeContents());
			msg.append("\n");
			msg.append("Noti : fullScreenIntent " + sbn.getNotification().fullScreenIntent);
			msg.append("\n");
			msg.append("Noti : contentIntent " + sbn.getNotification().contentIntent);
			msg.append("\n");
			LogUtils.d("KIY", "Noti  : " + msg.toString());

			Bundle bundle = new Bundle();
			bundle.putString("1",msg.toString() );
			sendReceiver(bundle,  sbn.getNotification().contentIntent);
		}
	}

	@Override
	public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
		super.onNotificationPosted(sbn, rankingMap);
	}

	@Override
	public void onNotificationRemoved(StatusBarNotification sbn) {
//		super.onNotificationRemoved(sbn);

		Notification notification = mNotification.getNotification();
		StringBuilder msg = new StringBuilder();
		msg.append("\n");
		msg.append("=========== onNotificationRemoved ===========" );
		msg.append("\n");
		msg.append("Noti ID : " + sbn.getId() );
		msg.append("\n");
		msg.append("Noti TickerText : " + notification.tickerText );
		msg.append("\n");
		msg.append( "Noti PackageName : " + sbn.getPackageName());
		msg.append("\n");
		msg.append( "Noti PostTime : " + sbn.getPostTime());
		msg.append("\n");
		msg.append("Noti TAG : " + sbn.getTag());
		msg.append("\n");
		msg.append("Noti Title : " + notification.extras.getString(Notification.EXTRA_TITLE) );
		msg.append("\n");
		msg.append("Noti Text : " + notification.extras.getString(Notification.EXTRA_TEXT));
		msg.append("\n");
		msg.append("Noti Big Titlt : " + notification.extras.getString(Notification.EXTRA_BIG_TEXT));
		msg.append("\n");
		msg.append("Noti Big Text : " + notification.extras.getString(Notification.EXTRA_BIG_TEXT));
		msg.append("\n");
		LogUtils.d("KIY", "Noti  : " + msg.toString());

		Bundle bundle = new Bundle();
		bundle.putString("1",msg.toString());
		sendReceiver(bundle);

	}

	@Override
	public void onNotificationRemoved(StatusBarNotification sbn, RankingMap rankingMap) {
		super.onNotificationRemoved(sbn, rankingMap);
	}

	@Override
	public void onListenerConnected() {
		super.onListenerConnected();
	}

	@Override
	public void onNotificationRankingUpdate(RankingMap rankingMap) {
		super.onNotificationRankingUpdate(rankingMap);
	}

	@Override
	public void onListenerHintsChanged(int hints) {
		super.onListenerHintsChanged(hints);
	}

	@Override
	public void onInterruptionFilterChanged(int interruptionFilter) {
		super.onInterruptionFilterChanged(interruptionFilter);
	}

	@Override
	public StatusBarNotification[] getActiveNotifications() {
		return super.getActiveNotifications();
	}

	@Override
	public StatusBarNotification[] getActiveNotifications(String[] keys) {
		return super.getActiveNotifications(keys);
	}

	@Override
	public RankingMap getCurrentRanking() {
		return super.getCurrentRanking();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return super.onBind(intent);
	}

	private void sendReceiver(Bundle bundle, PendingIntent pendingIntent) {
		Intent intent = new Intent("com.kiy_testproject.noti");
		intent.putExtra("bundle",bundle);
		intent.putExtra("pending",pendingIntent);
		sendBroadcast(intent);
	}
	private void sendReceiver(Bundle bundle) {
		Intent intent = new Intent("com.kiy_testproject.noti");
		intent.putExtra("bundle",bundle);
		sendBroadcast(intent);
	}
}
