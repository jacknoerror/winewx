package com.weixun.cn.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.weixun.cn.R;
import com.weixun.cn.ui.HubActivity;

public class LoginActivity extends Activity{
	
	EditText username;
	EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_login);
	}
	//登录
	public void login(View v){
		//获取了登录的用户名密码   先去自己服务器登录   登录成功以后再去环信服务器登录！
		username = (EditText)findViewById(R.id.index_user);
		password =(EditText)findViewById(R.id.index_pwd);
		startActivity(new Intent(this,HubActivity.class));
	}
	
	//注册
	public void register(View v){
		startActivity(new Intent(this,RegActivity.class));
	}
	
	
	//忘记密码
	public void forget(View v){
		startActivity(new Intent(this,ForgetActivity.class));
	}
	
	//微信登录
	public void weixinLogin(View v){
		
	}
	
	//qq登录
	public void qqLogin(View v){
		
	}

}
