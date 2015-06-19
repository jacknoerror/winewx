package com.weixun.cn.ui.login;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.activity.RegisterActivity;
import com.easemob.exceptions.EaseMobException;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NumberRule;
import com.mobsandgeeks.saripaar.annotation.NumberRule.NumberType;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.weixun.cn.R;
import com.weixun.cn.util.MyJsonRequest;
import com.weixun.cn.util.WxUtils;

public class RegActivity extends Activity implements ValidationListener{
	
	@Required(order = 1 , message="微醺号不能为空！")
	private EditText weixun_num;
	@Required(order = 2,message="昵称不能为空！")
	private EditText nick_name;
	@Required(order=3,message="不能为空！")
	@Password(order=4)
	@TextRule(order=5,minLength=6,message="密码不足6位！")
	private EditText pwd;
	@ConfirmPassword(order =6,messageResId=R.string.Two_input_password)
	private EditText repwd;
	@Required(order=7,message="不能为空！")
	@Regex(order=8,pattern="^[1][3,4,5,7,8][0-9]{9}$" ,message="请输入正确的手机号！")
	private EditText username;
	@Required(order=3,message="验证码不能为空！")
	private EditText yzm;
	private Validator va ;
	private RequestQueue mRequestQueue; 
	private SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_register);
		weixun_num = (EditText)findViewById(R.id.edit_weixunnum);
		nick_name = (EditText)findViewById(R.id.index_nickname_edit);
		pwd = (EditText)findViewById(R.id.index_regpwd_edit);
		repwd = (EditText)findViewById(R.id.index_reregpwd_edit);
		username = (EditText)findViewById(R.id.index_reguser_edit);
		yzm = (EditText)findViewById(R.id.index_yzm_edit);
		 va = new Validator(this);
	    va.setValidationListener(this);
	    mRequestQueue =  Volley.newRequestQueue(this); //请求队列
	    preferences = getSharedPreferences("user", MODE_PRIVATE);
	}
	
	public void sendyzm(View v){
		ProgressDialog pdg = new ProgressDialog(this);
		 String _mobile = username.getText().toString().trim();
		 if(WxUtils.checkRE("^[1][3,4,5,7,8][0-9]{9}$", username, "请输入正确的手机号！")){
			 HashMap<String, String> params = new HashMap<String, String>();
				params.put("mobile", _mobile);
				params.put("type", "register");
				MyJsonRequest myjsonrequest = new MyJsonRequest("app/userInfo/sendSmsCode", params, 
						new Response.Listener<JSONObject>(){
							@Override
							public void onResponse(JSONObject response) {
								try {
									preferences.edit().putString("appToken", response.getString("Result"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// TODO Auto-generated method stub								
							}					
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(getApplicationContext(), "获取验证码失败！" ,
										Toast.LENGTH_SHORT).show();
							}
						});
					}
				});
				
				mRequestQueue.add(myjsonrequest); 
			 
		 }
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	//注册
	public void reg(View v){
		va.validate();
//		va.validateAsync();
		
				
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void preValidation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess() {
		String st5 = getResources().getString(R.string.Is_the_registered);
		final String st6 = getResources().getString(R.string.Registered_successfully);
		final String weixunNum = weixun_num.getText().toString().trim();
		final String nickName = nick_name.getText().toString().trim();
		final String yzmString = yzm.getText().toString().trim();
		final String mobile = username.getText().toString().trim();
		final String password = pwd.getText().toString().trim();
		String confirm_pwd = repwd.getText().toString().trim();
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setMessage(st5);
		pd.show();
		
		// TODO Auto-generated method stub
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("account", weixunNum);
		params.put("password", password);
		params.put("type", "register");	
		params.put("mobile", mobile);
		params.put("nickname", nickName);
		params.put("yzm", yzmString);
		final String st7 = getResources().getString(R.string.network_anomalies);
		final String st8 = getResources().getString(R.string.User_already_exists);
		final String st9 = getResources().getString(R.string.registration_failed_without_permission);
		final String st10 = getResources().getString(R.string.Registration_failed);
		MyJsonRequest myjsonrequest = new MyJsonRequest("app/userInfo/register", params, 
				new Response.Listener<JSONObject>(){
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
				final String hxid = "h2";		
						new Thread(new Runnable() {
							public void run() {
								try {
									EMChatManager.getInstance().createAccountOnServer(hxid, password);
									// 调用sdk注册方法
									runOnUiThread(new Runnable() {
										public void run() {
											if (!RegActivity.this.isFinishing())
												pd.dismiss();
											// 保存用户名
											DemoApplication.getInstance().setUserName(hxid);
											Toast.makeText(getApplicationContext(), st6, 0).show();
											finish();
										}
									});
								} catch (final EaseMobException e) {
									runOnUiThread(new Runnable() {
										public void run() {
											if (!RegActivity.this.isFinishing())
												pd.dismiss();
											int errorCode=e.getErrorCode();
											if(errorCode==EMError.NONETWORK_ERROR){
												Toast.makeText(getApplicationContext(), st7, Toast.LENGTH_SHORT).show();
											}else if(errorCode==EMError.USER_ALREADY_EXISTS){
												Toast.makeText(getApplicationContext(), st8, Toast.LENGTH_SHORT).show();
											}else if(errorCode==EMError.UNAUTHORIZED){
												Toast.makeText(getApplicationContext(), st9, Toast.LENGTH_SHORT).show();
											}else{
												Toast.makeText(getApplicationContext(), st10 + e.getMessage(), Toast.LENGTH_SHORT).show();
											}
										}
									});
								}
							}
						}).start();
						
					}
			
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(getApplicationContext(), "注册失败！" ,
									Toast.LENGTH_SHORT).show();
						}
					});
				
				
			}
		});
		
		mRequestQueue.add(myjsonrequest);
		
		
	}

	@Override
	public void onFailure(View failedView, Rule<?> failedRule) {
		// TODO Auto-generated method stub
		String message = failedRule.getFailureMessage();
		 
        if (failedView instanceof EditText) {
            failedView.requestFocus();
            ((EditText) failedView).setError(message);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }		
	}

	@Override
	public void onValidationCancelled() {
		// TODO Auto-generated method stub
		
	}

}
