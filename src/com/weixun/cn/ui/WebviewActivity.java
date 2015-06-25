package com.weixun.cn.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jacktao.utils.JackUtils;
import com.weixun.cn.Const;

public class WebviewActivity extends Activity {

	private WebView mWebview;
	private RelativeLayout mRelative;
	private EditText buttonEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWebview = new WebView(this);
		setContentView(mWebview);
		String urlExtra = getIntent().getStringExtra(Const.EXTRA_WEBVIEW_URL);
		
		// websetting
		WebSettings ws = mWebview.getSettings();
		ws.setUseWideViewPort(true);
		ws.setLoadWithOverviewMode(true);
		ws.setSupportZoom(true);
		ws.setBuiltInZoomControls(true);
		ws.setJavaScriptEnabled(true);
		ws.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		
		//js 交互
		mWebview.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					JsResult result) {
				return super.onJsAlert(view, url, message, result);
			}

		});
		
		//
		mWebview.setWebViewClient(new WebViewClient(){
				private ProgressDialog pDialog;
				@Override
				public void onReceivedError(WebView view, int errorCode,
						String description, String failingUrl) {
					super.onReceivedError(view, errorCode, description, failingUrl);
//					mWv.loadUrl("file:///android_asset/404.htm");
				}
				@Override
				public void onPageFinished(WebView view, String url) {
					Log.i("onPageFinished", url);
					if(null==mWebview) return;//0203
//					changeTitleName(view.getTitle());
					if(null!=pDialog&&pDialog.isShowing()) pDialog.dismiss();
					mWebview.setInitialScale(300);
					super.onPageFinished(view, url);
					
					
				}
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
					super.onPageStarted(view, url, favicon);
					Log.i("onPageStarted", url);

					if(null==pDialog){
						pDialog = JackUtils.showProgressDialog(WebviewActivity.this, "加载");
						pDialog.setCancelable(true);
					}
					else pDialog.show();
				}
				
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					if(url.contains("baidu")){//TODO 
						showEditText(true);
					}else{
						showEditText(false);
					}
					JackUtils.showToast(WebviewActivity.this, url);
					return false;}
				@Override
				public void onScaleChanged(WebView view, float oldScale,
						float newScale) {
					super.onScaleChanged(view, oldScale, oldScale);
				}
			});
		
//		mWebview.loadUrl("file:///android_asset/home.html");
		mWebview.loadUrl(urlExtra);
	}
	
	
	
	protected void showEditText(boolean show) {
		if(mRelative==null){
			mRelative = new RelativeLayout(this);
			setContentView(mRelative);
			mRelative.addView(mWebview);
			buttonEdit = new EditText(this);
			mRelative.addView(buttonEdit);
		}
		if(show){
			
		buttonEdit.setVisibility(View.VISIBLE);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		buttonEdit.setBackgroundResource(android.R.drawable.edit_text);
		buttonEdit.setLayoutParams(lp);
		buttonEdit.setFocusable(true);
		buttonEdit.setFocusableInTouchMode(true);
		buttonEdit.requestFocus();
		InputMethodManager inputManager =
		(InputMethodManager) buttonEdit.getContext().getSystemService(
				Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(buttonEdit, 0);
		}else{
			buttonEdit.setVisibility(View.GONE);
		}
	}
	
}
