package com.gxzzb.gxphotocontest.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.ta.util.http.AsyncHttpClient;

public class HttpAsyncTask extends AsyncTask<String, Integer, String> {

	String strResult;
	Intent intent;
	String httpUrl;
	Context context;
	Class<?> myClass;
	ProgressDialog progressDialog;

	AsyncHttpClient client;
	MyAsyncHttpResponseHandler myAsyncHttpResponseHandler;

	public HttpAsyncTask(Context context1, Class<?> myClass,
			ProgressDialog progressDialog) {
		this.context = context1;
		this.myClass = myClass;
		this.progressDialog = progressDialog;
	}

	public HttpAsyncTask(Context context1, ProgressDialog progressDialog) {
		this.context = context1;
		this.progressDialog = progressDialog;
	}

	@Override
	protected void onPreExecute() {
		progressDialog.show();
		client = new AsyncHttpClient();
		myAsyncHttpResponseHandler = new MyAsyncHttpResponseHandler();
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... httpUrls) {
		httpUrl = httpUrls[0];
		System.out.println(httpUrl);

		client.post(httpUrl, myAsyncHttpResponseHandler);

		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);

	}

	@Override
	protected void onPostExecute(String strResult) {
		super.onPostExecute(strResult);
		progressDialog.dismiss();
	}

}
