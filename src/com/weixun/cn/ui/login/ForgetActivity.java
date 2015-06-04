package com.weixun.cn.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.weixun.cn.R;

public class ForgetActivity extends Activity{
	EditText username;
	EditText pwd;
	EditText repwd;
	EditText yzm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_forget_password);
	}
	
	
	public void editPwd(View v){
		//提交表单
		username = (EditText)findViewById(R.id.index_user_edit);
		pwd = (EditText)findViewById(R.id.index_pwd_edit);
		repwd = (EditText)findViewById(R.id.index_repwd_edit);
		yzm = (EditText)findViewById(R.id.index_yzm_edit);
	}

}
