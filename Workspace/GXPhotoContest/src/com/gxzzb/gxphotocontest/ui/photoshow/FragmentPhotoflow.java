package com.gxzzb.gxphotocontest.ui.photoshow;

import java.util.Random;

import com.gxzzb.gxphotocontest.R;

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
	int eachCount = 3;
	// 加载了多少次
	int pageCount = 0;
	// 第一次加载数
	int firtstCount = 6;
	// 判断是否是第一次加载
	boolean isfirst = true;

	View view;

	LayoutInflater inflater;

	ScrollView scrollView;

	LinearLayout linearLayoutcenter;
	LinearLayout linearLayoutleft;
	LinearLayout linearLayoutright;

	MyScrollViewEvents myScrollViewEvents = new MyScrollViewEvents();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
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

		if (eachCount * pageCount < resultCount) {

			for (int i = 0; i < eachCount; i++) {

				int k = i + eachCount * pageCount;

				if (k >= resultCount) {
					break;
				}
				LinearLayout linearLayout = new LinearLayout(
						inflater.getContext());
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				ImageView imageView1 = new ImageView(inflater.getContext());
				TextView textView = new TextView(inflater.getContext());
				textView.setText("aadfdfdsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffaaa");
				imageView1
						.setBackgroundResource(R.drawable.welcome_anter_btn_press);
				linearLayout.addView(imageView1);
				linearLayout.addView(textView);
				int isitem = new Random().nextInt(3);
				System.out.println(isitem);
				if (0 == isitem) {
					linearLayoutleft.addView(linearLayout);
					System.out.println(linearLayoutleft.getHeight());
				}
				if (1 == isitem) {
					linearLayoutcenter.addView(linearLayout);
					System.out.println(linearLayoutcenter.getHeight());
				}
				if (2 == isitem) {
					linearLayoutright.addView(linearLayout);
				}

				// linearLayoutleft.addView(imageView1);
				// linearLayoutright.addView(imageView3);
			}

			pageCount++;
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
				System.out.println(pageCount + "");
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
