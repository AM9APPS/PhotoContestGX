package com.gxzzb.gxphotocontest.ui.photouser;

import com.gxzzb.gxphotocontest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentPhotoUser extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.fragment_photouser, container, false);
		TextView textView_tob = (TextView) v.findViewById(R.id.textView_top);
		textView_tob.setText("我的作品");
		textView_tob.setVisibility(View.VISIBLE);
		
		LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.LinearLayout_photouser);
		for (int i = 0; i < 10; i++) {
			View photo_user = inflater.inflate(R.layout.view_photouser_item,
					null);
			TextView textView_user_item = (TextView) photo_user.findViewById(R.id.textView_user_tiem);
			textView_user_item.setText(""+i);
			linearLayout.addView(photo_user);
		}
		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
