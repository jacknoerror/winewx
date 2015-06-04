package com.weixun.cn.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.weixun.cn.R;

public class RegActivity extends Activity{
	EditText weixun_num;
	EditText nick_name;
	EditText pwd;
	EditText repwd;
	EditText username;
	EditText yzm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_register);
	}
	
	//注册
	public void reg(View v){
		weixun_num = (EditText)findViewById(R.id.edit_weixunnum);
		nick_name = (EditText)findViewById(R.id.index_nickname_edit);
		pwd = (EditText)findViewById(R.id.index_regpwd_edit);
		repwd = (EditText)findViewById(R.id.index_reregpwd_edit);
		username = (EditText)findViewById(R.id.index_reguser_edit);
		yzm = (EditText)findViewById(R.id.index_yzm_edit);
	}

}
