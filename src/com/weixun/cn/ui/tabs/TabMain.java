package com.weixun.cn.ui.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.easemob.chatuidemo.activity.MainActivity;
import com.easemob.chatuidemo.activity.MainActivity.OkListAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jacktao.ui.custom.getter.ViewPagerGetter;
import com.jacktao.ui.custom.getter.ViewPagerGetter.IvInVpImpl;
import com.jacktao.utils.JackUtils;
import com.weixun.cn.Const;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.bean.SpaceTalkDto;
import com.weixun.cn.ui.ContentAbstractFragment;
import com.weixun.cn.ui.MyPortal;
import com.weixun.cn.ui.SearchActivity;
import com.weixun.cn.util.MyJsonRequest;

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
	private List<CmListItem> takList;

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
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageSize", "5");
		params.put("pageNo", "1");
		String actionName = "app/spaceTalk/list";
		RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity());
		MyJsonRequest request = new MyJsonRequest(actionName,
				params, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				JSONArray jar = response.optJSONArray("result");
				loadData(jar);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				JackUtils.showToast(getActivity(), error.getMessage());

			}
		});
		
		actionName = "app/spaceReply/loadUserUnreadCount";
		params.clear();
		MyJsonRequest request0 = new MyJsonRequest(actionName,
				params, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				JSONObject job = response.optJSONObject("result");
				loadData0(job);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				JackUtils.showToast(getActivity(), error.getMessage());

			}
		});
		mRequestQueue.add(request);
		mRequestQueue.add(request0);
//		loadData();
	}

	
	/**
	 * 获得首页数据后将数据填充到ui中 
	 * @param jar 
	 */
	private void loadData(JSONArray jar) {
		//更新列表
		//TODO
		takList = new ArrayList<CmListItem>();
		for(int i=0;i<jar.length();i++){
			JSONObject job = jar.optJSONObject(i);
			Gson gson = new GsonBuilder()  
			  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
			  .create();
			SpaceTalkDto takDto = gson.fromJson(job.toString(), SpaceTalkDto.class);
			takList.add(takDto);
		}
		mList.setAdapter(new OkListAdapter(takList,(MainActivity)getActivity()));//
		
	}


	private void loadData0(JSONObject job) {
		// TODO Auto-generated method stub
		//顶部滑动图片数据 FIXME 造数据
		JSONArray advs = job.optJSONArray("advs");
		TempTopPic[] tps = new TempTopPic[advs.length()];
		
		for(int i=0;i<tps.length;i++){
			final JSONObject advJob = advs.optJSONObject(i);
			tps[i] = new TempTopPic(Const.URL_BASE+Const.URL_IMG_MIDPATH+advJob.optString("image"), new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String url = advJob.optString("advLinkAddress");
					JackUtils.showToast(getActivity(), url);;
					
				}
			});
		}
		/*{
                "value": "advBody_16",
                "advLinkAddress": "http://www.baidu.com",
                "id": 1,
                "image": "find_2.fw.png",
                "advName": "advName_73",
                "type": "forum"
            }*/
//		tps[0] = new TempTopPic("https://www.baidu.com/img/bdlogo.png", null);
//		tps[1] = new TempTopPic("http://image.zcool.com.cn/59/54/m_1303967870670.jpg", null);
//		tps[2] = new TempTopPic("http://image.zcool.com.cn/47/19/1280115949992.jpg", null);
		vpGet.setup(tps);
		//新消息栏
		int unread = job.optInt("unreadCount");
		if(unread>0){
		index_news.setVisibility(View.VISIBLE);
		index_news.setText(unread+"条新消息");
		}else index_news.setVisibility(View.INVISIBLE);
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
