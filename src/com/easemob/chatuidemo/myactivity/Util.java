package com.easemob.chatuidemo.myactivity;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
/**
 * @author yangyu
 *	功能描述：常量工具类
 */
public class Util {
	/**
	 * 得到设备屏幕的宽度
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 得到设备屏幕的高度
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 得到设备的密度
	 */
	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 把密度转换为像素px 像素 ，dp屏幕的密度
	 */
	public static int dip2px(Context context, float dp) {
		final float scale = getScreenDensity(context);
		return (int) (dp * scale + 0.5);
	}
	
	/**
	 * 把像素转换为密度
	 * 
	 */

public int Px2Dp(Context context, float px) { 
    final float scale = context.getResources().getDisplayMetrics().density; 
    return (int) (px / scale + 0.5f); 
} 







//转换dip为px 
	public static int convertDipOrPx(Context context, int dip) { 
	    float scale = context.getResources().getDisplayMetrics().density; 
	    return (int)(dip*scale + 0.5f*(dip>=0?1:-1)); 
	} 
	 
	//转换px为dip
	public static int convertPxOrDip(Context context, int px) { 
	    float scale = context.getResources().getDisplayMetrics().density; 
	    return (int)(px/scale + 0.5f*(px>=0?1:-1)); 
	} 

}
