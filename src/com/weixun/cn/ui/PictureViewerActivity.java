package com.weixun.cn.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jacktao.ui.custom.TouchImageView;
import com.jacktao.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.weixun.cn.R;

public class PictureViewerActivity extends Activity {

	private com.jacktao.ui.custom.ExtendedViewPager vp_pv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageLoaderHelper.initImageLoader(this);//
		setContentView(R.layout.activity_picview);
		
		vp_pv = (com.jacktao.ui.custom.ExtendedViewPager) findViewById(R.id.vp_pv);
		String[] urlarr = new String[10];
		/*List<View> viewList = new ArrayList<View>();*/
		for (int i = 0 ; i < 10 ; i ++){
			urlarr[i] = "https://www.baidu.com/img/bdlogo.png";
		}
		vp_pv.setAdapter(new TouchImageAdapter(urlarr ));
	}
	
	static class TouchImageAdapter extends PagerAdapter {

		String[] urlArr;
		

		public TouchImageAdapter(String[] urlArr) {
			super();
			this.urlArr = urlArr;
		}

		@Override
        public int getCount() {
        	return urlArr.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            TouchImageView img = new TouchImageView(container.getContext());
            ImageLoader.getInstance().displayImage(urlArr[position], img);
//            img.setImageResource(images[position]);
            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
	
	/*public class ZoomImageViewLoadListener implements com.nostra13.universalimageloader.core.assist.ImageLoadingListener{

		private static final String TAG = "zoomImageListener";
		TouchImageView zImg;
		
		
		public ZoomImageViewLoadListener(TouchImageView zImg) {
			super();
			this.zImg = zImg;
		}

		@Override
		public void onLoadingStarted(String imageUri, View view) {
		}

		@Override
		public void onLoadingFailed(String imageUri, View view,
				FailReason failReason) {
			Log.i(TAG, "failReason");
		}

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			// TODO Auto-generated method stub
			zImg.setImageBitmap(loadedImage);
			Log.i(TAG, "onLoadingComplete");
		}

		@Override
		public void onLoadingCancelled(String imageUri, View view) {
			Log.i(TAG, "onLoadingCancelled");
		}
		
	}*/
}
