package com.gxzzb.gxphotocontest.ui.photoshow;

import java.util.ArrayList;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.bean.BeanFilter;
import com.gxzzb.gxphotocontest.bean.BeanImageitem;
import com.gxzzb.gxphotocontest.data.photoflow.DataJsonAnalysisPhotoflow;
import com.gxzzb.gxphotocontest.datas.StaticString;
import com.gxzzb.gxphotocontest.net.HttpAsyncTask;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.gxzzb.gxphotocontest.ui.bins.UiItemProgressDialog;
import com.gxzzb.gxphotocontest.ui.photoenjoy.ActivityPhotoEnjoy;
import com.ta.TAApplication;
import com.ta.util.bitmap.TABitmapCacheWork;
import com.ta.util.bitmap.TADownloadBitmapHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentPhotoflow extends Fragment {
	// scrollview里面的view
	View view;
	// 判断是否有数据
	BeanFilter beanFilter;
	// 每次加载数据的list
	ArrayList<BeanImageitem> beanImageitems;
	// 总共加载了的数据
	ArrayList<BeanImageitem> sumbBeanImageitems = new ArrayList<BeanImageitem>();
	// 总共添加了的view
	ArrayList<View> sumViewPhotoitems = new ArrayList<View>();
	//
	LayoutInflater inflater;
	ScrollView scrollView;
	// thinkandroid加载图片
	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;
	//
	LinearLayout linearLayoutcenter;
	LinearLayout linearLayoutleft;
	LinearLayout linearLayoutright;
	//
	MyScrollViewEvents myScrollViewEvents = new MyScrollViewEvents();
	//
	ProgressDialog progressDialog;
	//
	BeanImageitem beanImageitem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		// 屏幕宽高
		int screenW = getResources().getDisplayMetrics().widthPixels;
		// int screenH = getResources().getDisplayMetrics().heightPixels;

		taApplication = new TAApplication();
		taBitmapCacheWork = new TABitmapCacheWork(inflater.getContext());
		taDownloadBitmapHandler = new TADownloadBitmapHandler(
				inflater.getContext(), 100, 200);

		taBitmapCacheWork.setProcessDataHandler(taDownloadBitmapHandler);
		// taBitmapCacheWork.setFileCache(taApplication.getFileCache());

		progressDialog = new UiItemProgressDialog().getprogressDialog(inflater
				.getContext());

		View v = inflater
				.inflate(R.layout.fragment_photoflow, container, false);
		TextView textView_top = (TextView) v.findViewById(R.id.textView_top);
		textView_top.setText("作品欣赏");
		textView_top.setVisibility(View.VISIBLE);

		scrollView = (ScrollView) v.findViewById(R.id.scrollView_photoflow);
		scrollView.setOnTouchListener(myScrollViewEvents);

		view = scrollView.getChildAt(0);
		// 计算三个linearlayout宽
		int linearW = (screenW - 20) / 3;

		linearLayoutleft = (LinearLayout) v
				.findViewById(R.id.linearLayout_photoflowleft);

		linearLayoutcenter = (LinearLayout) v
				.findViewById(R.id.linearLayout_photoflowcenter);

		linearLayoutright = (LinearLayout) v
				.findViewById(R.id.linearLayout_photoflowright);

		// 设置三个linearlayout的宽度参数
		LayoutParams layoutleftParams = (LayoutParams) linearLayoutleft
				.getLayoutParams();
		layoutleftParams.width = linearW;
		linearLayoutleft.setLayoutParams(layoutleftParams);

		LayoutParams layoutcenterParams = (LayoutParams) linearLayoutcenter
				.getLayoutParams();
		layoutcenterParams.width = linearW;
		linearLayoutcenter.setLayoutParams(layoutcenterParams);

		LayoutParams layoutrightParams = (LayoutParams) linearLayoutright
				.getLayoutParams();
		layoutrightParams.width = linearW;
		linearLayoutright.setLayoutParams(layoutrightParams);

		addData();
		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	public void addData() {
		initiData();

		if (beanFilter.getFilter().equals("ok")) {

			int isitem = 0;
			for (int i = 0; i < beanImageitems.size(); i++) {
				beanImageitem = beanImageitems.get(i);
				sumbBeanImageitems.add(beanImageitem);
				View view_photo_item = inflater.inflate(
						R.layout.view_photoflow_item, null);
				ImageView imageView = (ImageView) view_photo_item
						.findViewById(R.id.imageView_photoflw_item);

				taBitmapCacheWork.loadFormCache(beanImageitem.getTu(),
						imageView);

				TextView textView_dz = (TextView) view_photo_item
						.findViewById(R.id.textView_dz);

				textView_dz.setText(beanImageitem.getDz());

				TextView textView_scroe = (TextView) view_photo_item
						.findViewById(R.id.textView_scroe);
				textView_scroe.setText("" + beanImageitem.getTpnum());

				sumViewPhotoitems.add(view_photo_item);

				if (0 == isitem) {
					linearLayoutleft.addView(view_photo_item);
					if (linearLayoutleft.getChildCount() > 16) {
						linearLayoutleft.removeView(linearLayoutleft
								.getChildAt(0));
					}
					isitem = 1;
				} else if (1 == isitem) {
					linearLayoutcenter.addView(view_photo_item);
					if (linearLayoutcenter.getChildCount() > 16) {
						linearLayoutcenter.removeView(linearLayoutcenter
								.getChildAt(0));
					}
					isitem = 2;
				} else if (2 == isitem) {
					linearLayoutright.addView(view_photo_item);
					if (linearLayoutright.getChildCount() > 16) {
						linearLayoutright.removeView(linearLayoutright
								.getChildAt(0));
					}
					isitem = 0;
				}

			}

			// 给每个view添加事件
			for (int i = 0; i < sumViewPhotoitems.size(); i++) {
				// 取出每个view所对应的数据
				final BeanImageitem beanImageitempresent = sumbBeanImageitems
						.get(i);
				sumViewPhotoitems.get(i).setOnClickListener(
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								Intent intent = new Intent(inflater
										.getContext(), ActivityPhotoEnjoy.class);

								intent.putExtra("id",
										beanImageitempresent.getId());
								// intent.putExtra("tusm",
								// beanImageitempresent.getTusm());
								// intent.putExtra("dz",
								// beanImageitempresent.getDz());
								// intent.putExtra("sj",
								// beanImageitempresent.getSj());
								// intent.putExtra("tpnum",
								// beanImageitempresent.getTpnum());
								// intent.putExtra("did",
								// beanImageitempresent.getDid());
								// intent.putExtra("tuid",
								// beanImageitempresent.getTuid());

								startActivity(intent);

							}
						});
			}

			StaticString.pageCount++;

		} else {
			StaticString.pageCount = 0;
			Toast.makeText(inflater.getContext(), "没有作品了!", Toast.LENGTH_SHORT)
					.show();
		}

	}

	public void initiData() {
		// 初始化
		String httpUrl = URLHelper.URL_SJ_LIST + "?pagenum="
				+ StaticString.eachCount + "&pageFilter="
				+ StaticString.pageCount;
		HttpAsyncTask httpAsyncTask = new HttpAsyncTask(httpUrl,
				inflater.getContext());
		httpAsyncTask.initistrResultforHttp();

		if ("" == StaticString.strResUlt) {
			System.out.println("无数据");
		} else {
			System.out.println(StaticString.strResUlt);
			// 初始化bean
			DataJsonAnalysisPhotoflow dataJsonAnalysis = new DataJsonAnalysisPhotoflow(
					StaticString.strResUlt);
			beanImageitems = dataJsonAnalysis.getArrayList();
			beanFilter = dataJsonAnalysis.getBeanFilter();
		}

	}

	class MyScrollViewEvents implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// 事件触发，有内容，滑动到底部则加载数据
			System.out.println(StaticString.pageCount + "");
			if (myScrollViewEvents != null
					&& view != null
					&& view.getMeasuredHeight() - 50 <= scrollView.getScrollY()
							+ scrollView.getHeight()) {
				addData();
			}

			return false;
		}

	}

}
