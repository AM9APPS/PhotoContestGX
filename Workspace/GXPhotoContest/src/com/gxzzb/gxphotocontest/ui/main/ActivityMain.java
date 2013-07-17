package com.gxzzb.gxphotocontest.ui.main;

import com.gxzzb.gxphotocontest.R;
//import com.gxzzb.gxphotocontest.ui.photoenjoy.ActivityPhotoEnjoy;
import com.gxzzb.gxphotocontest.ui.photoshow.ActivityPhotoShow;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class ActivityMain extends Activity {

	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button_welcome_anter_btn);
		button.setOnClickListener(new Myevent());
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
