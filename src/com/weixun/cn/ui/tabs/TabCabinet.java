package com.weixun.cn.ui.tabs;

import java.util.ArrayList;

import android.widget.ListView;

import com.hm.view.pullview.HmPullToRefreshView;
import com.hm.view.pullview.HmPullToRefreshView.OnFooterLoadListener;
import com.hm.view.pullview.HmPullToRefreshView.OnHeaderRefreshListener;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.ui.ContentAbstractFragment;
import com.weixun.cn.ui.HubActivity;
import com.weixun.cn.ui.HubActivity.OkListAdapter;

/**
 * 酒柜
 * @author TaoTao
 *
 */
public class TabCabinet extends ContentAbstractFragment {

	private ListView mListView;
	private com.hm.view.pullview.HmPullToRefreshView mPTRV;

	@Override
	public int getLayoutRid() {
		return 0;
	}

	@Override
	public void initView() {
		mPTRV = new HmPullToRefreshView(getActivity());//(HmPullToRefreshView) mView.findViewById(R.id.ptrv_cabi);
		mListView = new ListView(getActivity());//(ListView) mView.findViewById(R.id.list_cabi);
		mView = mPTRV;
		mPTRV.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
			
			
			@Override
			public void onHeaderRefresh(HmPullToRefreshView view) {
				// TODO Auto-generated method stub
				view.onHeaderRefreshFinish();
			}
		});
		mPTRV.setOnFooterLoadListener(new OnFooterLoadListener() {
			
			@Override
			public void onFooterLoad(HmPullToRefreshView view) {
				// TODO Auto-generated method stub
				view.onFooterLoadFinish();
			}
		});
		mPTRV.setPullRefreshEnable(true);
		mPTRV.setLoadMoreEnable(true);
		ArrayList<CmListItem> dataList = new ArrayList<CmListItem>();
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		mListView.setAdapter(new OkListAdapter(dataList,(HubActivity)getActivity()));//
		mPTRV.addView(mListView);
		mPTRV.onFinishInflate();
	}
}
