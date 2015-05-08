package com.weixun.cn;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class MyApplication extends Application{

	static MyApplication app;
	public static MyApplication app()	{
		return app;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		app = this;

		initScreenData();
		initManas();

		// initActivityLcCallback();
	}
	
	
	/**
	 * 
	 */
	private String mDeviceId;
	public ConnectivityManager manaConnectivity;
	public void initManas() {
		mDeviceId = ((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		manaConnectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	
	
	private void initScreenData(){
		DisplayMetrics dm = getResources().getDisplayMetrics();
		Const.SCREEN_WIDTH = dm.widthPixels;
		Const.SCREEN_HEIGHT= dm.heightPixels;
//		Log.i("yft_app", dm.densityDpi+":dpi+=+desi:"+dm.density);
	}
	
	
	public SharedPreferences getSP(String name){
		return getSharedPreferences(name, Context.MODE_PRIVATE);
	}
	public String getDeviceId(){
		return mDeviceId	;
	}
	
	
}
