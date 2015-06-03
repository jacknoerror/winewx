package com.weixun.cn.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

public class ImageUtils {

	public static final int EXPECT_SIDE_640 = 640;
	public static final int EXPECT_SIDE_480 = 480;
	public static final int EXPECT_SIDE_320 = 320;
	public static final int EXPECT_SIDE_720 = 720;

	/**
	 * ʹ��320��Ϊ����ֵ����ͼƬѹ��
	 * 
	 * @see com.zdht.jingli.groups.utils.ImageUtils#compressImage(String, int)
	 */
	public static Bitmap compressImage(String path) throws OutOfMemoryError {
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		return compressImage(path, EXPECT_SIDE_640);
	}

	/**
	 * ��������ֵ�������ѹ��ͼƬ, ʵ�����ű���Ϊ��ӽ�����ֵ������ı�����2��n�η�. 
	 * <p>
	 * 
	 * @param path
	 *            ͼƬ�ļ�·��
	 * @param expect
	 *            ���������߳�, ���ڼ���ѹ����.
	 * @return 
	 */
	public static Bitmap compressImage(String path, int expect)
			throws OutOfMemoryError {
		if (TextUtils.isEmpty(path) || expect < 1) {
			return null;
		}

		try {
			Options o = new Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, o);

			int width = o.outWidth;
			int height = o.outHeight;
			
			if(width > height) { // �����ȱȽϳ��Ļ�
				o.inSampleSize = doubleToInt(width * 1.0f / expect);
			} else {
				o.inSampleSize = doubleToInt(height * 1.0f / expect);
			}

			o.inJustDecodeBounds = false;
			Bitmap bitmap = BitmapFactory.decodeFile(path, o);
			return bitmap;
		} catch (OutOfMemoryError e) {
			throw e;
		}
	}
	
	/**
	 * ��������תint
	 * @param a
	 * @return
	 */
	private static int doubleToInt(double a) {
		return (int)Math.floor(a + 0.5);
	}
	
	/**
	 * ��СͼƬ, ��Ϊ800;
	 * ���ͼƬ��͸߶�800, ��������.
	 * 
	 * @param bitmap
	 *            Ҫ������ͼƬ
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap) {
		float dstSide = 800;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		if(width <= dstSide && dstSide<= dstSide) {
			return bitmap;
		}
		Matrix matrix = new Matrix();
		float scale;
		if(width > height) {
			scale = dstSide / width;
		} else {
			scale = dstSide / height;
		}
		matrix.postScale(scale, scale);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}

	/**
	 * ��СͼƬ, ���ͼƬ��͸߶�С��Ŀ�곤, ��������.
	 * 
	 * @param bitmap
	 *            Ҫ������ͼƬ
	 * @param dstSide
	 *            Ŀ�곤
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, float dstSide) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		if(width <= dstSide && dstSide<= dstSide) {
			return bitmap;
		}
		Matrix matrix = new Matrix();
		float scale;
		if(width > height) {
			scale = dstSide / width;
		} else {
			scale = dstSide / height;
		}
		matrix.postScale(scale, scale);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}
	
	/**
	 * �Ŵ���СͼƬ���̶����,
	 * 
	 * @param bitmap
	 *            Ҫ������ͼƬ
	 * @param dstWidth
	 *            Ŀ���
	 * @param dstHeight
	 *            Ŀ���
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int dstWidth, int dstHeight) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidht = ((float) dstWidth / width);
		float scaleHeight = ((float) dstHeight / height);
		matrix.postScale(scaleWidht, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}

	/**
	 * ��Drawableת��ΪBitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * ���Բ��ͼƬ�ķ���
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		return getRoundedCornerBitmap(bitmap, 10);
	}

	/**
	 * ���Բ��ͼƬ�ķ���
	 * 
	 * @param bitmap
	 * @param percent
	 *            �ٷ���
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int percent) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		if(width > 480 || height > 800) {
			
//			zoomBitmap(bitmap, );
		}
		
		
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);

		int roundPx = rect.width() * percent / 100;
		int roundPy = rect.height() * percent / 100;
		canvas.drawRoundRect(rectF, roundPx, roundPy, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * ��ô���Ӱ��ͼƬ����
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);
		return bitmapWithReflection;
	}

	/**
	 * ͨ��HttpURLConnection ����ͼƬ
	 * 
	 * @param file
	 * @param imgUrl
	 */
	public static void loadImage(File file, String imgUrl) {

		HttpURLConnection connection = null;
		try {
			URL url = new URL(imgUrl);
			// ����http����
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(2000);
			connection.setReadTimeout(2000);

			// ��ȡ�ļ���С
			int fileSize = connection.getContentLength();
			if (fileSize == -1) {
				return;
			}
			// ��http����, ��ȡ������
			InputStream inputStream = connection.getInputStream();
			// ���û�����
			byte[] buf = new byte[1024];
			byte[] total = new byte[fileSize];
			int readNum = 0;
			int loadSize = 0;
			if (connection.getResponseCode() == 200) {
				// ��ȡ����
				while ((readNum = inputStream.read(buf)) != -1) {
					// �������ζ�ȡ�����ݵ���������
					System.arraycopy(buf, 0, total, loadSize, readNum);
					loadSize += readNum;
				}
				if (loadSize == fileSize) {// ����������, �����ļ�������
					// �ļ����浽����
					if (file != null) {
						FileOutputStream fos = new FileOutputStream(file);
						// д�뵽�ļ�
						fos.write(total, 0, loadSize);
						fos.close();
						// SCApplication.print( "loadImage end:success");
					}
				}
			}
		} catch (MalformedURLException e) {
			// SCApplication.print( "loadImage end:" + e.getMessage());
		} catch (IOException e) {
			// SCApplication.print( "loadImage end:" + e.getMessage());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int TOP = 3;
	public static final int BOTTOM = 4;

	/** */
	/**
	 * ͼƬȥɫ,���ػҶ�ͼƬ
	 * 
	 * @param bmpOriginal
	 *            �����ͼƬ
	 * @return ȥɫ���ͼƬ
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}

	/** */
	/**
	 * ȥɫͬʱ��Բ��
	 * 
	 * @param bmpOriginal
	 *            ԭͼ
	 * @param pixels
	 *            Բ�ǻ���
	 * @return �޸ĺ��ͼƬ
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {
		return toRoundCorner(toGrayscale(bmpOriginal), pixels);
	}

	/**
	 * ��ͼƬ���Բ��
	 * 
	 * @param bitmap
	 *            ��Ҫ�޸ĵ�ͼƬ
	 * @param pixels
	 *            Բ�ǵĻ���
	 * @return Բ��ͼƬ
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		System.gc();
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * ʹԲ�ǹ���֧��BitampDrawable
	 * 
	 * @param bitmapDrawable
	 * @param pixels
	 * @return
	 */
	public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable,
			int pixels) {
		Bitmap bitmap = bitmapDrawable.getBitmap();
		bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
		return bitmapDrawable;
	}

	/**
	 * ��ȡ·���е�ͼƬ��Ȼ����ת��Ϊ���ź��bitmap
	 * 
	 * @param path
	 */
	public static void saveBefore(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// ��ȡ���ͼƬ�Ŀ�͸�
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // ��ʱ����bmΪ��
		options.inJustDecodeBounds = false;
		// �������ű�
		int be = (int) (options.outHeight / (float) 200);
		if (be <= 0)
			be = 1;
		options.inSampleSize = 2; // ͼƬ�������С����֮һ
		// ���¶���ͼƬ��ע�����Ҫ��options.inJustDecodeBounds ��Ϊ falseŶ
		bitmap = BitmapFactory.decodeFile(path, options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		System.out.println(w + " " + h);
		// savePNG_After(bitmap,path);
		saveJPGE_After(bitmap, path);
	}

	/**
	 * ����ͼƬΪPNG
	 * 
	 * @param bitmap
	 * @param name
	 */
	public static void savePNG_After(Bitmap bitmap, String name) {
		File file = new File(name);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ͼƬΪJPEG
	 * 
	 * @param bitmap
	 * @param path
	 */
	public static void saveJPGE_After(Bitmap bitmap, String path) {
		File file = new File(path);
		try {
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ˮӡ
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createBitmapForWatermark(Bitmap src, Bitmap watermark) {
		if (src == null) {
			return null;
		}
		int w = src.getWidth();
		int h = src.getHeight();
		int ww = watermark.getWidth();
		int wh = watermark.getHeight();
		// create the new blank bitmap
		Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// ����һ���µĺ�SRC���ȿ��һ����λͼ
		Canvas cv = new Canvas(newb);
		// draw src into
		cv.drawBitmap(src, 0, 0, null);// �� 0��0���꿪ʼ����src
		// draw watermark into
		cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);// ��src�����½ǻ���ˮӡ
		// save all clip
		cv.save(Canvas.ALL_SAVE_FLAG);// ����
		// store
		cv.restore();// �洢
		return newb;
	}

	/**
	 * ͼƬ�ϳ�
	 * 
	 * @return
	 */
	public static Bitmap potoMix(int direction, Bitmap... bitmaps) {
		if (bitmaps.length <= 0) {
			return null;
		}
		if (bitmaps.length == 1) {
			return bitmaps[0];
		}
		Bitmap newBitmap = bitmaps[0];
		// newBitmap = createBitmapForFotoMix(bitmaps[0],bitmaps[1],direction);
		for (int i = 1; i < bitmaps.length; i++) {
			newBitmap = createBitmapForFotoMix(newBitmap, bitmaps[i], direction);
		}
		return newBitmap;
	}

	private static Bitmap createBitmapForFotoMix(Bitmap first, Bitmap second,
			int direction) {
		if (first == null) {
			return null;
		}
		if (second == null) {
			return first;
		}
		int fw = first.getWidth();
		int fh = first.getHeight();
		int sw = second.getWidth();
		int sh = second.getHeight();
		Bitmap newBitmap = null;
		if (direction == LEFT) {
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, sw, 0, null);
			canvas.drawBitmap(second, 0, 0, null);
		} else if (direction == RIGHT) {
			newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, 0, 0, null);
			canvas.drawBitmap(second, fw, 0, null);
		} else if (direction == TOP) {
			newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, 0, sh, null);
			canvas.drawBitmap(second, 0, 0, null);
		} else if (direction == BOTTOM) {
			newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,
					Config.ARGB_8888);
			Canvas canvas = new Canvas(newBitmap);
			canvas.drawBitmap(first, 0, 0, null);
			canvas.drawBitmap(second, 0, fh, null);
		}
		return newBitmap;
	}

	/**
	 * ��Bitmapת����ָ����С
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap createBitmapBySize(Bitmap bitmap, int width, int height) {
		return Bitmap.createScaledBitmap(bitmap, width, height, true);
	}

	/**
	 * Drawable ת Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmapByBD(Drawable drawable) {
		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		return bitmapDrawable.getBitmap();
	}

	/**
	 * Bitmap ת Drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmapToDrawableByBD(Bitmap bitmap) {
		Drawable drawable = new BitmapDrawable(bitmap);
		return drawable;
	}

	/**
	 * byte[] ת bitmap
	 * 
	 * @param b
	 * @return
	 */
	public static Bitmap bytesToBimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 * bitmap ת byte[]
	 * 
	 * @param bm
	 * @return
	 */
	public static byte[] bitmapToBytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	
	/**
	 * ת��ͼƬ��Բ��
	 * 
	 * @param bitmap
	 *            ����Bitmap����
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);

		paint.setAntiAlias(true);// ���û����޾��

		canvas.drawARGB(0, 0, 0, 0); // �������Canvas
		paint.setColor(color);

		// ���������ַ�����Բ,drawRounRect��drawCircle
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// ��Բ�Ǿ��Σ���һ������Ϊͼ����ʾ���򣬵ڶ��������͵����������ֱ���ˮƽԲ�ǰ뾶�ʹ�ֱԲ�ǰ뾶��
		canvas.drawCircle(roundPx, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));// ��������ͼƬ�ཻʱ��ģʽ,�ο�http://trylovecatch.iteye.com/blog/1189452
		canvas.drawBitmap(bitmap, src, dst, paint); //��Mode.SRC_INģʽ�ϲ�bitmap���Ѿ�draw�˵�Circle
		
		return output;
	}
}
