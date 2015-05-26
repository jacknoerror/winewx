package com.weixun.cn.ui.tabs;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jacktao.utils.JackWindowTitleManager;
import com.jacktao.utils.JackWindowTitleManager.JackTitleConst;
import com.weixun.cn.R;

/**
 * 拼箱发布
 * @author TaoTao
 */
public class LCLStartActivity extends Activity {
	EditText edit_lcls_name,edit_lcls_upsum,edit_lcls_upone,edit_lcls_price,edit_lcls_remark;
	Button btn_lcls_publish;
	
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lcl_start);
		JackWindowTitleManager jwtm = new JackWindowTitleManager(this);
		jwtm.setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, "发起拼箱");
		jwtm.initBackBtn();
		
		edit_lcls_name = (EditText) findViewById(R.id.edit_lcls_name);
		edit_lcls_upsum = (EditText) findViewById(R.id.edit_lcls_upsum);
		edit_lcls_upone = (EditText) findViewById(R.id.edit_lcls_upone);
		edit_lcls_price = (EditText) findViewById(R.id.edit_lcls_price);
		edit_lcls_remark = (EditText) findViewById(R.id.edit_lcls_remark);
		btn_lcls_publish = (Button) findViewById(R.id.btn_lcls_publish);
		btn_lcls_publish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	};
	
}
