package com.weixun.cn.ui.tabs;

import com.jacktao.utils.JackUtils;
import com.jacktao.utils.JackWindowTitleManager;
import com.jacktao.utils.JackWindowTitleManager.JackTitleConst;
import com.weixun.cn.R;
import com.weixun.cn.ui.MyPortal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 个人信息
 * @author TaoTao
 *
 */
public class MyInfoActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		JackWindowTitleManager jwtMana = new JackWindowTitleManager(this);
		jwtMana.setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, "个人信息");
		jwtMana.initBackBtn();
		
		((TextView) findViewById(R.id.tv_myinfo_nick)).setText("小盆友2");
		((TextView)findViewById(R.id.tv_myinfo_signiture)).setText("122222");
		
		findViewById(R.id.layout_myinfo_addr).setOnClickListener(this);
		findViewById(R.id.layout_myinfo_addr).setOnClickListener(this);
        findViewById(R.id.layout_myinfo_dist).setOnClickListener(this);
        findViewById(R.id.layout_myinfo_icon).setOnClickListener(this);
        findViewById(R.id.layout_myinfo_nick).setOnClickListener(this);
        findViewById(R.id.layout_myinfo_qrcode).setOnClickListener(this);
        findViewById(R.id.layout_myinfo_sex).setOnClickListener(this);
        findViewById(R.id.layout_myinfo_signiture).setOnClickListener(this);
        findViewById(R.id.layout_myinfo_wxno).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String mt=null,mc=null;
		switch (v.getId()) {
		case R.id.layout_myinfo_addr:
			break;
		case R.id.layout_myinfo_dist:
			break;
		case R.id.layout_myinfo_icon:
			break;
		case R.id.layout_myinfo_nick:
			mt = "我的昵称"; mc = "小盆友";
			break;
		case R.id.layout_myinfo_qrcode:
			break;
		case R.id.layout_myinfo_sex:
			break;
		case R.id.layout_myinfo_signiture:
			mt = "个性签名"; mc = "22222";
			break;
		case R.id.layout_myinfo_wxno:
			break;
		default:
			break;
		}
		if(null!=mt){
			MyPortal.goEditActi(this, mt, mc);
		}else{
			JackUtils.showToast(this, v.getId()+"");
		}
		
	}
	
}
