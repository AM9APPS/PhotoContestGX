package com.gxzzb.gxphotocontest.ui.photoshow;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.ui.photocamera.FragmentPhotoCamera;
import com.gxzzb.gxphotocontest.ui.photoupload.FragmentPhotoUpload;
import com.gxzzb.gxphotocontest.ui.photouser.FragmentPhotoUser;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

public class ActivityPhotoShow extends FragmentActivity {
	FragmentTabHost fragmentTabHost;
	TabWidget tabWidget;
	String[] arrayitem = { "浏览作品", "我的作品", "拍照上传", "相册上传" };
	Class[] fragmentArray = { FragmentPhotoflow.class, FragmentPhotoUser.class,
			FragmentPhotoCamera.class, FragmentPhotoUpload.class };
	int[] imagesitem = { R.drawable.photo_icon, R.drawable.user_icon,
			R.drawable.camera_icon, R.drawable.upload_icon };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photoshow);

		initiView();

		
		// fragmentTabHost.addTab(tabHost.newTabSpec("tab0")
		// .setIndicator(getMenuItem(R.drawable.photo_icon, arrayitem[0]))
		// .setContent(R.id.linearLayout_photo));
		// tabHost.addTab(tabHost.newTabSpec("tab1")
		// .setIndicator(getMenuItem(R.drawable.user_icon, arrayitem[1]))
		// .setContent(R.id.linearLayout_user));
		// tabHost.addTab(tabHost
		// .newTabSpec("tab2")
		// .setIndicator(getMenuItem(R.drawable.camera_icon, arrayitem[2]))
		// .setContent(R.id.linearLayout_camera));
		// tabHost.addTab(tabHost
		// .newTabSpec("tab3")
		// .setIndicator(getMenuItem(R.drawable.upload_icon, arrayitem[3]))
		// .setContent(R.id.linearLayout_upload));
		//
		// tabWidget = tabHost.getTabWidget();

	}

	public void initiView() {

		fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		fragmentTabHost.setup(this, getSupportFragmentManager(),
				R.id.frameLayout_content);

		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			TabSpec tabSpec = fragmentTabHost.newTabSpec(arrayitem[i])
					.setIndicator(getMenuItem(i));
			// 将Tab按钮添加进Tab选项卡中
			fragmentTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			fragmentTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.bottom_bar_bg);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}

		return super.onKeyDown(keyCode, event);
	}

	public View getMenuItem(int i) {
		LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.view_tab_item, null);
		ImageView imgView = (ImageView) ll.findViewById(R.id.imageView_icon);
		imgView.setBackgroundResource(imagesitem[i]);
		TextView textView = (TextView) ll.findViewById(R.id.textView_icon);
		textView.setText(arrayitem[i]);
		return ll;
	}

}
