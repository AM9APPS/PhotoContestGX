package com.gxzzb.gxphotocontest.ui.photoenjoy;

import java.util.ArrayList;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.bean.BeanImageArrayitem;
import com.gxzzb.gxphotocontest.bean.BeanImageContent;
import com.gxzzb.gxphotocontest.data.photoenjoy.DataJsonAnalysisPhotoenjoy;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.ta.TAApplication;
import com.ta.util.bitmap.TABitmapCacheWork;
import com.ta.util.bitmap.TADownloadBitmapHandler;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityPhotoEnjoy extends Activity {

	// thinkandroidº”‘ÿÕº∆¨
	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;

	Intent intent;
	String httpUrl = null;
	TextView textView_tusm = null;
	TextView textView_scroe = null;

	ArrayList<BeanImageArrayitem> beanImageArrayitems = null;
	BeanImageContent beanImageContent = null;
	BeanImageArrayitem beanImageArrayitem = null;

	LayoutInflater layoutInflater;
	LinearLayout linearLayout_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photoenjoy);

		taApplication = new TAApplication();
		taBitmapCacheWork = new TABitmapCacheWork(this);
		taDownloadBitmapHandler = new TADownloadBitmapHandler(this, 480, 400);
		taBitmapCacheWork.setProcessDataHandler(taDownloadBitmapHandler);

		linearLayout_content = (LinearLayout) this
				.findViewById(R.id.linearLayout_content);
		intent = getIntent();

		layoutInflater = getLayoutInflater();

		httpUrl = URLHelper.URL_SJ_VIEW;

		AsyncHttpClient client = new AsyncHttpClient();

		RequestParams params = new RequestParams();

		params.put("id", "" + intent.getIntExtra("id", 0));
		client.post(httpUrl, params, new MyAsyncHttpResponseHandler1());

	}

	class MyAsyncHttpResponseHandler1 extends AsyncHttpResponseHandler {
		@Override
		public void onSuccess(String content) {
			super.onSuccess(content);
			System.out.println("wo de " + content);
			DataJsonAnalysisPhotoenjoy dataJsonAnalysisPhotoenjoy = new DataJsonAnalysisPhotoenjoy(
					content);
			beanImageArrayitems = dataJsonAnalysisPhotoenjoy.getArrayList();
			beanImageContent = dataJsonAnalysisPhotoenjoy.getbeanImageContent();

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

			for (int i = 0; i < beanImageArrayitems.size(); i++) {
				beanImageArrayitem = beanImageArrayitems.get(i);
				View view = layoutInflater.inflate(
						R.layout.view_photocontent_item, null);
				ImageView imageView = (ImageView) view
						.findViewById(R.id.imageView_photoenjoy);
				taBitmapCacheWork.loadFormCache(beanImageArrayitem.getTu(),
						imageView);
				TextView textView_tusm = (TextView) view.findViewById(R.id.textView_tusm);
				textView_tusm.setText(beanImageArrayitem.getTusm());
				TextView textView_scroe = (TextView) view.findViewById(R.id.textView_scroe);
				textView_scroe.setText(""+beanImageArrayitem.getTpnum());
				System.out.println(beanImageArrayitem.getTu());
				linearLayout_content.addView(view);
			}

		}

	}

}
