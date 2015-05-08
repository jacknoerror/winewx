package com.weixun.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	ActionBar actionBar;
	private LinearLayout mGallery;
	private int[] mImgIds;
	private LayoutInflater mInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setCustomView(R.layout.index_title);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		// TextView tView=(TextView)findViewById(R.id.index_news);
		// final String sText2 = "<img src=\"/mnt/sdcard/temp/1.jpg\" />3条信息";
		// final Html.ImageGetter imageGetter = new Html.ImageGetter() {
		// public Drawable getDrawable(String source) {
		// Drawable drawable=null;
		// drawable=Drawable.createFromPath(source);
		// drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
		// drawable.getIntrinsicHeight());
		// return drawable;
		// }
		// };
		// tView.setText(Html.fromHtml(sText2, imageGetter, null));
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.listview, new String[] { "nickname" },
				new int[] { R.id.nickname });
		ListView list =(ListView)findViewById(R.id.listView); 
		list.setAdapter(adapter);
		initData();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.search) {
			return true;
		} else if (id == R.id.publish) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initData() {
		mImgIds = new int[] { R.drawable.dot_blur, R.drawable.ic_launcher,
				R.drawable.dot_focus };
	}

	private void initView() {
		LayoutInflater inflater = getLayoutInflater();//（在Activity中可以使用，实际上是View子类下window的一个函数）
		View layout = inflater.inflate(R.layout.listview, null);
		mGallery = (LinearLayout)layout.findViewById(R.id.gallery);
		for (int i = 0; i < mImgIds.length; i++) {
			View view = inflater.inflate(R.layout.gallery_item, mGallery,
					false);
			ImageView img = (ImageView)view.findViewById(R.id.imageView);
			img.setImageResource(mImgIds[i]);

			mGallery.addView(view);
		}
	}
	
	 private List<Map<String, Object>> getData() {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("nickname", "小小的我");
	        list.add(map);
	 
	        map = new HashMap<String, Object>();
	        map.put("nickname", "打打的是");
	        list.add(map);
	 
	        map = new HashMap<String, Object>();
	        map.put("nickname", "发觉开房间");
	        list.add(map);
	         
	        return list;
	    }
}
