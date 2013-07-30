package com.gxzzb.gxphotocontest.ui.photoupload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gxzzb.gxphotocontest.R;

public class FragmentPhotoUpload extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_photoupload, container,
				false);
		startActivity(new Intent(getActivity(), PhotoUpload.class));
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
