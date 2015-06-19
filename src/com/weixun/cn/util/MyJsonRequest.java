package com.weixun.cn.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.easemob.chatuidemo.DemoApplication;

public class MyJsonRequest extends Request<JSONObject>{
	public static final String BASE_URL = "http://120.26.105.250:8080/RedWine/";
	private Map<String, String> mMap;
    private Listener<JSONObject> mListener;
    public MyJsonRequest(String url, Map<String, String> map, Listener<JSONObject> listener,ErrorListener errorListener) {
        super(Request.Method.POST, BASE_URL+url, errorListener);             
        mListener = listener;
        mMap = map;
    }
     
    //mMap是已经按照前面的方式,设置了参数的实例
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
    	//设置appToken
        SharedPreferences pre =DemoApplication.applicationContext.getSharedPreferences("user", Context.MODE_PRIVATE);
		mMap.put("appToken", pre.getString("appToken", ""));
        System.out.println("POST:"+mMap.toString());
        return mMap;
    }
     
    //此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
                 
            return Response.success(new JSONObject(jsonString),HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }
    
    


}
