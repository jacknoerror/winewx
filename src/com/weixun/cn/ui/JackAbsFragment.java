package com.weixun.cn.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class JackAbsFragment extends Fragment {
	protected final String TAG 	 = getClass().getSimpleName();
	
	protected LayoutInflater mInflater;
	protected View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mInflater= inflater;
//		if(!inflate()) return null;
		inflate(container);// 
		initView( );
		return mView;
	}

	private boolean inflate(ViewGroup container){
		int rid = getLayoutRid();
		if(rid>0){
			mView =  mInflater.inflate(getLayoutRid(), container,false);
		}
		return rid>0;
	}
	
	public abstract int getLayoutRid() ;

	/**
	 */
	public abstract void initView() ;
}
