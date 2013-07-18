package com.gxzzb.gxphotocontest.ui.photocamera;

import com.gxzzb.gxphotocontest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPhotoCamera extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_photocamera, container, false);
		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
