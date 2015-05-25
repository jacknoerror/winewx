package com.weixun.cn.ui.tabs;

import java.util.ArrayList;

import com.jacktao.ui.custom.RefreshListView;
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

	private RefreshListView mRefreshListView;

	@Override
	public int getLayoutRid() {
		return 0;
	}

	@Override
	public void initView() {
		mRefreshListView = new RefreshListView(getActivity());
		mView = mRefreshListView;
		ArrayList<CmListItem> dataList = new ArrayList<CmListItem>();
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		dataList.add(new CmListItem());
		mRefreshListView.setAdapter(new OkListAdapter(dataList,(HubActivity)getActivity()));//
		
	}
}
