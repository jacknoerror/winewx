package com.weixun.cn.ui.tabs.stores;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.easemob.chatuidemo.activity.MainActivity.OkListAdapter;
import com.jacktao.utils.JackWindowTitleManager;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.util.WxUtils;

public class StoreInActivity extends Activity implements OnClickListener {

	private ListView mListv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stores);
		TextView view = new TextView(this);
		view.setText("入库");
		view.setOnClickListener(this);
		view.setBackgroundColor(Color.GREEN);
		view.setTextColor(Color.WHITE);
		view.setPadding(15, 5, 15, 5);
		WxUtils.simpleTitle(new JackWindowTitleManager(this), "入库").addRight(view );;
		
		
		initList();
	}

	private void initList() {
		mListv = (ListView) findViewById(R.id.list_stores);
		ArrayList<CmListItem> dataList = new ArrayList<CmListItem>();
//		dataList.add(new CmListItem());
//		dataList.add(new CmListItem());
		mListv.setAdapter(new OkListAdapter(dataList,this));//
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
