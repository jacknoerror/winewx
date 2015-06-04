package com.weixun.cn.ui.tabs;

import com.jacktao.utils.JackWindowTitleManager;
import com.weixun.cn.R;
import com.weixun.cn.util.WxUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ActDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actidetail);
		
		WxUtils.simpleTitle(new JackWindowTitleManager(this), "活动详情");//
		
		initLayout1();
		
	}

	private void initLayout1() {
		// TODO Auto-generated method stub
		View aLayout1 = findViewById(R.id.layout_actd1);
		//TODO 判断是否添加第二部分
		initLayout2();
	}

	private void initLayout2() {
		// TODO Auto-generated method stub
		View aLayout2 = findViewById(R.id.layout_actd2);
		
		//判断初始化第三部分
		initLayout3();
	}

	private void initLayout3() {
		// TODO Auto-generated method stub
		View aLayout3 = findViewById(R.id.layout_actd3);
		View btn_comment = findViewById(R.id.btn_ad_comment);
	}
	
}
