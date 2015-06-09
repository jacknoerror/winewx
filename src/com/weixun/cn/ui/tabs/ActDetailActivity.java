package com.weixun.cn.ui.tabs;

import java.util.ArrayList;
import java.util.List;

import com.jacktao.utils.JackWindowTitleManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.util.WxUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class ActDetailActivity extends Activity {

	private LayoutInflater mInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actidetail);
		
		WxUtils.simpleTitle(new JackWindowTitleManager(this), "活动详情");//
		mInflater = LayoutInflater.from(this);
		initLayout1();
		
	}

	private void initLayout1() {
		// TODO Auto-generated method stub
		View aLayout1 = findViewById(R.id.layout_actd1);
		//TODO 判断是否添加第二部分
		initLayout2();
	}

	private void initLayout2() {
		// TODO Auto-generated method stub
		View aLayout2 = findViewById(R.id.layout_actd2);
		aLayout2.setVisibility(View.VISIBLE);
		//加载列表数据
		LinearLayout layout = (LinearLayout) aLayout2.findViewById(R.id.layout_ad_wines);
		for(int i=0;i<5;i++){
			View item = mInflater.inflate(R.layout.item_actwines, null);
			ImageView v1 = (ImageView) item.findViewById(R.id.img_actwines_lock);
			TextView v2 = (TextView) item.findViewById(R.id.tv_actwines_name);//酒名
			TextView v3 = (TextView) item.findViewById(R.id.tv_actwines_num);//数量
			TextView v4 = (TextView) item.findViewById(R.id.tv_actwines_person);//人名
			
			v1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					v.setSelected(v.isSelected());
				}
			});
			//TODO 填充数据
			v4.setText("人名");
			layout.addView(item);
		}
		//加载头像数据
		GridView grid = (GridView) findViewById(R.id.grid_ad_enroll);
		ArrayList<NameAndPic> mList = new ArrayList<ActDetailActivity.NameAndPic>();
		mList.add(new NameAndPic("王e", "https://www.baidu.com/img/bdlogo.png"));
		mList.add(new NameAndPic("王2", "https://www.baidu.com/img/bdlogo.png"));
		mList.add(new NameAndPic("王三", "https://www.baidu.com/img/bdlogo.png"));
		mList.add(new NameAndPic("王4", "https://www.baidu.com/img/bdlogo.png"));
		mList.add(new NameAndPic("王5", "https://www.baidu.com/img/bdlogo.png"));
		grid.setAdapter(new MySimpleGridAdapter(mList));
		
		//判断初始化第三部分
		initLayout3();
	}

	private void initLayout3() {
		// TODO Auto-generated method stub
		View aLayout3 = findViewById(R.id.layout_actd3);
		aLayout3.setVisibility(View.VISIBLE);
		// 加载列表数据
		LinearLayout layout = (LinearLayout) aLayout3
				.findViewById(R.id.layout_ad_comment);
		for (int i = 0; i < 5; i++) {
			View item = mInflater.inflate(R.layout.listitem_common, null);

			// TODO 填充数据
			layout.addView(item);
		}
		//
		View btn_comment = findViewById(R.id.btn_ad_comment);
	}
	
	public class MySimpleGridAdapter extends BaseAdapter{

		List<NameAndPic> mList;
		
		
		
		public MySimpleGridAdapter(List<NameAndPic> mList) {
			super();
			this.mList = mList;
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			NPHolder holder = null;
			// View view = null;
			if (null == convertView) {
				convertView = mInflater.inflate(R.layout.layout_tab, null);// ?
				holder = new NPHolder();
				holder.img = (ImageView) convertView.findViewById(R.id.img_tab);
				holder.text = (TextView) convertView.findViewById(R.id.tv_tab);
				convertView.setTag(holder);
			} else {
				holder = (NPHolder) convertView.getTag();
			}
			// setup
			NameAndPic u = mList.get(position);
			ImageLoader.getInstance().displayImage(u.getPic(), holder.img);
			holder.img.setScaleType(ScaleType.CENTER_CROP);
			holder.text.setText(u.getName());
			holder.text.setVisibility(View.VISIBLE);
			
			return convertView;
		}
		
	}
	static class NPHolder{
		ImageView img;
		TextView text;
	}

	class NameAndPic {
		String name;
		String pic;

		public NameAndPic(String name, String pic) {
			super();
			this.name = name;
			this.pic = pic;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}

	}
}
