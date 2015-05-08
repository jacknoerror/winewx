package com.weixun.cn.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class Snippet {
	/**
	  * 获取拓展存储Cache的绝对路径
	  *
	  * @param context
	  */
	 public static String getExternalCacheDir(Context context) {
	      StringBuilder sb = new StringBuilder();
	      File file = context.getExternalCacheDir();
	      // In some case, even the sd card is mounted,
	      // getExternalCacheDir will return null
	      // may be it is nearly full.
	      if (file != null) {
	           sb.append(file.getAbsolutePath()).append(File.separator);
	      } else {
	           sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Android/data/").append(context.getPackageName())
	                     .append("/cache/").append(File.separator).toString();
	      }
	      return sb.toString();
	 }
}

