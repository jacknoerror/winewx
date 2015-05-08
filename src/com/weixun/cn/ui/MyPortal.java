package com.weixun.cn.ui;

import android.content.Context;
import android.content.Intent;

public class MyPortal {
	@SuppressWarnings("rawtypes")
	public static void justGo(Context context, Class clazz) {
		Intent intent = new Intent();
		intent.setClass(context, clazz);
		context.startActivity(intent);
	}
	
	
}
