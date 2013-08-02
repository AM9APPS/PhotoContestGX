package com.gxzzb.gxphotocontest.ui.photouser;

import java.util.ArrayList;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.bean.BeanFilter;
import com.gxzzb.gxphotocontest.bean.BeanImageitem;
import com.gxzzb.gxphotocontest.data.photoflow.DataJsonAnalysisPhotoflow;
import com.gxzzb.gxphotocontest.ui.photoenjoy.ActivityPhotoEnjoy;
import com.ta.TAApplication;
import com.ta.util.bitmap.TABitmapCacheWork;
import com.ta.util.bitmap.TADownloadBitmapHandler;
import com.ta.util.http.AsyncHttpResponseHandler;

public class HandlerUser extends AsyncHttpResponseHandler {
	ArrayList<BeanImageitem> beanImageitems;
	LayoutInflater inflater;
	View view;

	BeanFilter beanFilter;

	TAApplication taApplication;
	TABitmapCacheWork taBitmapCacheWork;
	TADownloadBitmapHandler taDownloadBitmapHandler;

	ArrayList<BeanImageitem> sumbeanImageitems;
	ArrayList<View> sumviewphotouseritems;

	public HandlerUser(LayoutInflater inflater, View view) {
		this.inflater = inflater;
		this.view = view;
	}

	@Override
	public void onSuccess(String content) {
		super.onSuccess(content);
		System.out.println(content);
		DataJsonAnalysisPhotoflow dataJsonAnalysis = new DataJsonAnalysisPhotoflow(
				content);
		beanImageitems = dataJsonAnalysis.getArrayList();
		beanFilter = dataJsonAnalysis.getBeanFilter();
	}

	@Override
	public void onStart() {
		super.onStart();
		taApplication = new TAApplication();
		taBitmapCacheWork = new TABitmapCacheWork(inflater.getContext());
		taDownloadBitmapHandler = new TADownloadBitmapHandler(
				inflater.getContext(), 100, 200);
		sumbeanImageitems = new ArrayList<BeanImageitem>();
		sumviewphotouseritems = new ArrayList<View>();

	}

	@Override
	public void onFailure(Throwable error) {
		super.onFailure(error);
	}

	@Override
	public void onFinish() {
		super.onFinish();
		if (beanFilter.getFilter().equals("ok")) {
			for (int i = 0; i < beanImageitems.size(); i++) {
				BeanImageitem beanImageitem = beanImageitems.get(i);

				TextView textViewname = (TextView) view
						.findViewById(R.id.textView_name);
				textViewname.setText("" + beanImageitem.getDid());

				View viewphotouseritem = inflater.inflate(
						R.layout.view_photouser_item, null);
				ImageView imageViewcontent = (ImageView) viewphotouseritem
						.findViewById(R.id.imageView_content);
				TextView textViewcontent = (TextView) viewphotouseritem
						.findViewById(R.id.textView_content);
				TextView textViewscroe = (TextView) viewphotouseritem
						.findViewById(R.id.textView_tpnum);
				TextView textViewtiem = (TextView) viewphotouseritem
						.findViewById(R.id.textView_user_tiem);

				taBitmapCacheWork.loadFormCache(beanImageitem.getTu(),
						imageViewcontent);
				textViewcontent.setText(beanImageitem.getTusm());
				textViewscroe.setText("" + beanImageitem.getTpnum());
				textViewtiem.setText(beanImageitem.getSj());
				LinearLayout linearlayoutphotouser = (LinearLayout) view
						.findViewById(R.id.LinearLayout_photouser);

				sumbeanImageitems.add(beanImageitem);
				sumviewphotouseritems.add(viewphotouseritem);

				linearlayoutphotouser.addView(viewphotouseritem);

			}

			for (int i = 0; i < sumviewphotouseritems.size(); i++) {
				View viewphotouseritem = sumviewphotouseritems.get(i);
				final BeanImageitem beanImageitemEV = sumbeanImageitems.get(i);
				viewphotouseritem.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(inflater.getContext(),
								ActivityPhotoEnjoy.class);
						intent.putExtra("id", beanImageitemEV.getId());
						inflater.getContext().startActivity(intent);
					}
				});
			}

		} else {
			Toast.makeText(inflater.getContext(), "您还没上传有作品哦!",
					Toast.LENGTH_SHORT).show();
		}

	}

}
