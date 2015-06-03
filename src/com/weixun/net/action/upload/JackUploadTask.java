package com.weixun.net.action.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.jacktao.utils.JackUtils;
import com.weixun.cn.Const;
import com.weixun.cn.MyData;
import com.weixun.cn.R;
import com.weixun.cn.util.ImageUtils;

public class JackUploadTask extends AsyncTask<UploadPic, Integer, String[]> {
	public interface ActionReceiverImpl{

		Activity getReceiverContext();

		void response(String string) throws JSONException;
		
	}
	
	final String TAG  = getClass().getSimpleName();
	private int albumId;
	private Activity mActivity;
	private ProgressDialog uploadDialog;
	private ProgressDialog loadingDialog;
	private ActionReceiverImpl receiver;
	public static String SPLIT_RESULT_UPLOAD;
	
	
//	String[] filepaths;
	
	/**
	 * @param albumId
	 * @param receiver should not be null and should return activity as ReceiverContext
	 */
	public JackUploadTask(int albumId,ActionReceiverImpl receiver){
		this.albumId = albumId;
		mActivity = (Activity) receiver.getReceiverContext();
		this.receiver = receiver;
	}
	
	private ProgressDialog showLoadingDialog() {
		return JackUtils.showProgressDialog(mActivity, "");
	}

	private void tryDismissLoadingDialog(){
		if(null!=loadingDialog&&loadingDialog.isShowing()) loadingDialog.dismiss();
	}

