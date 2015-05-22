package com.weixun.cn.ui.tabs;

import android.content.Intent;

import com.weixun.cn.ui.ContentAbstractFragment;
import com.weixun.cn.ui.WebviewActivity;

public class TabDiscover extends ContentAbstractFragment {

	@Override
	public int getLayoutRid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
		startActivity(new Intent(getActivity(), WebviewActivity.class));
	}

}
