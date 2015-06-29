package com.weixun.cn.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.easemob.chatuidemo.activity.ChatAllHistoryFragment;
import com.easemob.chatuidemo.activity.MainActivity;
import com.jacktao.utils.JackUtils;
import com.jacktao.utils.JackWindowTitleManager;
import com.jacktao.utils.JackWindowTitleManager.JackTitleConst;
import com.weixun.cn.MyData;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.customview.MyShareDialog;
import com.weixun.cn.ui.tabs.PublishActivity;
import com.weixun.cn.ui.tabs.TabCabinet;
import com.weixun.cn.ui.tabs.TabDiscover;
import com.weixun.cn.ui.tabs.TabMain;
import com.weixun.cn.ui.tabs.TabMy;
@Deprecated
public class HubActivity extends FragmentActivity implements
		OnTabChangeListener {
	final String TAG = HubActivity.class.getName();
	final int[] ICONS = new int[] { R.drawable.selector_hubtab0,
			R.drawable.selector_hubtab1, R.drawable.selector_hubtab2,
			R.drawable.selector_hubtab3, R.drawable.selector_hubtab4 };
	final String[] TITLES = new String[] { "首页", "聊天", "酒柜", "发现", "我的" };
	final Class[] CLAZZZ = new Class[] { TabMain.class, ChatAllHistoryFragment.class,
			TabCabinet.class, TabDiscover.class, TabMy.class };

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private TabHost mTabHost;
	private JackFragmentTabChangedHelper jftcl;

	long backtime;
	private JackWindowTitleManager jwtMana;
	private ImageView rightImg_publish, rightImg_search, rightImg_stores;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			long currentTimeMillis = System.currentTimeMillis();
			if (currentTimeMillis - backtime > 2000) {
				JackUtils.showToast(this, "再按一次退出微醺世界");
			} else {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addCategory(Intent.CATEGORY_HOME);
				this.startActivity(intent);
			}
			backtime = currentTimeMillis;
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.a_hub);
		jwtMana = new JackWindowTitleManager(this);
		jwtMana.setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, "微醺世界");
		rightImg_publish = new ImageView(this);
		rightImg_search = new ImageView(this);
		rightImg_stores = new ImageView(this);
		rightImg_publish.setImageResource(R.drawable.index_xie);
		rightImg_publish.setPadding(1, 0, 15, 0);
		rightImg_search.setImageResource(R.drawable.index_search);
		rightImg_stores.setImageResource(R.drawable.speak_saoyisao);
		rightImg_publish.setId(R.drawable.index_xie);
		rightImg_search.setId(R.drawable.index_search);
		rightImg_stores.setId(R.drawable.speak_saoyisao);
		View.OnClickListener l = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.drawable.index_xie:
					MyPortal.justGo(HubActivity.this, PublishActivity.class);
					break;
				case R.drawable.index_search:
					MyPortal.justGo(HubActivity.this, SearchActivity.class);
					break;
				case R.drawable.speak_saoyisao:
					showShareDialog();
					break;
				default:
					break;
				}
			}
		};
		rightImg_publish.setOnClickListener(l);
		rightImg_search.setOnClickListener(l);
		rightImg_stores.setOnClickListener(l);
		jwtMana.addRight(rightImg_publish);
		jwtMana.addRight(rightImg_search);
		jwtMana.addRight(rightImg_stores);

		// singleBack = getIntent().getBooleanExtra(YftValues.EXTRAS_SINGLEBACK,
		// false);
		jftcl = new JackFragmentTabChangedHelper(this, R.id.realtabcontent);
		for (int i = 0; i < CLAZZZ.length; i++) {
			jftcl.addTabPack(jftcl.new TabPack(ICONS[i], TITLES[i], CLAZZZ[i]));
		}
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mTabHost.setOnTabChangedListener(this);
		for (int i = 0; i < CLAZZZ.length; i++) {
			addTabBtn(i);
		}
		// if(!singleBack)YftData.data().setHostTab(mTabHost);

		// int tabId = getIntent().getIntExtra(YftValues.EXTRAS_HUB_TAB, 0);
		// User me = MyData.data().getMe();
		// int defaultTab = me==null||me.getMemberType()<=1?0:2;//0725
		int defaultTab = 0;
		mTabHost.setCurrentTab(defaultTab);

		MyData.data().setTabHost(mTabHost);

	}

	static AlertDialog showDialog(Context context, String hintContent,
			DialogInterface.OnClickListener positiveListener) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(hintContent);

		builder.setTitle("提示");

		if (null != positiveListener)
			builder.setPositiveButton("确认", positiveListener);// 0408

		builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		return builder.show();
	}

	@Override
	public void onTabChanged(String tabId) {
//		getActionBar().show();
		if (null != jftcl)
			jftcl.onTabChanged(tabId);
		if (null != jwtMana) {
			jwtMana.setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, tabId);
		}
		rightImg_publish.setVisibility(tabId.equals(TITLES[0])?View.VISIBLE:View.GONE);
		rightImg_search.setVisibility(tabId.equals(TITLES[0])?View.VISIBLE:View.GONE);
		rightImg_stores.setVisibility(tabId.equals(TITLES[2])?View.VISIBLE:View.GONE);
		if(tabId.equals(TITLES[1])){
			//TODO 切换到聊天
//			getActionBar().hide();
//			
//			Intent intent = new Intent(this,MainActivity.class);
//			Bundle data = new Bundle();
//			data.putInt("index", 0);
//			intent.putExtras(data);
//			startActivity(intent);
		}
	}

	private void addTabBtn(int index) {
		if (index >= TITLES.length || index >= ICONS.length)
			return;
		View view = LayoutInflater.from(this)
				.inflate(R.layout.layout_tab, null);

		ImageView img = (ImageView) view.findViewById(R.id.img_tab);
		TextView text = (TextView) view.findViewById(R.id.tv_tab);

		text.setTextColor(getResources().getColorStateList(
				R.color.selector_tab_textcolor));
		img.setImageResource(ICONS[index]);
		img.setScaleType(ScaleType.CENTER_INSIDE);
		text.setText(TITLES[index]);

		mTabHost.addTab(mTabHost.newTabSpec(TITLES[index]).setIndicator(view)
				.setContent(new DummiContiFac(getBaseContext())));
	}

	public class DummiContiFac implements TabContentFactory {
		Context dcContext;

		public DummiContiFac(Context context) {
			dcContext = context;
		}

		@Override
		public View createTabContent(String tag) {
			return new View(dcContext);
		}

	}

	static class CabHolder {
		// TODO textview
	}

	

	public Dialog showShareDialog() {
		MyShareDialog msDialog = new MyShareDialog(this);
		msDialog.show();
		return msDialog;
	}
}
