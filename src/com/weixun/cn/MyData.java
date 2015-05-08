package com.weixun.cn;

import android.widget.TabHost;

/**
 * @author TaoTao
 *
 */
public class MyData {
	
	static MyData data;
	private MyData(){};
	public static MyData data(){
		if(null==data) data=new MyData();
		return data;
	}
	
	TabHost mTabHost;
	public void setTabHost(TabHost tabHost){
		this.mTabHost = tabHost;
	}
	public TabHost getTabHost(){
		return mTabHost;
	}
	
}
