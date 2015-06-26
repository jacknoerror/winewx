package com.jacktest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Environment;
import android.os.SystemClock;
import android.test.AndroidTestCase;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.weixun.cn.util.MyJsonRequest;

public class MainTest extends AndroidTestCase{
	protected static final String TAG = "MainTest";


	public void testRequest(Map<String, String> params, String actionName) throws Exception {
		RequestQueue mRequestQueue = Volley.newRequestQueue(getContext());
		MyJsonRequest request = new MyJsonRequest(actionName,
				params, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						String res;
						res = response.toString();
						assertNotNull(res);
						Log.i(TAG, "==>"+res);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						String res;
						res = error.getMessage();
						assertNotNull(res);
						Log.i(TAG, "-->"+res);

					}
				});
		mRequestQueue.add(request);
		SystemClock.sleep(3000);
	}

	
	public void test() throws Exception{
		testSwitch(1);
	}
	
	private void testSwitch(int key) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		String actionName = null;
		switch (key) {
		case 0:
			params.put("account", "15257149665");
			params.put("password", "123456");
			actionName = "app/userInfo/login";
			break;
		case 1://首页论坛数据 list 
			params.put("pageSize", "3");
			params.put("pageNo", "1");
//			params.put("purview", "123456");//Public 默认权限, friend

			actionName = "app/spaceTalk/list";
			break;
		case 2://帖子查询,搜索
			params.put("pageSize", "3");
			params.put("pageNo", "1");
//			params.put("Search", "123456");//Public 默认权限, friend
			
			actionName = "app/spaceTalk/search";
			
			break;
		default:
			break;
		}
		assertNotNull(actionName);
		testRequest(params,actionName);
	}
	
	
	/**
	 * @param s
	 * @param name ex."/harmony/test.txt"
	 * @throws Exception 
	 */
	public static void writeToSdcard(String s,String name) throws Exception
 {
		try {
			File file = new File(Environment.getExternalStorageDirectory()
					.getPath() + name);
			if(!file.exists()) file.getParentFile().mkdir();
			FileOutputStream outStream = new FileOutputStream(file);//
			OutputStreamWriter writer = new OutputStreamWriter(outStream,"gb2312");//,gb2312
			writer.write(s);
//			writer.write("/n");
			writer.flush();
			writer.close();// �ǵùر�

			outStream.close();
		} catch (Exception e) {
			Log.e("m", "file write error");
			throw e;
		}
	}
}
