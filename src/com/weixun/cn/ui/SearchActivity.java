package com.weixun.cn.ui;

import com.jacktao.utils.JackWindowTitleManager;
import com.weixun.cn.R;
import com.weixun.cn.util.WxUtils;

import android.app.Activity;
import android.os.Bundle;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search);
		WxUtils.simpleTitle(new JackWindowTitleManager(this), "搜索");
		
		
	}
	
}
