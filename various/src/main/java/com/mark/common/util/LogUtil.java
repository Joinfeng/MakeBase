package com.mark.common.util;

import android.util.Log;

/**
 * 
 * @ClassName: LogUtil
 * @Description: 打印log日志
 * @author: zxm1243
 * @date: 2016-9-9 上午10:27:28
 */
public class LogUtil {

	private LogUtil(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	static String DEFAULTTAG = "LogUtil";
	static boolean DEBUG = true;

	public static void v(String msg) {
		if (DEBUG)
			Log.v(DEFAULTTAG, msg);
	}

	public static void i(String msg) {
		if (DEBUG)
			Log.i(DEFAULTTAG, msg);
	}

	public static void e(String msg) {
		if (DEBUG)
			Log.e(DEFAULTTAG, msg);
	}

	public static void w(String msg) {
		if (DEBUG)
			Log.w(DEFAULTTAG, msg);
	}

	 public static void v(String tag, String msg) {
        if (DEBUG)
            Log.v(tag, msg);
    }
    public static void v(String tag, String msg,Throwable tr) {
        if (DEBUG)
            Log.v(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, msg);
    }
    public static void i(String tag, String msg,Throwable tr) {
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }
    public static void e(String tag, String msg,Throwable tr) {
        if (DEBUG)
            Log.e(tag, msg);
    }



    public static void w(String tag, String msg) {
        if (DEBUG)
            Log.w(tag, msg);
    }
    public static void w(String tag, String msg,Throwable tr) {
        if (DEBUG)
            Log.w(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg);
    }
    public static void d(String tag, String msg,Throwable tr) {
        if (DEBUG)
            Log.d(tag, msg);
    }

	/**
	 * 功能:当数据太多,系统自带的log打印时控制台显示不完,可以使用这个方法来打印在控制台上,显示全部信息
	 * 
	 * @param response
	 * @param tag
	 * @param x
	 */
	public static void longLog(String response, String tag, int x) {
		int len = response.length();
		int j = len % x;
		int k = len / x;
		if (j != 0) {
			j += 1;
		}
		for (int i = 0; i < x; i++) {
			if (j != 0 && i == x - 1) {
				LogUtil.e(tag + i, response.substring(k * i, len));
				break;
			}
			LogUtil.e(tag + i, response.substring(k * i, k * (i + 1)));
		}
	}

}
