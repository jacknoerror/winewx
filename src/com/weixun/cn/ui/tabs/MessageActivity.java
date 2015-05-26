package com.weixun.cn.ui.tabs;

import java.util.ArrayList;

import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.ui.HubActivity;
import com.weixun.cn.ui.HubActivity.OkListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class MessageActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ListView mList = new ListView(this);
		setContentView(mList);
		TextView footer = new TextView(this);
		footer.setText("加载更多");
		mList.addFooterView(footer);
		
		ArrayList<CmListItem> dataList = new ArrayList<CmListItem>();
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		mList.setAdapter(new OkListAdapter(dataList,this));//
		
		
	}

}
