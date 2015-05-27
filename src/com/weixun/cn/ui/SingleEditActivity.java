package com.weixun.cn.ui;

import com.jacktao.utils.JackWindowTitleManager;
import com.jacktao.utils.JackWindowTitleManager.JackTitleConst;
import com.weixun.cn.Const;
import com.weixun.cn.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;

/**
 * @author TaoTao
 *
 */
public class SingleEditActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String strTitle = getIntent().getStringExtra(Const.EXTRA_EDITACTI_TITLE);
		String strCur = getIntent().getStringExtra(Const.EXTRA_EDITACTI_CUR);
		
		JackWindowTitleManager jwtMana = new JackWindowTitleManager(this);
		jwtMana.setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, strTitle);
		jwtMana.initBackBtn();
		
		EditText edit = new EditText(this);
		edit.setText(strCur);
		setContentView(edit);
		getWindow().setGravity(Gravity.TOP);
		edit.requestFocus();
	}
}
