package com.weixun.cn.ui.tabs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easemob.chatuidemo.myactivity.ActionItem;
import com.easemob.chatuidemo.myactivity.TitlePopup;
import com.easemob.chatuidemo.myactivity.TitlePopup.OnItemOnClickListener;
import com.jacktao.utils.JackWindowTitleManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weixun.cn.R;
import com.weixun.cn.util.WxUtils;

public class ActDetailActivity extends Activity implements OnClickListener {

	private LayoutInflater mInflater;
	private TitlePopup titlePopup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actidetail);
		
		WxUtils.simpleTitle(new JackWindowTitleManager(this), "活动详情");//
		
		initLayout1();
		
	}

	private void initLayout1() {
		// TODO Auto-generated method stub
		View aLayout1 = findViewById(R.id.layout_actd1);
		final TextView selectRange = (TextView) aLayout1.findViewById(R.id.tv_act_range);
		final TextView tv_act_group = (TextView) aLayout1.findViewById(R.id.tv_act_group);
		selectRange.setOnClickListener(this);
		tv_act_group.setOnClickListener(this);
		titlePopup = new TitlePopup(this, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		titlePopup
				.addAction(new ActionItem(this, "私有", R.drawable.addfriend));
		titlePopup
				.addAction(new ActionItem(this, "仅群报名", R.drawable.groupchars));
		titlePopup.addAction(new ActionItem(this, "公开报名",
				R.drawable.mm_title_btn_qrcode_normal));
		titlePopup.setItemOnClickListener(new OnItemOnClickListener(){

            @Override
            public void onItemClick(ActionItem item, int position) {
                // TODO Auto-generated method stub
                switch (position) {
                case 0:
                	selectRange.setText("私有");
                	tv_act_group.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                	selectRange.setText("仅群报名");
                	//  选择群
                	tv_act_group.setVisibility(View.VISIBLE);
                	tv_act_group.setText("点我选择群组");
                    break;
                case 2:
                	tv_act_group.setVisibility(View.INVISIBLE);
                	selectRange.setText("公开报名");
                    break;
                default:
                    break;
                }
            }
		
		});
		// TODO 判断是否添加第二部分
		initLayout2();
	}

	private void initLayout2() {
		// TODO Auto-generated method stub
		View aLayout2 = findViewById(R.id.layout_actd2);
		aLayout2.setVisibility(View.VISIBLE);
		//加载列表数据
		LinearLayout layout = (LinearLayout) aLayout2.findViewById(0);//R.id.layout_ad_wines); //FIXME
		for(int i=0;i<5;i++){
			View item = mInflater.inflate(R.layout.item_actwines, null);
			final ImageView v1 = (ImageView) item.findViewById(R.id.img_actwines_lock);
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
			item.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub 有关加锁的请求
					v1.setSelected(v.isSelected());//锁
				}
			});
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
				.findViewById(9);//R.id.layout_ad_comment);//FIXME
		for (int i = 0; i < 5; i++) {
			View item = mInflater.inflate(R.layout.listitem_comment, null);

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_act_range:
			//弹出选择
			titlePopup.show(v);
			break;
		case R.id.tv_act_group:
			//TODO 选择群组
			break;
		default:
			break;
		}
	}
}
