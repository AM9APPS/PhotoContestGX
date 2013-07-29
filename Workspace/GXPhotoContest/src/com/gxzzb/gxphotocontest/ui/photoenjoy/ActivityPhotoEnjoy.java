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

public class ActivityPhotoEnjoy extends Activity {

	// thinkandroid加载图片
	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;

	Intent intent;
	String httpUrl = null;

	// 每次加载的数目
	ArrayList<BeanImageArrayitem> beanImageArrayitems = null;
	BeanImageContent beanImageContent = null;
	BeanImageArrayitem beanImageArrayitem = null;

	// 总共加载了的view
	ArrayList<View> sumviewphotocontentitems = new ArrayList<View>();
	// 总共加载了的数据
	ArrayList<BeanImageArrayitem> sumbeanImageArrayitems = new ArrayList<BeanImageArrayitem>();
	// 总共加载的赞票textview
	ArrayList<TextView> sumtextviewscores = new ArrayList<TextView>();
	// 总共加载的button
	ArrayList<Button> sumbuttonscores = new ArrayList<Button>();

	LayoutInflater layoutInflater;
	LinearLayout linearLayout_content;

	// 判断想请求是哪个内容
	// 0请求beanImageArrayitems beanImageContent
	//
	//
	int isContent;

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
		isContent = 0;

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
			switch (isContent) {
			case 0:
				DataJsonAnalysisPhotoenjoy dataJsonAnalysisPhotoenjoy = new DataJsonAnalysisPhotoenjoy(
						content);
				beanImageArrayitems = dataJsonAnalysisPhotoenjoy.getArrayList();
				beanImageContent = dataJsonAnalysisPhotoenjoy
						.getbeanImageContent();

				break;
			case 1:

				break;

			default:
				break;
			}

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
				View viewphotocontentitem = layoutInflater.inflate(
						R.layout.view_photocontent_item, null);
				ImageView imageView = (ImageView) viewphotocontentitem
						.findViewById(R.id.imageView_photoenjoy);
				taBitmapCacheWork.loadFormCache(beanImageArrayitem.getTu(),
						imageView);
				TextView textView_tusm = (TextView) viewphotocontentitem
						.findViewById(R.id.textView_tusm);
				textView_tusm.setText(beanImageArrayitem.getTusm());
				TextView textView_scroe = (TextView) viewphotocontentitem
						.findViewById(R.id.textView_scroe);
				textView_scroe.setText("" + beanImageArrayitem.getTpnum());
				System.out.println(beanImageArrayitem.getTu());
				// 保存view
				sumviewphotocontentitems.add(viewphotocontentitem);
				// 保存view相应的数据
				sumbeanImageArrayitems.add(beanImageArrayitem);
				// 保存textviewscore
				sumtextviewscores.add(textView_scroe);
				// 保存button
				sumbuttonscores.add((Button) viewphotocontentitem
						.findViewById(R.id.button_scroe));
				linearLayout_content.addView(viewphotocontentitem);
			}

			// 给每个button添加事件
			for (int i = 0; i < sumbuttonscores.size(); i++) {
				final BeanImageArrayitem beanImageArrayitem = sumbeanImageArrayitems
						.get(i);
				final TextView textView_scroe = sumtextviewscores.get(i);
				sumbuttonscores.get(i).setOnClickListener(
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								textView_scroe.setText(""
										+ beanImageArrayitem.getTuid());
								textView_scroe.postInvalidate();
								System.out.println(""
										+ beanImageContent.getSj());
								// 获取设备号
								TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

							}
						});
			}

		}

	}

}
