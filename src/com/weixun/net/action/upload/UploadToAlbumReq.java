package com.weixun.net.action.upload;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.os.Environment;
import android.util.Log;

/**
 * ����actionRequest��ͬ��ֱ�ӵ���doUpload��������
 * @author taotao
 * @Date 2014-7-22
 */
public class UploadToAlbumReq {//implements ExpandableActReqImpl{

	int albumId;
//	byte[] Filedata;
	String[] filepaths;
	String userAuth;
	String src;
	String modelCode;
	String picDesc;
	
	
	public UploadToAlbumReq(int albumId, String picDesc, String... filepaths) {
		super();
		this.albumId = albumId;
		this.filepaths = filepaths;
		src = "app";
		modelCode = "galary";
		userAuth =  getUserAuth();//TODO
		this.picDesc = picDesc;
	}

	private String getUserAuth() {
		// TODO Auto-generated method stub
		return "dbVOSwUrI@api_param_add@O3X8DC3hF8ZA==";
	}

	public String getApiName() {
		return "http://192.168.192.107/imgsvc/upload/result.htm";//TODO
//		return _UF.getUploadUrl();
	}

	public String toHttpBody() {
//		NetStrategies.finishTheURL(halfwayParamMap(NetStrategies.getBasicParamMapInstance(getApiName())));
		return finishTheURL(halfwayParamMap(new HashMap<String, String>()));
	}

	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put("albumId",""+albumId);
//		halfway.put("Filedata",""+new String(Filedata));//
		halfway.put("src",""+src);
		halfway.put("modelCode",""+modelCode);
		halfway.put("userAuth",""+userAuth);
		halfway.put("picDesc",""+picDesc);

		return halfway;
	}

	
	public static String finishTheURL(Map<String, String> map){
		if(null==map)return "";//0418
		StringBuffer url;
		url = new StringBuffer();
		//����
		String[] arrays = new String[]{};
		arrays = map.keySet().toArray(arrays);
		Arrays.sort(arrays);
		//��ǩ
			for(String str : arrays){
					url.append(str)
						.append("=")
						.append("\""+new String(map.get(str))+"\"")
						.append("; ");
			}
		Log.i("UploadToAlbumReq", "url::"+url);
		//ƴ��
//		url.append(URL_OPENAPI_VALIDCODE).append("=").append(JackUtils.getMD5(valid.toString()));
		return url.toString();
	}
	
	public String doUpload3(){
		
		try {
			String a = SocketHttpRequester.post(getApiName(), 
					halfwayParamMap(new HashMap<String, String>()), 
					new FormFile("filename.png", 
							new File(Environment.getExternalStorageDirectory()+"/qfc/imgs/398201/01/company/882575.png"), 
							"Filedata", null));
			if(a.contains("{")&&a.contains("}")) a = a.substring(a.indexOf("{"),a.indexOf("}"));
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/*@Override
	public ExpandableActReqImpl setExpandableParam(String param) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	
}
