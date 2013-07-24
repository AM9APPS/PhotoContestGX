package com.gxzzb.gxphotocontest.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.ta.util.http.AsyncHttpClient;

public class HttpAsyncTask {

	String strResult;
	Intent intent;
	String httpUrl;
	Context context;
	Class<?> myClass;
	ProgressDialog progressDialog;

	AsyncHttpClient client;
	MyAsyncHttpResponseHandler myAsyncHttpResponseHandler;

	public HttpAsyncTask(String httpUrl, Context context) {
		this.context = context;
		this.httpUrl = httpUrl;
		client = new AsyncHttpClient();
		progressDialog = new ProgressDialog(context);
		myAsyncHttpResponseHandler = new MyAsyncHttpResponseHandler(
				progressDialog);
	}

	public void getDateforHttp() {
		client.post(httpUrl, myAsyncHttpResponseHandler);
	}

}
