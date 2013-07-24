package com.gxzzb.gxphotocontest.ui.photoshow;

import java.util.Random;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.bean.BeanImageitem;
import com.gxzzb.gxphotocontest.data.photoflow.DataJsonAnalysis;
import com.gxzzb.gxphotocontest.data.photoflow.StaticString;
import com.gxzzb.gxphotocontest.net.HttpAsyncTask;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.gxzzb.gxphotocontest.ui.bin.UiItemProgressDialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class FragmentPhotoflow extends Fragment {
	// 数据总数
	int resultCount = 1000;
	// 每次加载数据总数

	View view;

	LayoutInflater inflater;

	ScrollView scrollView;

	LinearLayout linearLayoutcenter;
	LinearLayout linearLayoutleft;
	LinearLayout linearLayoutright;

	BeanImageitem beanImageitem;

	MyScrollViewEvents myScrollViewEvents = new MyScrollViewEvents();

	ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		progressDialog = new UiItemProgressDialog().getprogressDialog(inflater
				.getContext());
		View v = inflater
				.inflate(R.layout.fragment_photoflow, container, false);
		TextView textView_top = (TextView) v.findViewById(R.id.textView_top);
		textView_top.setText("浏览作品");
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
		if (StaticString.eachCount * StaticString.pageCount < resultCount) {
			int isitem = 0;
			for (int i = 0; i < StaticString.eachCount; i++) {

				int k = i + StaticString.eachCount * StaticString.pageCount;

				if (k >= resultCount) {
					break;
				}
				LinearLayout linearLayout = new LinearLayout(
						inflater.getContext());
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				ImageView imageView1 = new ImageView(inflater.getContext());
				TextView textView = new TextView(inflater.getContext());
				textView.setText("aadfdfdsffffffffffffffffffffffffffaaa");
				imageView1.setBackgroundResource(R.drawable.ic_launcher);
				linearLayout.addView(imageView1);
				linearLayout.addView(textView);

				if (0 == isitem) {
					linearLayoutleft.addView(linearLayout);
					isitem = 1;
					System.out.println(linearLayoutleft.getHeight());
				} else if (1 == isitem) {
					linearLayoutcenter.addView(linearLayout);
					isitem = 2;
					System.out.println(linearLayoutcenter.getHeight());
				} else if (2 == isitem) {
					linearLayoutright.addView(linearLayout);
					isitem = 0;
				}

				// linearLayoutleft.addView(imageView1);
				// linearLayoutright.addView(imageView3);
			}

			StaticString.pageCount++;
		}

	}

	public void initiData() {
		// 初始化StaticString.STRRESULT
		String httpUrl = URLHelper.URL_SJ_LIST + "?pagenum="
				+ StaticString.eachCount + "&pageFilter="
				+ StaticString.pageCount;
		HttpAsyncTask httpAsyncTask = new HttpAsyncTask(httpUrl,
				inflater.getContext());
		httpAsyncTask.getDateforHttp();
		if ("" == StaticString.strResUlt) {
			System.out.println("无数据");
		} else {
			System.out.println(StaticString.strResUlt);

			DataJsonAnalysis dataJsonAnalysis = new DataJsonAnalysis(
					StaticString.strResUlt);
			beanImageitem = dataJsonAnalysis.getbean();
		}

	}

	class MyScrollViewEvents implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_UP:
				// 如果触发监听事件，并且有内容，并且ScrollView已经拉到底部，加载一次数据
				System.out.println(StaticString.pageCount + "");
				if (myScrollViewEvents != null
						&& view != null
						&& view.getMeasuredHeight() - 30 <= scrollView
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
