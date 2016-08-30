package util;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.widget.RemoteViews;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MyAccessibilityService extends AccessibilityService {
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		String msg;

		if(TextUtils.isEmpty(event.getText().toString().replace("[", "").replace("]", "").trim())) {
			msg = getText(event);
		} else {
			msg = event.getText().toString();
		}


		LogUtils.d("TEST", "message = " + msg);
	}

	@Override
	protected void onServiceConnected() {
		super.onServiceConnected();
		LogUtils.d("TEST", "service connect");
		AccessibilityServiceInfo info = new AccessibilityServiceInfo();
		info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
		info.notificationTimeout = 0;
		info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
		setServiceInfo(info);
	}

	@Override
	public void onInterrupt() {

	}

	private String getText(AccessibilityEvent event) {
		try {
			Notification notification = (Notification)event.getParcelableData();
			RemoteViews views = notification.contentView;
			Class<?> secretClass = views.getClass();

			Field outerFields[] = secretClass.getDeclaredFields();
			for(int i = 0; i < outerFields.length; i++) {
				if(!outerFields[i].getName().equals("mActions"))
					continue;

				outerFields[i].setAccessible(true);

				int count = 0;

				@SuppressWarnings("unchecked")
				ArrayList<Object> actions = (ArrayList<Object>)outerFields[i].get(views);
				for(Object action : actions) {
					Field innerFields[] = action.getClass().getDeclaredFields();

					Object value = null;
					Integer type = null;

					for(Field field : innerFields) {
						field.setAccessible(true);

						if(field.getName().equals("value"))
							value = field.get(action);
						else if(field.getName().equals("type"))
							type = field.getInt(action);
					}

					if(type != null && (type == 9 || type == 10)) {
						count++;

						if(count == 2)
							return value.toString();
					}
				}
			}
		} catch(Exception e) {
		}

		return null;
	}
}
