package com.gxzzb.gxphotocontest.ui.photoshow;

import com.gxzzb.gxphotocontest.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class ActivityPhotoShow extends Activity {
    TabHost tabHost;
    String[] arrayitem = {"浏览作品","我的作品","拍照上传","相册上传"};
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photoshow);
		
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		
		tabHost.addTab(tabHost.newTabSpec("0").setIndicator(getMenuItem(R.drawable.photo_icon, arrayitem[0])).setContent(R.id.linearLayout_photoshow));
		tabHost.addTab(tabHost.newTabSpec("1").setIndicator(getMenuItem(R.drawable.user_icon, arrayitem[1])).setContent(R.id.linearLayout_photoshow));
		tabHost.addTab(tabHost.newTabSpec("2").setIndicator(getMenuItem(R.drawable.camera_icon, arrayitem[2])).setContent(R.id.linearLayout_photoshow));
		tabHost.addTab(tabHost.newTabSpec("3").setIndicator(getMenuItem(R.drawable.upload_icon, arrayitem[3])).setContent(R.id.linearLayout_photoshow));
		
	}
	
	public View getMenuItem(int imgID, String textID){
		LinearLayout ll = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.view_tab_item, null);
		ImageView imgView = (ImageView)ll.findViewById(R.id.imageView_icon);
		imgView.setBackgroundResource(imgID);
		TextView textView = (TextView)ll.findViewById(R.id.textView_icon);
		textView.setText(textID);
		return ll;
	}	
}
