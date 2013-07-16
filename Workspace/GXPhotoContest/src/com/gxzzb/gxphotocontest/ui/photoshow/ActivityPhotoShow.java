package com.gxzzb.gxphotocontest.ui.photoshow;

import com.gxzzb.gxphotocontest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class ActivityPhotoShow extends Activity {
	TabHost tabHost;
	TabWidget tabWidget;
	String[] arrayitem = { "浏览作品", "我的作品", "拍照上传", "相册上传" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photoshow);

		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		tabHost.addTab(tabHost.newTabSpec("tab0")
				.setIndicator(getMenuItem(R.drawable.photo_icon, arrayitem[0]))
				.setContent(R.id.linearLayout_photoshow));
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator(getMenuItem(R.drawable.user_icon, arrayitem[1]))
				.setContent(R.id.linearLayout_photoshow));
		tabHost.addTab(tabHost
				.newTabSpec("tab2")
				.setIndicator(getMenuItem(R.drawable.camera_icon, arrayitem[2]))
				.setContent(R.id.linearLayout_photoshow));
		tabHost.addTab(tabHost
				.newTabSpec("tab3")
				.setIndicator(getMenuItem(R.drawable.upload_icon, arrayitem[3]))
				.setContent(R.id.linearLayout_photoshow));

		tabWidget = tabHost.getTabWidget();

		tabWidget.getChildAt(0).setOnClickListener(new Myevents());
		tabWidget.getChildAt(1).setOnClickListener(new Myevents());
		tabWidget.getChildAt(2).setOnClickListener(new Myevents());
		tabWidget.getChildAt(3).setOnClickListener(new Myevents());

	}

	public View getMenuItem(int imgID, String textID) {
		LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.view_tab_item, null);
		ImageView imgView = (ImageView) ll.findViewById(R.id.imageView_icon);
		imgView.setBackgroundResource(imgID);
		TextView textView = (TextView) ll.findViewById(R.id.textView_icon);
		textView.setText(textID);
		return ll;
	}

	class Myevents implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v == tabWidget.getChildAt(0)) {
				System.out.println("aaa");
			}
			if (v == tabWidget.getChildAt(1)) {
				System.out.println("bbb");
			}
			if (v == tabWidget.getChildAt(2)) {
				System.out.println("ccc");
			}
			if (v == tabWidget.getChildAt(3)) {
				System.out.println("ddd");
			}

		}

	}

}
