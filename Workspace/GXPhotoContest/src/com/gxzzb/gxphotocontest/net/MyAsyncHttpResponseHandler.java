package com.gxzzb.gxphotocontest.net;

import android.app.ProgressDialog;

import com.gxzzb.gxphotocontest.data.photoflow.StaticString;
import com.ta.util.http.AsyncHttpResponseHandler;

public class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
	ProgressDialog progressDialog;
	public MyAsyncHttpResponseHandler(ProgressDialog progressDialog){
		this.progressDialog = progressDialog;
	}

	@Override
	public void onSuccess(String content) {
		super.onSuccess(content);
		StaticString.strResUlt = content;
	}

	@Override
	public void onStart() {
		super.onStart();
		progressDialog.show();
	}

	@Override
	public void onFailure(Throwable error) {
		super.onFailure(error);
	}

	@Override
	public void onFinish() {
		super.onFinish();
		progressDialog.dismiss();

	}

}
