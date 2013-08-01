package com.gxzzb.gxphotocontest.ui.photoupload;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gxzzb.gxphotocontest.R;

public class PhotoUpload extends Activity {

	LinearLayout ly_pload;
	private static int RESULT_LOAD_IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_upload);
		setTitle("本地图片上传");

		ly_pload = (LinearLayout) findViewById(R.id.img_load);

		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, RESULT_LOAD_IMAGE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
            
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			ImageView imageView = new ImageView(this);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

			Button btn_submit = new Button(this);
			btn_submit.setText("提交");

			EditText edit = new EditText(this);

			// ly_pload.removeView(btn_submit);
			ly_pload.addView(imageView);
			ly_pload.addView(edit);
			ly_pload.addView(btn_submit);

		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// startActivity(new Intent(this, ActivityPhotoShow.class));
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
