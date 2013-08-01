package com.gxzzb.gxphotocontest.support.bins;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Base64;

public class SupportBitmap {
	String pictureDir = "";
	String base64 = "";
	FileOutputStream fos = null;
	BufferedOutputStream bos = null;
	ByteArrayOutputStream baos = null;
	Bitmap bitmap;

	public SupportBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		handlebitmap();
	}

	public void handlebitmap() {

		try {
			baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			//
			byte[] byteArray = baos.toByteArray();
			//
			base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
			//
			String saveDir = Environment.getExternalStorageDirectory()
					+ "/temple";
			File dir = new File(saveDir);
			if (!dir.exists()) {
				dir.mkdir();
			}
			File file = new File(saveDir, "temp.jpg");
			file.delete();
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(byteArray);
			//Í¼Æ¬Â·¾¶
			pictureDir = file.getPath();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	public String getbase64() {
		return base64;

	}

	public String getpictureDir() {
		return pictureDir;

	}

}