	@SuppressLint("NewApi")
		private void tryShowProgressDialog() {
			if(null==mActivity) return;
			if(null!=uploadDialog) return;
			uploadDialog = new ProgressDialog(mActivity);
	//		dialog.getWindow().
	//		dialog.setContentView(R.layout.dialog_severalrequest);
			uploadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			uploadDialog.setIndeterminate(false);
			uploadDialog.setCancelable(false);
			uploadDialog.setTitle("�ϴ�ͼƬ");
	//		dialog.setMessage("");
			uploadDialog.setIcon(R.drawable.ic_launcher);
			uploadDialog.setButton(Dialog.BUTTON_NEGATIVE, "��ֹ�ϴ�", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					cancel(true);
					
				}
			});
			uploadDialog.show();
		}

	@Override
	protected void onPreExecute() {
		loadingDialog = showLoadingDialog();
		super.onPreExecute();
	}

	/* 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 * @return �ϴ�ʧ�ܵĵ�ַ�ַ�����
	 */
	@Override
	protected String[] doInBackground(UploadPic... params) {
		List<String> failStrs = new LinkedList<String>();
		int progress = 0;
		publishProgress(0,params.length);//0909 ��Ϊ����ʾ�Ի���ĵ���һ�θ��½�ȿ�ʱ�����Լ�����ÿ�����ʾ
		for(UploadPic up : params){
			String failPath = null;
			try{
				failPath = upload(up );
			}catch(OutOfMemoryError e){//0924
				failPath = up.uri;
			}
			if(null!=failPath) failStrs.add(failPath);//��¼�ϴ�ʧ��·��
			else publishProgress(++progress,params.length);//�����ǰ��
		}
		return failStrs.size()==0?null:failStrs.toArray(new String[]{});
	}

	 @Override
	protected void onProgressUpdate(Integer... values) {
		tryDismissLoadingDialog();//0909
		tryShowProgressDialog();
		if(null!=uploadDialog&&values.length>1){
			uploadDialog.setMax(values[1]);
			uploadDialog.setProgress(values[0]);
		}
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(final String[] result) {
		if(mActivity==null||receiver ==null) throw new IllegalStateException("receiver cannot be nil");
		if(null==result||result.length==0){
			JackUtils.showToast(mActivity, "ͼƬȫ���ϴ��ɹ�");
		}else{
			JackUtils.showDialog(mActivity, "����ͼƬû���ϴ��ɹ�\n�����ϴ���", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// �����ϴ�
					dialog.dismiss();
					UploadPic[] ups = new UploadPic[result.length];
					for(int i=0;i<result.length;i++){
						ups[i] = new UploadPic(result[i]);
					}
					new JackUploadTask(albumId, receiver).execute(ups);
				}
			});
		}
		uploadDialog.dismiss();
		StringBuilder rsb = new StringBuilder();
		if(result!=null){//some failed
			for(String r : result){
				SPLIT_RESULT_UPLOAD = ";";
				rsb.append(r).append(SPLIT_RESULT_UPLOAD);
			}
		}
			try {
				receiver.response(rsb.toString());
			} catch (JSONException e) {}
		super.onPostExecute(result);
	}

	Map<String, String> compressedBitmapMap;


	public  Map<String, String> getCompressedBitmapPathMap(){
		if(null == compressedBitmapMap	) compressedBitmapMap = new HashMap<String, String>();
		return compressedBitmapMap;
	}

	public String getCompressedHLPath(boolean HD, String originPath){
		String result = null;
		if(HD){
			result = getCompressedBitmapPathMap().get("pOrigin");//Ӧ����ԭͼ
		}else{
			result = getCompressedBitmapPathMap().get("p800");
		}
		if(null==result) result = originPath;
		return result;
	}

	/**
	 * ��doInBackground�б�����
	 * @param up
	 * @return ʧ�ܣ�ͼƬ·���� �ɹ��� null 
	 */
	protected String upload(UploadPic up) throws OutOfMemoryError{
		if(isCancelled()) return up.uri;
		String localPath = up.uri.replace("file://", "");
		//�����С
		Bitmap bitmapFromSDCard = JackUtils.getBitmapFromSDCard(localPath);
		Bitmap tryRotateBitmap = bitmapFromSDCard;//JackUtils.tryRotateBitmap(bitmapFromSDCard, localPath);
		if(null!=tryRotateBitmap){//���û����ת�򲻽���
			localPath =  Environment.getExternalStorageDirectory().getPath()+"0987a"+System.currentTimeMillis()+"_rotated.jpg";
			storeInSD(tryRotateBitmap, localPath	);
			if(tryRotateBitmap==bitmapFromSDCard) Log.e(TAG, "same bm , all right");
			else {
				Log.e(TAG, "not same");
				bitmapFromSDCard.recycle();
				bitmapFromSDCard = tryRotateBitmap;
			}
		}
//		bitmapFromSDCard = ;//0919
		Bitmap bitmapFromSDCard2 = ImageUtils.zoomBitmap(bitmapFromSDCard, 800,800);//XXX check this
		if(bitmapFromSDCard!=bitmapFromSDCard2){//you know
			bitmapFromSDCard.recycle();
			bitmapFromSDCard=bitmapFromSDCard2;
		}
		String path800 = Environment.getExternalStorageDirectory().getPath()+"987a/"+System.currentTimeMillis()+"_800X.jpg";
		storeInSD(bitmapFromSDCard, path800	);
		getCompressedBitmapPathMap().put("p800", path800);
		getCompressedBitmapPathMap().put("pOrigin", localPath);
		bitmapFromSDCard.recycle();//
		//�ϴ�
		try {
			Map<String, String> uploadParamMap = getUploadParamMap(new HashMap<String, String>());
			uploadParamMap.put("picDesc", up.desc);
			String a = SocketHttpRequester.post(getUploadUrl(), 
					uploadParamMap, 
					new FormFile(localPath.substring(localPath.lastIndexOf("/")), 
							new File(getCompressedHLPath(Const.UPLOAD_HD, localPath)),//Environment.getExternalStorageDirectory()+"/qfc/imgs/398201/01/company/882575.png"), 
							"Filedata", null));
			Log.i(TAG, ""+a);
			if(a.contains("{")&&a.contains("}")) a = a.substring(a.indexOf("{"),a.indexOf("}")+1);//���?���ַ�
			JSONObject job = new JSONObject(a);
			int code = job.getInt("code");
			return code==0?null:getCompressedHLPath(Const.UPLOAD_HD, localPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localPath;
	}

	protected Map<String, String> getUploadParamMap(Map<String, String> halfway) {
		halfway.put("albumId",""+albumId);
		halfway.put("src","app");
		halfway.put("modelCode","galary");
		halfway.put("userAuth",getUserAuth());
//		halfway.put("picDesc",""+picDesc);

		return halfway;
	}
	
	/**
	 * @return �ϴ�ͼƬ��url
	 */
	protected String getUploadUrl() {
		return "http://192.168.192.107/imgsvc/upload/result.htm";//TODO
//		return NetConst._UF.getUploadUrl();
	}
	
	/**
	 * ���ܵ��㷨����com.qfc.api.util.APIDesUtils�ļ��ܺͽ���
		key=modelCode,
		value=this.uid + "|null|app",��"|"Ϊ�ָ��ַ�����
		uidΪ��ǰ�û���Id
		���� APIDesUtils.encrypt("test","36538|null|member")="bkSnZ7zSYwy1jEAkskY5cy8tLnAl2e3R"
	 * @return �ϴ�ͼƬ��userAuth������û���Ϣ���ܻ��
	 */
	private String getUserAuth() {//XXX 
		return "dbVOSwUrI@api_param_add@O3X8DC3hF8ZA==";
//		try {
//			return new APIDesUtils().encrypt(MyData.data().getMe().getId()+"|null|app",APIDesUtils.UPLOAD_KEY);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}
	
	/**
	 * storeInSD(bitmap, "/01/imgs/test.jpg");
	 * 
	 * @param bitmap
	 * @param path
	 */
	public static void storeInSD(Bitmap bitmap, String path) {
		String pre = Environment.getExternalStorageDirectory().getPath();
		if(!path.startsWith(pre))path = pre + path;
		File file = new File(path.substring(0, path.lastIndexOf('/') + 1));
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("here u go");
		}
		File imageFile = new File(file,
				path.substring(path.lastIndexOf('/') + 1));
		try {
			imageFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(imageFile);
			bitmap.compress(CompressFormat.JPEG, 100, fos);//
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
