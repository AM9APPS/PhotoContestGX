package com.gxzzb.gxphotocontest.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.datas.StaticString;
import com.gxzzb.gxphotocontest.net.HttpAsyncTask;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.gxzzb.gxphotocontest.ui.photoshow.ActivityPhotoShow;

public class ActivityMain extends Activity {

	Button button;
	private LinearLayout wel_ly01;
	private LinearLayout wel_ly02;
	private ImageView img_view_logo;
	private Button btn01;

	private TextView txv01;
	private TextView txv02;
	private TextView txv03;
	private TextView txv04;

	public int screenW;
	public int screenH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		wel_ly01 = (LinearLayout)findViewById(R.id.wel_ly01);
		getScreen();
		md_image_view();
		
		wel_ly02 = (LinearLayout)findViewById(R.id.wel_ly02);
		md_texview();
		button = (Button) this.findViewById(R.id.btn01);
		button.setOnClickListener(new Myevent());
		// 初次加载数据获取
		String httpUrl = URLHelper.URL_SJ_LIST + "?pagenum="
				+ StaticString.eachCount + "&pageFilter="
				+ StaticString.pageCount;
		HttpAsyncTask httpAsyncTask = new HttpAsyncTask(httpUrl, this);
		httpAsyncTask.initistrResultforHttp();
		StaticString.pageCount++;

	}

	class Myevent implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(ActivityMain.this,
					ActivityPhotoShow.class);
			ActivityMain.this.startActivity(intent);

		}

	}
	private void md_image_view() {
		int imageview_height;
		int imageview_top;

		imageview_height = (int) (screenH * 0.25);
		imageview_top = (int) (screenH * 0.15);

		img_view_logo = new ImageView(this);
		img_view_logo.setImageDrawable(getResources().getDrawable(
				R.drawable.welcome_logo));

		img_view_logo.setAdjustViewBounds(true);
		img_view_logo.setMaxHeight(imageview_height);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		img_view_logo.setLayoutParams(params);
		params.setMargins(0, imageview_top, 0, 0);
		wel_ly01.addView(img_view_logo);

	}

	public void getScreen() {
		WindowManager windowM = getWindowManager();
		Display display = windowM.getDefaultDisplay();

		screenH = display.getHeight();
		screenW = display.getWidth();

	}
	private void md_texview() {

//		int textview_top;
//
//		textview_top = (int) (screenH * 0.07);

		txv01 = new TextView(this);
		txv02 = new TextView(this);
		txv03 = new TextView(this);
		txv04 = new TextView(this);

		txv01.setText(R.string.textvs1);
		txv02.setText(R.string.textvs2);
		txv03.setText(R.string.textvs3);
		txv04.setText(R.string.textvs4);

		LinearLayout.LayoutParams params_text_view = new LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
				android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);

//		txv01.setLayoutParams(params_text_view);
//
//		params_text_view.setMargins(0, textview_top, 0, 0);

		txv01.setGravity(Gravity.CENTER);
		txv02.setGravity(Gravity.CENTER);
		txv03.setGravity(Gravity.CENTER);
		txv04.setGravity(Gravity.CENTER);
		wel_ly02.addView(txv01);
		wel_ly02.addView(txv02);
		wel_ly02.addView(txv03);
		wel_ly02.addView(txv04);

	}


}
