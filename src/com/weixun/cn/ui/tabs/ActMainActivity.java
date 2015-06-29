package com.weixun.cn.ui.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.easemob.chatuidemo.DemoApplication;
import com.jacktao.utils.JackLocationManager;
import com.jacktao.utils.JackUtils;
import com.jacktao.utils.JackWindowTitleManager;
import com.jacktao.utils.JackWindowTitleManager.JackTitleConst;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weixun.cn.MyApplication;
import com.weixun.cn.R;
import com.weixun.cn.bean.CmListItem;
import com.weixun.cn.ui.MyPortal;
import com.weixun.cn.util.MyJsonRequest;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 活动首页
 * 
 * @author TaoTao
 *
 */
public class ActMainActivity extends Activity implements OnItemClickListener {

	private ListView mListView;

	@Override
	protected void onResume() {
		super.onResume();
		JackLocationManager.getInstance(DemoApplication.getInstance())
				.makeLocationUpdates();
	}

	@Override
	protected void onPause() {
		super.onPause();
		JackLocationManager.getInstance(DemoApplication.getInstance())
				.removeLocationUpdates();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		JackWindowTitleManager jwtMana = new JackWindowTitleManager(this);
		jwtMana.setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, "活动");
		TextView view = new TextView(this);
		view.setText("+");
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 添加
				
			}
		});
		jwtMana.addRight(view);
		jwtMana.initBackBtn();;
		
		mListView = new ListView(this);
		setContentView(mListView);
//		mListView.addHeaderView(getHeader());
		
		//list init
		List<CmListItem> dataList = new ArrayList<CmListItem>();
//		dataList.add(new CmListItem());
//		dataList.add(new CmListItem());
		mListView.setAdapter(new ActMainListAdapter(dataList, this));
		
		mListView.setOnItemClickListener(this);
		
		requestData();
	}

	private void requestData() {
		Map<String, String> params = new HashMap<String, String>();
		Location location = JackLocationManager.getInstance(DemoApplication.getInstance()).getLocation();//FIXME
		params.put("city", "杭州");//	String	Y	城市
		params.put("lon", location!=null?location.getLongitude()+"":"");//	Double	y	经度
		params.put("lat", location!=null? location.getLatitude()+"":"");//	double	y	维度
		params.put("pageSize", "10");//	Int	Y	每页大小
		params.put("pageNo", "3");//	Int	y	
//		params.put("search", "3");//	String	n	查询条件
		String actionName = "app/activity/index";
		
		RequestQueue mRequestQueue = Volley.newRequestQueue(this);
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
				JackUtils.showToast(ActMainActivity.this, error.getMessage());

			}
		});
		mRequestQueue.add(request);
	}

	protected void loadData(JSONArray jar) {
		// TODO Auto-generated method stub
		
	}

	private View getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	class ActMainListAdapter extends BaseAdapter {
		List<CmListItem> contentList;
		private LayoutInflater aInflater;

		public ActMainListAdapter(List<CmListItem> list, Activity mActi) {
			if (null == list)
				list = new ArrayList<CmListItem>();
			contentList = list;
			aInflater = LayoutInflater.from(mActi);
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
			AMHolder holder = null;
			// View view = null;
			if (null == convertView) {
				convertView = aInflater.inflate(R.layout.listitem_actmain, null);// ?
				holder = new AMHolder();
//				holder TODO
				holder.img_big = (ImageView) convertView.findViewById(R.id.img_itm_am_bigbg);
				holder.tv_city = (TextView) convertView.findViewById(R.id.tv_item_am_city);
				convertView.setTag(holder);
			} else {
				holder = (AMHolder) convertView.getTag();
			}
			// setup
			CmListItem u = contentList.get(position);
			// TODO
			ImageLoader.getInstance().displayImage("https://www.baidu.com/img/bdlogo.png", holder.img_big);
			holder.tv_city.setText("浙江");
			
			return convertView;
		}

	}
	static class AMHolder{
		TextView tv_city,tv_pos,tv_smalltitle,tv_price;
		ImageView img_big,img_small;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		MyPortal.justGo(this, ActDetailActivity.class);
	}
}
