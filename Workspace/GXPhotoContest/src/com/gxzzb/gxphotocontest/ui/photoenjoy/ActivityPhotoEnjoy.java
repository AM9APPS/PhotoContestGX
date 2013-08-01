package com.gxzzb.gxphotocontest.ui.photoenjoy;

import java.util.ArrayList;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.bean.BeanImageContentitem;
import com.gxzzb.gxphotocontest.bean.BeanImageContent;
import com.gxzzb.gxphotocontest.bean.BeanTpnum;
import com.gxzzb.gxphotocontest.data.photoenjoy.DataJsonAnalysisPhotoenjoy;
import com.gxzzb.gxphotocontest.data.photoenjoy.DataJsonAnalysisTpnum;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.gxzzb.gxphotocontest.support.bins.SupportMD5;
import com.ta.TAApplication;
import com.ta.util.bitmap.TABitmapCacheWork;
import com.ta.util.bitmap.TADownloadBitmapHandler;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPhotoEnjoy extends Activity {

	// thinkandroid加载图片
	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;

	AsyncHttpClient client;

	Intent intent;

	// 每次加载的数目
	ArrayList<BeanImageContentitem> beanImageContentitems = null;
	BeanImageContent beanImageContent = null;
	BeanImageContentitem beanImageContentitem = null;

	// 总共加载了的view
	ArrayList<View> sumviewphotocontentitems = new ArrayList<View>();
	// 总共加载了的数据
	ArrayList<BeanImageContentitem> sumbeanImageContentitems = new ArrayList<BeanImageContentitem>();

	LayoutInflater layoutInflater;
	LinearLayout linearLayout_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photoenjoy);
		// thinkandroid图片参数设置
		taApplication = new TAApplication();
		taBitmapCacheWork = new TABitmapCacheWork(this);
		taDownloadBitmapHandler = new TADownloadBitmapHandler(this, 480, 400);
		taBitmapCacheWork.setProcessDataHandler(taDownloadBitmapHandler);
		//
		linearLayout_content = (LinearLayout) this
				.findViewById(R.id.linearLayout_content);
		intent = getIntent();

		layoutInflater = getLayoutInflater();
		// 请求数据
		String httpUrl = URLHelper.URL_SJ_VIEW;

		client = new AsyncHttpClient();

		RequestParams params = new RequestParams();

		params.put("id", "" + intent.getIntExtra("id", 0));
		client.post(httpUrl, params, new MyAsyncHttpResponseHandler());

	}

	class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
		BeanTpnum beanTpnum;

		@Override
		public void onSuccess(String content) {
			super.onSuccess(content);
			DataJsonAnalysisPhotoenjoy dataJsonAnalysisPhotoenjoy = new DataJsonAnalysisPhotoenjoy(
					content);
			beanImageContentitems = dataJsonAnalysisPhotoenjoy.getArrayList();
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

			for (int i = 0; i < beanImageContentitems.size(); i++) {
				beanImageContentitem = beanImageContentitems.get(i);
				View viewphotocontentitem = layoutInflater.inflate(
						R.layout.view_photocontent_item, null);
				ImageView imageView = (ImageView) viewphotocontentitem
						.findViewById(R.id.imageView_photoenjoy);
				taBitmapCacheWork.loadFormCache(beanImageContentitem.getTu(),
						imageView);
				TextView textView_tusm = (TextView) viewphotocontentitem
						.findViewById(R.id.textView_tusm);
				textView_tusm.setText(beanImageContentitem.getTusm());
				TextView textView_scroe = (TextView) viewphotocontentitem
						.findViewById(R.id.textView_scroe);
				textView_scroe.setText("" + beanImageContentitem.getTpnum());
				System.out.println(beanImageContentitem.getTu());
				// 保存view
				sumviewphotocontentitems.add(viewphotocontentitem);
				// 保存view相应的数据
				sumbeanImageContentitems.add(beanImageContentitem);

				linearLayout_content.addView(viewphotocontentitem);
			}

			// 给每个button添加事件
			for (int i = 0; i < sumviewphotocontentitems.size(); i++) {
				View viewphotocontentitem = sumviewphotocontentitems.get(i);
				Button buttonscroe = (Button) viewphotocontentitem
						.findViewById(R.id.button_scroe);
				final BeanImageContentitem beanImageContentitemEV = sumbeanImageContentitems
						.get(i);
				buttonscroe.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// 获取button相应的textview
						View vp = (View) v.getParent();
						TextView textView_scores = (TextView) vp
								.findViewById(R.id.textView_scroe);
						TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
						// 获取设备号
						String deviceId = telephonyManager.getDeviceId();
						// 获取手机号码
						String telephonyNumber = telephonyManager
								.getLine1Number();

						// 时间戳
						Long ltimeMillis = System.currentTimeMillis() / 1000;

						Long timeMillis = (ltimeMillis * 2 - 3);
						SupportMD5 supportMD5 = new SupportMD5();
						// 获取32位MD5加密验证码
						String lzm = supportMD5.GetMD5Code(timeMillis
								.toString());
						// 请求参数设置
						RequestParams params = new RequestParams();
						params.put("tuid", String
								.valueOf(beanImageContentitemEV.getTuid()));

						params.put("lzm", lzm);
						params.put("sj", ltimeMillis.toString());
						params.put("tel", telephonyNumber);
						params.put("did", deviceId);

						String httpUrl = URLHelper.URL_SJ_TP;

						client.post(httpUrl, params, new MyHandlerTpnum(
								textView_scores));

					}
				});
			}

		}

		class MyHandlerTpnum extends AsyncHttpResponseHandler {
			private TextView textView;
			private BeanTpnum beanTpnum;

			public MyHandlerTpnum(TextView textView) {
				this.textView = textView;
			}

			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				DataJsonAnalysisTpnum dataJsonAnalysisTpnum = new DataJsonAnalysisTpnum(
						content);
				beanTpnum = dataJsonAnalysisTpnum.getBeanTpnum();
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
				if ("" != beanTpnum.getTpnum()) {
					textView.setText(beanTpnum.getTpnum());
					Toast.makeText(getApplicationContext(), "已赞!!!",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "每个用户只能赞一次!",
							Toast.LENGTH_SHORT).show();
				}

			}

		}

	}

}
