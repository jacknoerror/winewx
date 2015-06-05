package com.weixun.cn.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.weixun.cn.R;
import com.weixun.cn.ui.MyPortal;
import com.weixun.cn.ui.tabs.stores.StoreInActivity;
import com.weixun.cn.ui.tabs.stores.StoreOutActivity;

public class MyShareDialog extends Dialog {
//	final int[] SHAREICONID = new int[]{R.drawable.share_wechat,R.drawable.share_pengyou,R.drawable.share_weibo};
	final String[] SHAREICONNAME = new String[]{"微信","朋友圈","微博"};
	Context context;
	private android.view.View.OnClickListener itemListener;
	
	public MyShareDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//获得当前窗体
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.merge_share);
		window .setGravity(Gravity.BOTTOM);
		window.setWindowAnimations(R.style.mystyle);
		window.setBackgroundDrawable(context.getResources().getDrawable(android.R.color.transparent));
		initShareLayout();
	}
	
	private void initShareLayout() {
		Button btn_dialog_instore,btn_dialog_outstore,btn_dialog_cancel;
		btn_dialog_instore = (Button) findViewById(R.id.btn_dialog_instore);
		btn_dialog_outstore = (Button) findViewById(R.id.btn_dialog_outstore);
		btn_dialog_cancel = (Button) findViewById(R.id.btn_dialog_cancel);
		View.OnClickListener onClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_dialog_instore:
					MyPortal.justGo(context, StoreInActivity.class);
					break;
				case R.id.btn_dialog_outstore:
					MyPortal.justGo(context, StoreOutActivity.class);
					break;
				case R.id.btn_dialog_cancel:
					break;

				default:
					break;
				}
				dismiss();
			}
		};
		btn_dialog_cancel.setOnClickListener(onClickListener);
		btn_dialog_outstore.setOnClickListener(onClickListener);
		btn_dialog_instore.setOnClickListener(onClickListener);
	}

	public final void setItemListener(android.view.View.OnClickListener itemListener) {
		this.itemListener = itemListener;
	}
	
	
}
