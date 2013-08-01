package com.gxzzb.gxphotocontest.ui.photocamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.ui.photoshow.ActivityPhotoShow;

public class CameraUpLoad extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_up_load);
		setTitle("拍照上传");

		try {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		try {
			if (requestCode != 0) {
				return;
			}
			if (null == data) {
				return;
			}
			super.onActivityResult(requestCode, resultCode, data);
			Bundle extras = data.getExtras();
			Bitmap b = (Bitmap) extras.get("data");

			ImageView img_view = new ImageView(this);
			img_view.setImageBitmap(b);
			LinearLayout ly_img = (LinearLayout) findViewById(R.id.img_ly);
			ly_img.addView(img_view);
			Button btn_up = new Button(this);
			btn_up.setText("上传");
			ly_img.addView(btn_up);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(this, ActivityPhotoShow.class));
			finish();
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera_up_load, menu);
		return true;
	}

}
