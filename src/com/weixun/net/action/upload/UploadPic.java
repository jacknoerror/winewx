package com.weixun.net.action.upload;

import android.graphics.Bitmap;

public class UploadPic{
		Bitmap bitmap;
		public String desc="��������";
		public String uri;
		public UploadPic(Bitmap bitmap) {
			super();
			this.bitmap = bitmap;
		}
		/**
		 * ��imageloader����ʱ��ȷ����ͷ��file://��sd������·��
		 * @param uri
		 */
		public UploadPic(String uri) {
			super();
			this.uri = uri;
		}
		@Override
		public int hashCode() {
			return uri==null?super.hashCode():uri.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			return uri==null||(!(o instanceof UploadPic))?super.equals(o):uri.equals(((UploadPic)o).uri);
		}
		
	}