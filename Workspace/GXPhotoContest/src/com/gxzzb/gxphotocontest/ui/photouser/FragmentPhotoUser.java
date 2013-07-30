package com.gxzzb.gxphotocontest.ui.photouser;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.ta.TAApplication;
import com.ta.util.bitmap.TABitmapCacheWork;
import com.ta.util.bitmap.TADownloadBitmapHandler;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentPhotoUser extends Fragment {

	// thinkandroid加载图片
	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;

	AsyncHttpClient client = new AsyncHttpClient();
	LayoutInflater inflater;

	LinearLayout linearLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View v = inflater
				.inflate(R.layout.fragment_photouser, container, false);
		TextView textView_tob = (TextView) v.findViewById(R.id.textView_top);
		textView_tob.setText("我的作品");
		textView_tob.setVisibility(View.VISIBLE);

		linearLayout = (LinearLayout) v
				.findViewById(R.id.LinearLayout_photouser);

		TelephonyManager telephonyManager = (TelephonyManager) inflater
				.getContext().getSystemService(Context.TELEPHONY_SERVICE);
		// 获取设备号
		String deviceId = telephonyManager.getDeviceId();
		//请求接口
		String httpUrl = URLHelper.URL_SJ_PERSONAL;
		//请求参数
		RequestParams params = new RequestParams();
		params.put("did", deviceId);
		//开始请求
		client.post(httpUrl, params, new MyAsyncHttpResponseHandler());

		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

		@Override
		public void onSuccess(String content) {
			super.onSuccess(content);
			System.out.println(content);
		}

		@Override
		public void onStart() {
			super.onStart();
		}

		@Override
		public void onFailure(Throwable error) {
			super.onFailure(error);
		}

		@Override
		public void onFinish() {
			super.onFinish();
			for (int i = 0; i < 10; i++) {
				View photo_user_item = inflater.inflate(
						R.layout.view_photouser_item, null);
				TextView textView_user_item = (TextView) photo_user_item
						.findViewById(R.id.textView_user_tiem);
				textView_user_item.setText("" + i);
				linearLayout.addView(photo_user_item);
			}

		}

	}

}
