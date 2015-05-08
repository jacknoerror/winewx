package com.weixun.cn.ui;


import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public abstract class ContentAbstractFragment extends JackAbsFragment {

	
	protected void hideSoftKeyboard( ) {
		try{
			
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.SHOW_FORCED);
		}catch(Exception e){
		}
	}
	
}
