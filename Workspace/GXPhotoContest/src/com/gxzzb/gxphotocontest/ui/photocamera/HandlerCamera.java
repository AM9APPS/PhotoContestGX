package com.gxzzb.gxphotocontest.ui.photocamera;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gxzzb.gxphotocontest.R;
import com.ta.util.http.AsyncHttpResponseHandler;

public class HandlerCamera extends AsyncHttpResponseHandler {
	Context context;
	View view;
	ImageView imageViewtu1;
	EditText editTexttusm1;
	Button buttoncamera;

	LinearLayout linearLayoutaddress;
	LinearLayout linearLayoutupload;

	String isSuccess = null;

	public HandlerCamera(Context context, View view) {
		this.view = view;
		this.context = context;
	}

	@Override
	public void onSuccess(String content) {
		super.onSuccess(content);
		try {
			content = URLDecoder.decode(content, "GBK");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		try {
			JSONObject jsonObject = new JSONObject(content);
			isSuccess = jsonObject.optString("tuid");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println("content:" + content);
	}

	@Override
	public void onStart() {
		super.onStart();
		imageViewtu1 = (ImageView) view.findViewById(R.id.imageView_tu1);
		editTexttusm1 = (EditText) view.findViewById(R.id.editText_tusm1);
		linearLayoutaddress = (LinearLayout) view.findViewById(R.id.linearyout_address);
		linearLayoutupload = (LinearLayout) view.findViewById(R.id.linearlayout_upload);
		buttoncamera = (Button) view.findViewById(R.id.button_camera);
	}

	@Override
	public void onFailure(Throwable error) {
		super.onFailure(error);
	}

	@Override
	public void onFinish() {
		super.onFinish();
		imageViewtu1.setImageResource(R.drawable.ic_launcher);
		editTexttusm1.setVisibility(View.INVISIBLE);
		linearLayoutaddress.setVisibility(View.INVISIBLE);
		linearLayoutupload.setVisibility(View.INVISIBLE);
		buttoncamera.setVisibility(View.VISIBLE);
		
		if (null == isSuccess) {
			Toast.makeText(context, "上传失败!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "上传成功!", Toast.LENGTH_SHORT).show();
		}

	}

}
