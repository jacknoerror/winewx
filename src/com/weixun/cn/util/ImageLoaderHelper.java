package com.weixun.cn.util;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.weixun.cn.R;

/**
 * String imageUri = "http://site.com/image.png"; // 网络图片  
 * String imageUri = "file:///mnt/sdcard/image.png"; //SD卡图片  
 * String imageUri = "content://media/external/audio/albumart/13"; // 媒体文件夹  
 * String imageUri = "assets://image.png"; // assets  
 * String imageUri = "drawable://" + R.drawable.image; //  drawable文件   
 * @author taotao
 * @Date 2014-6-6
 */
public class ImageLoaderHelper {
	public static ImageLoader imageLoader = ImageLoader.getInstance();
	// public static DisplayImageOptions image_display_options =

	static Map<DisplayOptionType, DisplayImageOptions> map = new HashMap<ImageLoaderHelper.DisplayOptionType, DisplayImageOptions>();

	public static DisplayImageOptions getDisplayOpts(DisplayOptionType type) {
		DisplayImageOptions dio;
		if (null == (dio = map.get(type))) {

			switch (type) {
			case NOCACHE:
				dio = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.ic_launcher)
						.showImageForEmptyUri(R.drawable.ic_launcher)
						.showImageOnFail(R.drawable.ic_launcher)
						.displayer(new SimpleBitmapDisplayer())
						.cacheInMemory(false).cacheOnDisc(false).build();
				break;

			default:
				dio = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.ic_launcher)
						.showImageForEmptyUri(R.drawable.ic_launcher)
						.showImageOnFail(R.drawable.ic_launcher)
						.displayer(new SimpleBitmapDisplayer())
						.cacheInMemory(true).cacheOnDisc(false).build();
				break;
			}
			map.put(type, dio);
		}
		return dio;
	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(256, 256)
				// taotao
				.threadPriority(Thread.NORM_PRIORITY - 1)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				// .memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(100)
				// .writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);

	}

	public enum DisplayOptionType {
		DEFAULT, NOCACHE
	}

	/**
	 * @param bitmap
	 * @return
	 */
	@SuppressLint("NewApi")
	public static long getBitmapsize(Bitmap bitmap) {
		if (null == bitmap)
			return 0;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
			return bitmap.getByteCount();
		}
		// Pre HC-MR1
		return bitmap.getRowBytes() * bitmap.getHeight();

	}

	/**
	 * 
	 * @param bitmap
	 * @param s
	 * @return
	 */
	public static Bitmap resizeBitmap(Bitmap bitmap, float s) {
		// ��ȡ���ͼƬ�Ŀ�͸�
		float width = bitmap.getWidth();
		float height = bitmap.getHeight();
		// ��������ͼƬ�õ�matrix����
		Matrix matrix = new Matrix();
		// ������������
		float scaleWidth = s;
		float scaleHeight = s;
		float scale = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;
		if (scale > 1)
			return bitmap;
		// ����ͼƬ����
		matrix.postScale(scale, scale);
		Bitmap resulttBm = Bitmap.createBitmap(bitmap, 0, 0, (int) width,
				(int) height, matrix, true);
		if (resulttBm == null)
			return bitmap;
		// Log.i(TAG,
		// resulttBm.getWidth()+"?do u succeed?"+resulttBm.getHeight());
		return resulttBm;
	}
}
