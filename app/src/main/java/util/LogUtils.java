package util;

import android.os.Environment;
import android.util.Log;

import com.kiy.app.test_project.BuildConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LogWriter는 기본적으로 안드로이드의 Log클래스를 이용한다.
 * 이와 별도로 e, i, w 함수의 경우 파일에 쓰며 파일에 쓰길 원하지 않는다면 d 함수를 사용하면 된다.
 *
 */
public class LogUtils {
	private static final String TAG = "test_project";
	
	private LogUtils() {}
	
	private static String preLogText(){
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		StackTraceElement beforeStack = stacks[2];
		String beforeStackClassName = beforeStack.getClassName();
		String beforeStackMethodName = beforeStack.getMethodName();
		
		if(beforeStackClassName.startsWith("com.CouponChart.network.VolleyCoocha")){
			beforeStack = stacks[3];
			beforeStackClassName = beforeStack.getClassName();
			beforeStackMethodName = beforeStack.getMethodName();
		}
		
		return beforeStackClassName+" "+beforeStackMethodName+" ";
	}
	
	/**
	 * Write a verbose log. Dosen't write a log on the file.
	 * @param message
	 */
	public static void v(String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.v(TAG, preLogText() + message);
			
		}
	}
	
	/**
	 * Write a info log. LogWriter write a log on the file.
	 * @param path
	 * @param message
	 */
	public static void i(String path , String message){
		if(BuildConfig.DEBUG) {
//			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.i(TAG, preLogText()+message);
			writeLog(path , message);
		}
	}
	
	/**
	 * Write a info log. LogWriter write a log on the file.
	 * @param message
	 */
	public static void i(String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.i(TAG, preLogText()+message);
			writeLog(message);
		}
	}
	
	/**
	 * Write a debug log. LogWriter doesn't write a log on the file.
	 * @param message
	 */
	public static void d(String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.d(TAG, preLogText() + message);
		}
	}
	
	
	/**
	 * Write a warning log. LogWriter write a log on the file.
	 * @param message
	 */
	public static void w(String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.w(TAG, preLogText()+message);
			writeLog(message);
		}
	}

	/**
	 * Write a error log. LogWriter write a log on the file.
	 */
	public static void e(Throwable e) {
		e(getStringFromThrowable(e));
	}
	
	/**
	 * Write a error log. LogWriter write a log on the file.
	 * @param message
	 */
	public static void e(String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.e(TAG, preLogText()+message);
			writeLog(message);
		}
	}
	
	/**
	 * Write a log on the log file.
	 * @param message
	 */
	static void writeLog(String message) {
		File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), TAG);
		if(!root.canWrite())root.mkdirs();
		if (root.canWrite()) {
			File logFile = new File(root, TAG + new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ ".log");
			
			writeTextMessage(message, logFile, true);
		}
	}
	
	/**
	 * Write a log on the log file.
	 * @param path
	 * @param message
	 */
	static void writeLog(String path , String message) {
		File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), TAG);
		if(!root.canWrite())root.mkdirs();
		if (root.canWrite()) {
			File logFile = new File(root, path + ".txt");
			
			writeTextMessage(message, logFile, true);
		}
	}

	public static String getStringFromThrowable(Throwable e) {
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    
	    return sw.toString();
	}
	
	static String writeTextMessage(String message, File file, boolean isAppend) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file, isAppend);
			fos.write((message + "\n").getBytes());
			fos.flush();
		} catch (IOException ioe) {
			Log.e(TAG, "Could not write file " + ioe.getMessage());
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "file://" + file.toString();
	}
	
	/**
     * Save log, when uncaughtExceptionHandler happen. 
     */
//    public static void saveCrashlog(Throwable ex) {
//    	String cuurentTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(System.currentTimeMillis());
//		String mFileName = "log"+"("+cuurentTime+")"+".txt";
//
//		String errorLog = getStringFromThrowable(ex);
//		File mSDCard = new File(CooChaConstants.COOCHA_LOG_PATH);
//		if (!mSDCard.exists()) mSDCard.mkdir();
//		File mSDCardLog = new File(mSDCard, mFileName);
//
//		if (mSDCardLog.exists()){
//			mSDCardLog.delete();
//			mSDCardLog = new File(mSDCard, mFileName);
//		}
//
//		FileOutputStream outputStream = null;
//		try {
//			outputStream = new FileOutputStream(mSDCardLog);
//			outputStream.write(errorLog.getBytes());
//			outputStream.flush();
//		} catch (FileNotFoundException e) {
//			LogUtils.e(e);
//		} catch (IOException e) {
//			LogUtils.e(e);
//		}finally {
//			if(outputStream != null) {
//				try {
//					outputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		GlobalPreference.getInstance().edit().putString(GlobalPreference.PREF_CRASH_LOG, mFileName).commit();
//    }

	/**
	 * Write a verbose log. Dosen't write a log on the file.
	 * @param message
	 */
	public static void v(String tag , String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.v(tag, preLogText() + message);

		}
	}

	/**
	 * Write a info log. LogWriter write a log on the file.
	 * @param message
	 */
	public static void logi(String tag, String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.i(tag, preLogText()+message);
		}
	}

	/**
	 * Write a debug log. LogWriter doesn't write a log on the file.
	 * @param message
	 */
	public static void d(String tag , String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.d(tag, preLogText() + message);
		}
	}


	/**
	 * Write a warning log. LogWriter write a log on the file.
	 * @param message
	 */
	public static void w(String tag , String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.w(tag, preLogText()+message);
			writeLog(message);
		}
	}

	/**
	 * Write a error log. LogWriter write a log on the file.
	 * @param message
	 */
	public static void e(String tag , String message) {
		if(BuildConfig.DEBUG) {
			message = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\t[" + Thread.currentThread().getId() + "] " + message;
			Log.e(tag, preLogText()+message);
			writeLog(message);
		}
	}
}
