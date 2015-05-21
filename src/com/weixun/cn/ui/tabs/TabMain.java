package com.weixun.cn.ui.tabs;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bocclottery.ui.custom.getter.ViewPagerGetter;
import com.bocclottery.ui.custom.getter.ViewPagerGetter.IvInVpImpl;
import com.weixun.cn.R;
import com.weixun.cn.ui.ContentAbstractFragment;

/**
 * @author TaoTao
 *
 */
/**
 * @author TaoTao
 *
 */
public class TabMain extends ContentAbstractFragment {

	private ListView mList;
	private ViewPagerGetter vpGet;
	private Button btnMsgFrd;
	private Button btnMsgAll;
	private TextView index_news;

	@Override
	public int getLayoutRid() {
		return 0;
	}

	@Override
	public void initView() {
		
		mList = new ListView(getActivity());
		mList.addHeaderView(initViewPager());
		initMsgBar();
		requestData();
	}

	private void initMsgBar() {
		// TODO Auto-generated method stub
		View vMB = mInflater.inflate(R.layout.layout_main_msgbar, null);
		btnMsgFrd = (Button) vMB.findViewById(R.id.index_friend_button);
		btnMsgAll = (Button) vMB.findViewById(R.id.index_all_button);
		index_news = (TextView) vMB.findViewById(R.id.index_news);
		btnMsgFrd.setSelected(true);//默认选中"朋友"
		View.OnClickListener bListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 btnMsgFrd.setSelected(v== btnMsgFrd);
				 btnMsgAll.setSelected(v== btnMsgAll);
				 // TODO Auto-generated method stub
				 
			}
		};
	}

	private View initViewPager() {
		vpGet = new ViewPagerGetter(getActivity());
		View vpLayout = mInflater.inflate(R.layout.layout_main_viewpager, null);
		ViewPager vp = (ViewPager) vpLayout.findViewById(R.id.viewpager_main);
		LinearLayout spotLayout = (LinearLayout) vpLayout.findViewById(R.id.layout_spots_main);
		vpGet.init(vp,spotLayout);
		return vpLayout;
	}
	
	
	
	/**
	 * 通过网络请求首页数据 
	 */
	private void requestData() {
		// TODO Auto-generated method stub
		loadData();
	}

	
	/**
	 * 获得首页数据后将数据填充到ui中 
	 */
	private void loadData() {
		// TODO Auto-generated method stub
		//顶部滑动图片数据 FIXME 造数据
		TempTopPic[] tps = new TempTopPic[3];
		for(TempTopPic ttp : tps){
			ttp = new TempTopPic("https://www.baidu.com/img/bdlogo.png", null);
		}
		vpGet.setup(tps);
		//新消息栏
//		if(新消息>0){TODO
		index_news.setVisibility(View.VISIBLE);
		index_news.setText("几条新消息");
//		}end if	TODO else View.INVISIBLE
		//更新列表
		//TODO
	}

	/**
	 * 此类临时使用
	 * 可根据实际返回数据，建立需要的类，实现其接口即可
	 * @author TaoTao
	 */
	class TempTopPic implements  IvInVpImpl{

		private String imgPath;
		private OnClickListener mListener;
		
		public TempTopPic(String imgPath, OnClickListener mListener) {
			super();
			this.imgPath = imgPath;
			this.mListener = mListener;
		}

		@Override
		public String getImage() {
			return imgPath;
		}

		@Override
		public OnClickListener getListener() {
			return mListener;
		}
		
	}
}
