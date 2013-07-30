package com.gxzzb.gxphotocontest.ui.photoenjoy;

import java.util.ArrayList;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.bean.BeanImageContentitem;
import com.gxzzb.gxphotocontest.bean.BeanImageContent;
import com.gxzzb.gxphotocontest.data.photoenjoy.DataJsonAnalysisPhotoenjoy;
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

public class ActivityPhotoEnjoy extends Activity {

	// thinkandroid����ͼƬ
	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;

	AsyncHttpClient client;

	Intent intent;

	// ÿ�μ��ص���Ŀ
	ArrayList<BeanImageContentitem> beanImageContentitems = null;
	BeanImageContent beanImageContent = null;
	BeanImageContentitem beanImageContentitem = null;

	// �ܹ������˵�view
	ArrayList<View> sumviewphotocontentitems = new ArrayList<View>();
	// �ܹ������˵�����
	ArrayList<BeanImageContentitem> sumbeanImageContentitems = new ArrayList<BeanImageContentitem>();
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

		String httpUrl = URLHelper.URL_SJ_VIEW;
		isContent = 0;

		client = new AsyncHttpClient();

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
				beanImageContentitems = dataJsonAnalysisPhotoenjoy.getArrayList();
				beanImageContent = dataJsonAnalysisPhotoenjoy
						.getbeanImageContent();

				break;
			case 1:

				System.out.println("wo de content" + content);

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
			if (0 == isContent) {
				for (int i = 0; i < beanImageContentitems.size(); i++) {
					beanImageContentitem = beanImageContentitems.get(i);
					View viewphotocontentitem = layoutInflater.inflate(
							R.layout.view_photocontent_item, null);
					ImageView imageView = (ImageView) viewphotocontentitem
							.findViewById(R.id.imageView_photoenjoy);
					taBitmapCacheWork.loadFormCache(
							beanImageContentitem.getTu(), imageView);
					TextView textView_tusm = (TextView) viewphotocontentitem
							.findViewById(R.id.textView_tusm);
					textView_tusm.setText(beanImageContentitem.getTusm());
					TextView textView_scroe = (TextView) viewphotocontentitem
							.findViewById(R.id.textView_scroe);
					textView_scroe
							.setText("" + beanImageContentitem.getTpnum());
					System.out.println(beanImageContentitem.getTu());
					// ����view
					sumviewphotocontentitems.add(viewphotocontentitem);
					// ����view��Ӧ������
					sumbeanImageContentitems.add(beanImageContentitem);
					// ����textviewscore
					sumtextviewscores.add(textView_scroe);
					// ����button
					sumbuttonscores.add((Button) viewphotocontentitem
							.findViewById(R.id.button_scroe));
					linearLayout_content.addView(viewphotocontentitem);
				}
			}
			// ��ÿ��button����¼�
			for (int i = 0; i < sumbuttonscores.size(); i++) {
				final BeanImageContentitem beanImageContentitemEV = sumbeanImageContentitems
						.get(i);
				final TextView textView_scroe = sumtextviewscores.get(i);
				sumbuttonscores.get(i).setOnClickListener(
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								String httpUrl = URLHelper.URL_SJ_TP;
								isContent = 1;
								
								TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
								// ��ȡ�豸��
								String deviceId = telephonyManager
										.getDeviceId();
								System.out.println("deviceId===:"+deviceId);
								// ��ȡ�ֻ�����
								String telephonyNumber = telephonyManager
										.getLine1Number();
                                System.out.println("telephonyNumber===:"+telephonyNumber);
								// ʱ���
								Long ltimeMillis = System.currentTimeMillis();
								System.out.println("ltimeMillis===:" + ltimeMillis);
								
								Long timeMillis = (ltimeMillis * 2 - 3);
								System.out.println("timeMillis===:" + timeMillis);
								SupportMD5 supportMD5 = new SupportMD5();
								// ��ȡ32λMD5������֤��
								String lzm = supportMD5.GetMD5Code(timeMillis
										.toString());
								System.out.println("lzm===:" + lzm);

								RequestParams params = new RequestParams();
								params.put("tuid",String.valueOf(beanImageContentitemEV.getTuid()));
								
								params.put("lzm", lzm);
								params.put("sj", ltimeMillis.toString());
								params.put("tel", telephonyNumber);
								params.put("did", deviceId);

								client.post(httpUrl, params,
										new MyAsyncHttpResponseHandler1());
								
								textView_scroe.setText(""
										+ beanImageContentitemEV.getTuid());
								textView_scroe.postInvalidate();


							}
						});
			}

		}

	}

}
