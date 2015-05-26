package com.weixun.cn.ui;

import com.weixun.cn.Const;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebviewActivity extends Activity {

	private WebView mWebview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWebview = new WebView(this);
		setContentView(mWebview);
		String urlExtra = getIntent().getStringExtra(Const.EXTRA_WEBVIEW_URL);
		
		
		
//		mWebview.loadUrl("file:///android_asset/home.html");
		mWebview.loadUrl(urlExtra);
	}
	
}
