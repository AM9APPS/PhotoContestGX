package com.gxzzb.gxphotocontest.ui.photouser;

import com.gxzzb.gxphotocontest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
		ListView listView = (ListView) v.findViewById(R.id.listView_photouser);
		ListAdapterPhotoUser listAdapterPhotoUser = new ListAdapterPhotoUser(
				inflater);
		listView.setAdapter(listAdapterPhotoUser);
		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
