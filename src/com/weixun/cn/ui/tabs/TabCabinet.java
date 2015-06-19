package com.weixun.cn.ui.tabs;

import java.util.ArrayList;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import com.weixun.cn.customview.MyShareDialog;
import com.easemob.chatuidemo.activity.MainActivity;
import com.hm.view.pullview.HmPullToRefreshView;
import com.hm.view.pullview.HmPullToRefreshView.OnFooterLoadListener;
import com.hm.view.pullview.HmPullToRefreshView.OnHeaderRefreshListener;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.customview.MyShareDialog;
import com.weixun.cn.ui.ContentAbstractFragment;
import com.weixun.cn.ui.HubActivity.OkListAdapter;

/**
 * 酒柜
 * @author TaoTao
 *
 */
public class TabCabinet extends ContentAbstractFragment {

	private ListView mListView;
	private com.hm.view.pullview.HmPullToRefreshView mPTRV;
	private ImageButton imagebutton;

	@Override
	public int getLayoutRid() {
		return R.layout.fragment_listview_winebox;
	}

	@Override
	public void initView() {
		mPTRV = (HmPullToRefreshView) mView.findViewById(R.id.ptrv_cabi);
		mListView = (ListView) mView.findViewById(R.id.list_cabi);
		imagebutton = (ImageButton)mView.findViewById(R.id.imb_do);
//		mView = mPTRV;
		
		imagebutton.setOnClickListener(new OnClickListener(){
            @Override 
            public void onClick(View v) { 
            	showShareDialog();

            } 
		});
		
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
//		mListView.setAdapter(new OkListAdapter(dataList,(HubActivity)getActivity()));//
		mListView.setAdapter(new OkListAdapter(dataList,(MainActivity)getActivity()));//
//		mPTRV.addView(mListView);
		mPTRV.onFinishInflate();
	}
	public Dialog showShareDialog() {
		MyShareDialog msDialog = new MyShareDialog(getActivity());
		msDialog.show();
		return msDialog;
	}
}
