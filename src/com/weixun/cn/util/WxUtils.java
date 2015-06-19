package com.weixun.cn.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jacktao.utils.JackWindowTitleManager;
import com.weixun.cn.MyApplication;
import com.weixun.cn.R;


public class WxUtils {
	
	public final static String srcRegEx = "(?<=src=\")(.*?)(?=\")";
	
	public static void showToast(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	public static ProgressDialog showProgressDialog(Context context,String text){
		
		return ProgressDialog.show(context, "", "������...");
	}
	

	 /** 
	      * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����) 
	      */  
	     public static int dip2px(Context context, float dpValue) {  
	         final float scale = context.getResources().getDisplayMetrics().density;  
	         return (int) (dpValue * scale + 0.5f);  
	     }  
	   
	     /** 
	      * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp 
	      */  
	     public static int px2dip(Context context, float pxValue) {  
	         final float scale = context.getResources().getDisplayMetrics().density;  
	         return (int) (pxValue / scale + 0.5f);  
	     }  

	     /**
	      * ��Assets�ж�ȡͼƬ
	      */
	     public static Bitmap getbmFromAssetsFile(Resources res , String fileName)
	     {
	     	if(res==null) return null;
	         Bitmap bm = null;
	         AssetManager am = res.getAssets();
	         try
	         {
	             InputStream is = am.open(fileName);
	             bm = BitmapFactory.decodeStream(is);
	             is.close();
	         }
	         catch (IOException e)
	         {
	             e.printStackTrace();
	         }

	         return bm;

	     }
	
	     @Deprecated
      public static String getDate(){
    	  return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      }
      
      /**
       * write string to /data
       * @param context
       * @param str
       * @return
       */
      public static boolean writeToSomeWhere(Context context , String str) {
          return writeToFile(context, "fromSomewhere.xml", str);
	}
      public static boolean writeToFile(Context context, String filename, String content){
    	  try { 
              OutputStream out=context.openFileOutput(filename, Context.MODE_PRIVATE); 
              OutputStreamWriter outw=new OutputStreamWriter(out); 
              
              try { 
                  outw.write(content); 
                  outw.close(); 
                  out.close(); 
                  return true; 
              } catch (IOException e) { 
                  return false; 
              } 
          } catch (FileNotFoundException e) { 
              return false; 
          } 
      }
      
      public static String readFromFile(Context context , String filename){
    	  String result= "";
    	  
    	  try {
			InputStream in = context.openFileInput(filename);
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			while(br.read()!=-1){
				result+=br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("no such file as:"+ filename);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	  
    	  return result;
      }
      
      /**
       * add SD-pre automately
       * @param path
       * @return
       */
      public static boolean deleteFile(String path){
    	  if(!path.startsWith(getSDPre())) path = getSDPre()+path;
    	  return deleteFile(new File(path));
      }
      //��SD���ļ�ɾ��
      public static boolean  deleteFile(File file)
      {
    	   
       if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
       {
	        if (file.exists())
	        {
		         if (file.isFile())
		         {
		        	 return file.delete();//1210
		         }
		         // �������һ��Ŀ¼
		         else if (file.isDirectory())
		         {
		          // ����Ŀ¼�����е��ļ� files[];
			          File files[] = file.listFiles();
			          for (int i = 0; i < files.length; i++)
			          { // ����Ŀ¼�����е��ļ�
			           deleteFile(files[i]); // ��ÿ���ļ� ������������е���
			          }
		         }
	         return file.delete();
	        }else
	        {//�Ѿ�������
	       	 return true; //1210 taotao
	        }
       }
       return false;
      }  
      
	public static AlertDialog showDialog(Context context, String hintContent,
			DialogInterface.OnClickListener positiveListener) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(hintContent);

		builder.setTitle("��ʾ");

		if (null != positiveListener)
			builder.setPositiveButton("ȷ��", positiveListener);

		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		/*TextView customTitleView = (TextView) LayoutInflater.from(context).inflate(R.layout.textview_dialoghead, null);
		customTitleView.setText(context.getResources().getString(R.string.app_name)+"����");
		builder.setCustomTitle(customTitleView );*/

		return builder.show();
	}
      /**
       * ���ȷ����ر�activity
     * @param rContext
     * @param hintContent
     * @param activity
     * @return
     * 1211
     */
	public static void showDialogAndFinishActivity(final Activity activity, String hintContent) {
		if(null==activity) return;
		AlertDialog.Builder builder = new Builder(activity);
		builder.setMessage(hintContent);
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				activity.finish();
			}
		});
		 builder.setCancelable(false);
		builder.show();
	}
      
      /**
       * 
       * @param path   "/qfc/01/we.jpg"
       * @return
       */
      public static Bitmap getBitmapFromSDCard(String filePath){
    	  /**
    	      *ͼƬ�ļ�·��
    	 *��ӡEnvironment.getExternalStorageDirectory()�õ���"/mnt/sdcard"�����ҵ���sd���ĸ�Ŀ¼
    	      */
    	 if(filePath==null||!filePath.startsWith("/mnt/sdcard"))filePath=Environment.getExternalStorageDirectory()+filePath;
    	     File mfile=new File(filePath);
    	     if (mfile.exists()) {//�����ļ�����
    	     Bitmap bm = BitmapFactory.decodeFile(filePath);
    	     Log.i("SD", "exists");
    	     return bm;
    	     }
    	  return null;
      }
      
      public static void textpaint_bold(TextView tv) {
  		TextPaint tp = tv.getPaint();tp.setFakeBoldText(true);//�Ӵ�
  	}
     public static void textpaint_underline(TextView tv){
    	 TextPaint tp = tv.getPaint();tp.setUnderlineText(true);
     }
     
     public static String getSDPre(){
    	 return Environment.getExternalStorageDirectory().getPath();
     }
     
     final static long durationMillis = 800,delayMillis=200;
     public static void slideview(final View view , final float p1, final float p2) {//1231
 		if(null==view) return;
 	     
 		 TranslateAnimation animation = new TranslateAnimation(0, 0, p1, p2);
 	     //��������д��������ʱ��view�ƶ���ʱ�� ���е���Ч��
 	     animation.setInterpolator(new DecelerateInterpolator());
 	     animation.setDuration(durationMillis);
 	     animation.setStartOffset(delayMillis);
 	     animation.setAnimationListener(new Animation.AnimationListener() {
 	         @Override
 	         public void onAnimationStart(Animation animation) {
 	         }
 	         
 	         @Override
 	         public void onAnimationRepeat(Animation animation) {
 	         }
 	         
 	         @Override
 	         public void onAnimationEnd(Animation animation) {
 	             int left = view.getLeft();
 	             int top = view.getTop()+(int)(p2-p1);
 	             int width = view.getWidth();
 	             int height = view.getHeight();
 	            view.clearAnimation();
 	           view.layout(left, top, left+width, top+height);
 	         }
 	     });
 	    view.startAnimation(animation);
 	 }
     
     public static String getTimeStamp(){
 		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
 	}
     
     /**
 	 * MD5
 	 * @param s
 	 * @return
 	 */
 	 public final static String getMD5(String s) {
 	        return getMD5(s.getBytes());
 	    }
 	public final static String getMD5(byte[] bytes){
 		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};       
         try {
             byte[] btInput = bytes;
             // ���MD5ժҪ�㷨�� MessageDigest ����
             MessageDigest mdInst = MessageDigest.getInstance("MD5");
             // ʹ��ָ�����ֽڸ���ժҪ
             mdInst.update(btInput);
             // �������
             byte[] md = mdInst.digest();
             // ������ת����ʮ�����Ƶ��ַ�����ʽ
             int j = md.length;
             char str[] = new char[j * 2];
             int k = 0;
             for (int i = 0; i < j; i++) {
                 byte byte0 = md[i];
                 str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                 str[k++] = hexDigits[byte0 & 0xf];
             }
             return new String(str);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
 	}
 	
 	
 	public static String encryptionPassword(String password) {
		// 0A53F97440A7C3
		String result = "";
		int data[] = { 0x39, 0x60, 0xCA, 0x47, 0x73, 0x94, 0xA2, 0x45 };
		int j = 0;

		byte[] chats = null;
		try {
			chats = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < chats.length; i++) {
			String s = Integer.toHexString(chats[i] ^ data[j]);
			if (s.length() == 1) {
				s = "0" + s;
			}
			result += s;

			j = (j + 1) % 8;
		}

		return result;
	}
 	
 	
	/**
	 * ͼƬ����
	 * δ�����ڴ棬������ʹ��
	 * @param bm
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
 	@Deprecated
	public static Drawable imageScale(Bitmap bitmap, int dst_w, int dst_h) {

		int src_w = bitmap.getWidth();
		int src_h = bitmap.getHeight();

		if (src_w <= dst_w && src_h <= dst_h) {
			dst_w = src_w;
			dst_h = src_h;
		}

		float scale_w = ((float) dst_w) / src_w;
		float scale_h = ((float) dst_h) / src_h;
		Matrix matrix = new Matrix();
		matrix.postScale(scale_w, scale_h);
		Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
				true);
		return new BitmapDrawable(dstbmp);
	}
	

	
	/**
	 * �����û�����ͼƬ
	 * 
	 * @param bmp ԭʼͼ
	 * @return ��������ͼ
	 */
	public static Bitmap setUsOfFlineBmp(Bitmap bmp) {
		Bitmap grayImg = null;
		try {
			int width = bmp.getWidth();
			int height = bmp.getHeight();
			grayImg = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_4444);
			Canvas canvas = new Canvas(grayImg);
			Paint paint = new Paint();
			ColorMatrix colorMatrix = new ColorMatrix();
			colorMatrix.setSaturation(0);
			paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
			canvas.drawBitmap(bmp, 0, 0, paint);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();

		}

		return grayImg;
	}
	
	/**
	 * 
	 * @param bmp1 ԭʼͼ
	 * @param bmp2 ״̬ͼ��
	 * @return �ϳɺ��ͼ
	 */
	public static Bitmap setSynthBmp(Bitmap bmp1, Bitmap bmp2) {
		Bitmap grayImg = null;
		try {
			int width = bmp1.getWidth();
			int height = bmp1.getHeight();
			grayImg = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_4444);
			Canvas canvas = new Canvas(grayImg);
			Paint paint = new Paint();
			canvas.drawBitmap(bmp1, 0, 0, paint);
			canvas.drawBitmap(bmp2, width - bmp2.getWidth(),
					height - bmp2.getHeight(), paint);// ����ͼ��
			canvas.save(Canvas.ALL_SAVE_FLAG);

		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		return grayImg;

	}
	
	
	/**
	 * @return
	 */
	public static boolean isNetworkAvailable(){
		ConnectivityManager manaConnectivity;
		NetworkInfo networkinfo;
		return null!=(manaConnectivity = MyApplication.app().manaConnectivity)
				&&null!=(networkinfo = manaConnectivity.getActiveNetworkInfo())
				&&networkinfo.isAvailable();
	}
	
	
	/**
	 * ��ȡ��ǰ����Ļ��ʾ�Ĵ�������
	 * 
	 * @param context
	 * @return
	 */
	public static String getDisplayClassName(Context context) {
		return ((ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1)
				.get(0).topActivity.getClassName();
	}

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),InputMethodManager.SHOW_FORCED);
	}
	
	public static void makeAcall(String phoneNum,Context context) throws Exception{// 
		Intent intent=new Intent(); 
		intent.setAction(Intent.ACTION_DIAL);   
		intent.setData(Uri.parse("tel:"+phoneNum));
		context.startActivity(intent);
	}
	
	/**
	 * @throws NameNotFoundException 
	 * 
	 */
	public static int  getApiLvl( )  {
		return android.os.Build.VERSION.SDK_INT;
	}
	
	/**
	 * @param path
	 * @return File�ļ�������ļ��в������򴴽��ļ���
	 */
	public static File makeMeFile(String path) {
		File photoFile = new File(path);
		if (!photoFile.getParentFile().exists()) {
			photoFile.getParentFile().mkdirs();
		}
		return photoFile;
	}

	static int vvcc;
	static String vvnn="";
	public static int getVersionCode(Context context)//��ȡ�汾��(�ڲ�ʶ���)
	{
		if(vvcc>0) return vvcc;
		try {
			PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return (vvcc=pi.versionCode);
		} catch (NameNotFoundException e) {
			return 0;
		}
	}
	public static String getVersionName(Context context)//��ȡ�汾��(�ڲ�ʶ���)
	{
		if(!vvnn.isEmpty()) return vvnn;
		try {
			PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return (vvnn=pi.versionName);
		} catch (NameNotFoundException e) {
			return "";
		}
	}

	/**
	 * @param url
	 */
	public static void gotoWeb(final String url, Activity activity) {
		if (null == activity)
			return;
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse(url);
		intent.setData(content_url);
		activity.startActivity(intent);
	}

	public static JackWindowTitleManager simpleTitle(JackWindowTitleManager mWtm, String title) {
		mWtm.setComponent(R.id.title_midtext, title);
		mWtm.initBackBtn();
		return mWtm;
	}
	
	public static boolean checkRE(String rule,EditText editText,String errormsg){
		if(null==editText) return false;
		if(!checkRE(rule, editText.getText().toString())){
			editText.setError(errormsg);
			editText.requestFocus();
			return false;
		}
		return true;
	}
	
	public static boolean checkRE(String rule,String content){
		return content.matches(rule);
	}
	


}
