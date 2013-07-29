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

	// thinkandroid����ͼƬ
	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;

	Intent intent;
	String httpUrl = null;

	// ÿ�μ��ص���Ŀ
	ArrayList<BeanImageArrayitem> beanImageArrayitems = null;
	BeanImageContent beanImageContent = null;
	BeanImageArrayitem beanImageArrayitem = null;

	// �ܹ������˵�view
	ArrayList<View> sumviewphotocontentitems = new ArrayList<View>();
	// �ܹ������˵�����
	ArrayList<BeanImageArrayitem> sumbeanImageArrayitems = new ArrayList<BeanImageArrayitem>();
	// �ܹ����ص���Ʊtextview
	ArrayList<TextView> sumtextviewscores = new ArrayList<TextView>();
	// �ܹ����ص�button
	ArrayList<Button> sumbuttonscores = new ArrayList<Button>();

	LayoutInflater layoutInflater;
	LinearLayout linearLayout_content;

	// �ж����������ĸ�����
	// 0����beanImageArrayitems beanImageContent
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
				// ����view
				sumviewphotocontentitems.add(viewphotocontentitem);
				// ����view��Ӧ������
				sumbeanImageArrayitems.add(beanImageArrayitem);
				// ����textviewscore
				sumtextviewscores.add(textView_scroe);
				// ����button
				sumbuttonscores.add((Button) viewphotocontentitem
						.findViewById(R.id.button_scroe));
				linearLayout_content.addView(viewphotocontentitem);
			}

			// ��ÿ��button����¼�
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
								// ��ȡ�豸��
								TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

							}
						});
			}

		}

	}

}
