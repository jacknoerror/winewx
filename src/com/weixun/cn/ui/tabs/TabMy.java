package com.weixun.cn.ui.tabs;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.weixun.cn.R;
import com.weixun.cn.ui.ContentAbstractFragment;
import com.weixun.cn.ui.MyPortal;
import com.weixun.cn.ui.RadioGroupListActivity;

public class TabMy extends ContentAbstractFragment implements OnClickListener{
	@Override
	public int getLayoutRid() {
		return R.layout.fragment_my;
	}

	@Override
	public void initView() {
		mView.findViewById(R.id.layout_my_my).setOnClickListener(this);
		mView.findViewById(R.id.tv_my_contact).setOnClickListener(this);
		mView.findViewById(R.id.tv_my_myact).setOnClickListener(this);
		mView.findViewById(R.id.tv_my_mycab).setOnClickListener(this);
		mView.findViewById(R.id.tv_my_mylcl).setOnClickListener(this);
		mView.findViewById(R.id.tv_my_mywx).setOnClickListener(this);
		mView.findViewById(R.id.tv_my_setting).setOnClickListener(this);
		
		
		((TextView) mView.findViewById(R.id.tv_my_name)).setText("红酒酒");//
		((TextView) mView.findViewById(R.id.tv_my_wxno)).setText("我的微醺号：12306");//
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_my_contact://通讯录
			//用环信的通讯录	TODO
			
			FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
			transaction.addToBackStack("tabMy").hide(this)
			.show(getActivity().getSupportFragmentManager().findFragmentByTag("contactListFragment"))
			.commit();
			getActivity().findViewById(R.id.main_bottom).setVisibility(View.GONE);
			// 显示通讯录
			
			break;
		case R.id.tv_my_myact://我的活动nd
			MyPortal.justGo(getActivity(), RadioGroupListActivity.class);
			
			break;
		case R.id.tv_my_setting://设置
			 //设置
			getActivity().findViewById(R.id.main_bottom).setVisibility(View.GONE);
			FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
			transaction1.addToBackStack("tabMy").hide(this)		
			.show(getActivity().getSupportFragmentManager().findFragmentByTag("settingFragment"))
			.commit();
			
			break;
			
			
		case R.id.tv_my_mylcl://我的拼箱
			MyPortal.goRadioActivity(getActivity(),1);
			break;
		case R.id.layout_my_my://个人设置
			MyPortal.justGo(getActivity(), MyInfoActivity.class);
			break;
			
		default:
			break;
		}
	}


}
