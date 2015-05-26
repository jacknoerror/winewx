package com.weixun.cn.ui.tabs;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;

import com.weixun.cn.R;
import com.weixun.cn.ui.ContentAbstractFragment;
import com.weixun.cn.ui.MyPortal;
import com.weixun.cn.ui.WebviewActivity;

public class TabDiscover extends ContentAbstractFragment implements OnClickListener {

	@Override
	public int getLayoutRid() {
		return R.layout.fragment_discover;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
		mView.findViewById(R.id.layout_find1).setOnClickListener(this);
		mView.findViewById(R.id.layout_find2).setOnClickListener(this);
		mView.findViewById(R.id.layout_find3).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//TODO
		switch (v.getId()) {
		case R.id.layout_find1:
			
			break;
		case R.id.layout_find2:
			MyPortal.justGo(getActivity(), LCLStartActivity.class);
			break;
		case R.id.layout_find3://葡萄酒学院
			MyPortal.goWebView(getActivity(), "http://www.baidu.com");//
			break;

		default:
			break;
		}
		
	}

}
