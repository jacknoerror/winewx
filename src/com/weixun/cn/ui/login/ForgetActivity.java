package com.weixun.cn.ui.login;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.weixun.cn.R;
import com.weixun.cn.util.MyJsonRequest;
import com.weixun.cn.util.WxUtils;

public class ForgetActivity extends Activity implements ValidationListener {
	@Required(order = 1, message = "不能为空！")
	@Regex(order = 2, pattern = "^[1][3,4,5,7,8][0-9]{9}$", message = "请输入正确的手机号！")
	private EditText username;

	@Required(order = 3, message = "不能为空！")
	@Password(order = 4)
	@TextRule(order = 5, minLength = 6, message = "密码不足6位！")
	private EditText pwd;

	@ConfirmPassword(order = 6, messageResId = R.string.Two_input_password)
	private EditText repwd;

	@Required(order = 7, message = "不能为空！")
	private EditText yzm;
	private Validator va;
	private RequestQueue mRequestQueue;
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_forget_password);
		username = (EditText) findViewById(R.id.index_user_edit);
		pwd = (EditText) findViewById(R.id.index_pwd_edit);
		repwd = (EditText) findViewById(R.id.index_repwd_edit);
		yzm = (EditText) findViewById(R.id.index_yzm_edit);

		va = new Validator(this);
		va.setValidationListener(this);
		mRequestQueue = Volley.newRequestQueue(this); // 请求队列
		preferences = getSharedPreferences("user", MODE_PRIVATE);

	}

	public void editPwd(View v) {
		// 提交表单
		va.validate();

	}

	public void sendyzm(View v) {
		String _mobile = username.getText().toString().trim();
		if (WxUtils.checkRE("^[1][3,4,5,7,8][0-9]{9}$", username, "请输入正确的手机号！")) {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("mobile", _mobile);
			params.put("type", "findPwd");
			MyJsonRequest myjsonrequest = new MyJsonRequest(
					"app/userInfo/sendSmsCode", params,
					new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							try {
								preferences.edit().putString("appToken",
										response.getString("Result"));
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
									Toast.makeText(getApplicationContext(),
											"获取验证码失败！", Toast.LENGTH_SHORT)
											.show();
								}
							});
						}
					});

			mRequestQueue.add(myjsonrequest);
		}

	}

	@Override
	public void preValidation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess() {
		String mobile = username.getText().toString().trim();
		String yzmString = yzm.getText().toString().trim();
		String password = pwd.getText().toString().trim();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mobile", mobile);
		params.put("password", password);
		params.put("yzm", yzmString);
		MyJsonRequest myjsonrequest = new MyJsonRequest("app/userInfo/findPwd",
				params, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						new android.app.AlertDialog.Builder(
								getApplicationContext()).setTitle("提示")
								.setMessage("密码修改成功！")
								.setPositiveButton("确定", new OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										startActivity(new Intent(
												ForgetActivity.this,
												LoginActivity.class));
										finish();
									}
								}).show();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(getApplicationContext(),
										"找回密码失败！", Toast.LENGTH_SHORT).show();
							}
						});
					}
				});

		mRequestQueue.add(myjsonrequest);
	}

	@Override
	public void onFailure(View failedView, Rule<?> failedRule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onValidationCancelled() {
		// TODO Auto-generated method stub

	}

}
