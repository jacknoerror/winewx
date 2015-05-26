package com.weixun.cn.ui.tabs;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacktao.utils.JackWindowTitleManager;
import com.jacktao.utils.JackWindowTitleManager.JackTitleConst;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.weixun.cn.Const;
import com.weixun.cn.R;
import com.weixun.cn.ui.MyPortal;

public class PublishActivity extends Activity {

	private JackWindowTitleManager mTitleMana;
	private ImageView img1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);
		mTitleMana = new JackWindowTitleManager(this);
		mTitleMana.setComponent(JackTitleConst.CUSTOMTITLE_ID_MIDTEXT, "发布动态");
		TextView view = new TextView(this);
		view.setText("发布");
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 发布
				
			}
		});
		mTitleMana.addRight(view);
		mTitleMana.initBackBtn();
		
		img1 = (ImageView)findViewById(R.id.img_publish_1);
		ImageLoader.getInstance().displayImage("file:///external/images/media/775",  img1);
		img1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyPortal.goImages((Activity)PublishActivity.this);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
//			ContentResolver cr = this.getContentResolver();
			String path = "";
			if (requestCode == Const.AR_LOCALPHOTO) {

				Uri uri = data.getData();
//				startPhotoZoom(uri, Const.AR_LOCALPHOTO);
				Log.e("uri", uri.toString());
				
				ContentResolver cr = this.getContentResolver();    
	            try {    
	                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));    
	                /* 将Bitmap设定到ImageView */    
	                img1.setImageBitmap(bitmap);    
	                findViewById(R.id.img_publish_2).setVisibility(View.VISIBLE);//TODO
	            } catch (FileNotFoundException e) {    
	                Log.e("Exception", e.getMessage(),e);    
	            }    
	            
			} 
			/*else if (requestCode == Const.AR_TAKEPHOTO) {
				path = getIntent().getStringExtra("path");// like NetConst.PATH_UPLOADTEMP_CAM+"_cam.jpg";
				File temp = new File(path);
				startPhotoZoom(Uri.fromFile(temp), Const.AR_TAKEPHOTO);
			}*/
			/*else if (requestCode == Const.AR_CUTPHOTO) {
				if (data != null && data.hasExtra("data")) {
					Bitmap photo = data.getExtras().getParcelable("data");
					img_upload.setImageBitmap(photo);
					//从当前intent中获取在之前步骤中放入的路径
					String pp = getIntent().getStringExtra("path");
					if (null != pp)
						uploadPic(new UploadPic(pp));// FIXME 不在这里上传
					else
						JackUtils.showToast(this, "图片路径错误");
				}

			}*/
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
