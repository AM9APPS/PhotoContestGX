package com.gxzzb.gxphotocontest.ui.main;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.data.photoflow.StaticString;
import com.gxzzb.gxphotocontest.net.HttpAsyncTask;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.gxzzb.gxphotocontest.ui.photoshow.ActivityPhotoShow;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class ActivityMain extends Activity {

	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button_welcome_anter_btn);
		button.setOnClickListener(new Myevent());
		//初次加载数据获取
		String httpUrl = URLHelper.URL_SJ_LIST + "?pagenum=" + StaticString.eachCount + "&pageFilter="
				+ StaticString.pageCount;
		HttpAsyncTask httpAsyncTask = new HttpAsyncTask(httpUrl,
				this);
		httpAsyncTask.getDateforHttp();
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
}
