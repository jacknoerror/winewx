package com.weixun.cn.ui.tabs;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jacktao.ui.custom.RefreshListView;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.ui.ContentAbstractFragment;

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
		mRefreshListView.setAdapter(new OkListAdapter(dataList));
		
	}

	public class OkListAdapter	extends BaseAdapter{
		List<CmListItem> contentList;
		private LayoutInflater aInflater;
		
		public   OkListAdapter(List<CmListItem> list) {
			if(null==list) list = new ArrayList<CmListItem>();
			contentList = list;
			aInflater = LayoutInflater.from(getActivity());
		}

		@Override
		public int getCount() {
			return contentList.size();
		}

		@Override
		public Object getItem(int position) {
			return contentList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			CabHolder holder = null;
//			View view = null;
			if(null == convertView){
				convertView = aInflater.inflate(R.layout.listitem_common, null);//?
				holder = new CabHolder();
				convertView.setTag(holder);
			}else{
				holder = (CabHolder) convertView.getTag();
			}
			//setup
			CmListItem u = contentList.get(position);
			//TODO
			
			return convertView;
		}
		
	}
	static class CabHolder{
		//TODO
	}
}
