package com.gxzzb.gxphotocontest.ui.photoshow;

import java.util.ArrayList;

import com.gxzzb.gxphotocontest.R;
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

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class FragmentPhotoflow extends Fragment {
	// scrollview里面的view
	View view;
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

		taApplication = new TAApplication();
		taBitmapCacheWork = new TABitmapCacheWork(inflater.getContext());
		taDownloadBitmapHandler = new TADownloadBitmapHandler(
				inflater.getContext(), 200);

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

		linearLayoutleft = (LinearLayout) v
				.findViewById(R.id.linearLayout_photoflowleft);

		linearLayoutcenter = (LinearLayout) v
				.findViewById(R.id.linearLayout_photoflowcenter);

		linearLayoutright = (LinearLayout) v
				.findViewById(R.id.linearLayout_photoflowright);
		addData();
		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	public void addData() {
		initiData();
		int isitem = 0;
		for (int i = 0; i < beanImageitems.size(); i++) {
			beanImageitem = beanImageitems.get(i);
			sumbBeanImageitems.add(beanImageitem);
			View view_photo_item = inflater.inflate(
					R.layout.view_photoflow_item, null);
			ImageView imageView = (ImageView) view_photo_item
					.findViewById(R.id.imageView_photoflw_item);

			taBitmapCacheWork.loadFormCache(beanImageitem.getTu(), imageView);

			TextView textView_dz = (TextView) view_photo_item
					.findViewById(R.id.textView_dz);

			textView_dz.setText(beanImageitem.getDz());

			TextView textView_scroe = (TextView) view_photo_item
					.findViewById(R.id.textView_scroe);
			textView_scroe.setText("" + beanImageitem.getTpnum());

			sumViewPhotoitems.add(view_photo_item);

			if (0 == isitem) {
				linearLayoutleft.addView(view_photo_item);
				isitem = 1;
			} else if (1 == isitem) {
				linearLayoutcenter.addView(view_photo_item);
				isitem = 2;
			} else if (2 == isitem) {
				linearLayoutright.addView(view_photo_item);
				isitem = 0;
			}

		}
		// 给每个view添加事件
		for (int i = 0; i < sumViewPhotoitems.size(); i++) {
			// 取出每个view所对应的数据
			final BeanImageitem beanImageitempresent = sumbBeanImageitems
					.get(i);
			sumViewPhotoitems.get(i).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(inflater.getContext(),
							ActivityPhotoEnjoy.class);

					intent.putExtra("id", beanImageitempresent.getId());
					// intent.putExtra("tusm", beanImageitempresent.getTusm());
					// intent.putExtra("dz", beanImageitempresent.getDz());
					// intent.putExtra("sj", beanImageitempresent.getSj());
					// intent.putExtra("tpnum",
					// beanImageitempresent.getTpnum());
					// intent.putExtra("did", beanImageitempresent.getDid());
					// intent.putExtra("tuid", beanImageitempresent.getTuid());

					startActivity(intent);

				}
			});
		}

		StaticString.pageCount++;

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
		}

	}

	class MyScrollViewEvents implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				// 事件触发，有内容，滑动到底部则加载数据
				System.out.println(StaticString.pageCount + "");
				if (myScrollViewEvents != null
						&& view != null
						&& view.getMeasuredHeight() - 50 <= scrollView
								.getScrollY() + scrollView.getHeight()) {
					addData();
				}
				break;

			default:
				break;
			}

			return false;
		}

	}

}
