package com.weixun.cn.ui;

import com.weixun.cn.Const;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

public class MyPortal {
	@SuppressWarnings("rawtypes")
	public static void justGo(Context context, Class clazz) {
		Intent intent = new Intent();
		intent.setClass(context, clazz);
		context.startActivity(intent);
	}

	public static void goImages(Activity activity) {
		Intent intent = new Intent();
		/* 开启Pictures画面Type设定为image */
		intent.setType("image/*");
		/* 使用Intent.ACTION_GET_CONTENT这个Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);
		/* 取得相片后返回本画面 */
		activity.startActivityForResult(intent, Const.AR_LOCALPHOTO);
		
	}

	public static void goWebView(Activity activity, String url) {
		Intent intent = new Intent();
		intent.putExtra(Const.EXTRA_WEBVIEW_URL, url);
		intent.setClass(activity, WebviewActivity.class);
		activity.startActivity(intent);
	}
	
	
}
