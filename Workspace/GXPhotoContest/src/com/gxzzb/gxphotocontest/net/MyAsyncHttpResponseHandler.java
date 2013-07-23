package com.gxzzb.gxphotocontest.net;

import com.ta.util.http.AsyncHttpResponseHandler;

public class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

	String strResult;
	public MyAsyncHttpResponseHandler() {

	}

	@Override
	public void onSuccess(String content) {
		super.onSuccess(content);
		strResult = content;
		
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
		System.out.println(strResult);
		super.onFinish();

	}

	

}
