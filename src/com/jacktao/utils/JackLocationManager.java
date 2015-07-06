package com.jacktao.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class JackLocationManager implements LocationListener {
	
	private static JackLocationManager jlMana;
	private static Context mContext;
	private JackLocationManager(){
		lm = (LocationManager)mContext. getSystemService(Context.LOCATION_SERVICE);
		mLocListener = this;
	}
	public static JackLocationManager getInstance(Context context){
		if(null==jlMana) {
			mContext = context;
			jlMana = new JackLocationManager();
		}
		return jlMana;
	}
	
	private LocationManager lm;
	private String mProviderName;
	private LocationListener mLocListener;
	private Location mLocation;
	
	public Location getLocation(){
		return mLocation;
	}
	
	public void makeLocationUpdates(){
		if(null==mProviderName) findProviderName()	;
		if(null!=lm&&null!=mProviderName) lm.requestLocationUpdates(mProviderName, 0, 0, mLocListener);
	}

	public void removeLocationUpdates(){
		if(null!=lm) lm.removeUpdates(mLocListener);
	}
	
	
	
	private void findProviderName() {
		Criteria criteria = new Criteria();
		criteria.setCostAllowed(false); // ����λ�÷������
		criteria.setAccuracy(Criteria.ACCURACY_COARSE); // ����ˮƽλ�þ���
		// getBestProvider ֻ��������ʵ��û��λ�ù�Ӧ�̽�������
		mProviderName = lm.getBestProvider(criteria, true);
		Log.i("findProviderName", "------λ�÷���" + mProviderName);
	
	}
	@Override
	public void onLocationChanged(Location location) {
		mLocation = location;
		Log.i("onLocationChanged", mLocation.getLatitude()+"+"+mLocation.getLongitude());
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	@Override
	public void onProviderEnabled(String provider) {//not showing?
		JackUtils.showToast(mContext, "provider enable");
	}
	@Override
	public void onProviderDisabled(String provider) {//not showing?
		JackUtils.showToast(mContext, "provider disable");
	}
	
}
