package com.weixun.cn.ui.tabs;

import java.util.ArrayList;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.chatuidemo.activity.MainActivity;
import com.jacktao.ui.custom.getter.ViewPagerGetter;
import com.jacktao.ui.custom.getter.ViewPagerGetter.IvInVpImpl;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.ui.ContentAbstractFragment;
import com.weixun.cn.ui.HubActivity;
import com.weixun.cn.ui.SearchActivity;
import com.weixun.cn.ui.HubActivity.OkListAdapter;
import com.weixun.cn.ui.MyPortal;

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
		return R.layout.activity_index;
	}
	
	
	@Override
	public void initView() {
//		View Xview = mInflater.inflate(R.layout.index_head, null);
		Button searchButton= (Button)mView.findViewById(R.id.search);
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyPortal.justGo(getActivity(), SearchActivity.class);
			}
		});
		
		Button puhButton= (Button)mView.findViewById(R.id.publish);
		puhButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyPortal.justGo(getActivity(), PublishActivity.class);
			}
		});
		
		
		
		mList = (ListView)mView.findViewById(R.id.list_index);
		mList.addHeaderView(initViewPager());
		mList.addHeaderView(initMsgBar());
		
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyPortal.goWebView(getActivity(),"url");
				
			}
		});
		requestData();
	}

	private View initMsgBar() {
		// TODO Auto-generated method stub
		View vMB = mInflater.inflate(R.layout.layout_main_msgbar, null);
		btnMsgFrd = (Button) vMB.findViewById(R.id.index_friend_button);
		btnMsgAll = (Button) vMB.findViewById(R.id.index_all_button);
		index_news = (TextView) vMB.findViewById(R.id.index_news);
		btnMsgAll.setSelected(true);//默认选中"全部"
		View.OnClickListener bListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 btnMsgFrd.setSelected(v== btnMsgFrd);
				 btnMsgAll.setSelected(v== btnMsgAll);
				 // TODO Auto-generated method stub
				 if(v == index_news){
					 MyPortal.justGo(getActivity(), MessageActivity.class);
				 }
			}
		};
		btnMsgFrd.setOnClickListener(bListener);
		btnMsgAll.setOnClickListener(bListener);
		index_news.setOnClickListener(bListener);
		return vMB;
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
//		for(int i=0;i<tps.length;i++){
//			tps[i] = new TempTopPic("https://www.baidu.com/img/bdlogo.png", null);
//		}
		tps[0] = new TempTopPic("https://www.baidu.com/img/bdlogo.png", null);
		tps[1] = new TempTopPic("http://image.zcool.com.cn/59/54/m_1303967870670.jpg", null);
		tps[2] = new TempTopPic("http://image.zcool.com.cn/47/19/1280115949992.jpg", null);
		vpGet.setup(tps);
		//新消息栏
//		if(新消息>0){TODO
		index_news.setVisibility(View.VISIBLE);
		index_news.setText("几条新消息");
//		}end if	TODO else View.INVISIBLE
		//更新列表
		//TODO
		ArrayList<CmListItem> dataList = new ArrayList<CmListItem>();
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		mList.setAdapter(new OkListAdapter(dataList,(MainActivity)getActivity()));//
//		mList.setAdapter(new OkListAdapter(dataList,(HubActivity)getActivity()));
		
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
