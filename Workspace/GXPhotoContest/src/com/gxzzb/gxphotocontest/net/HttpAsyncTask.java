package com.gxzzb.gxphotocontest.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;

public class HttpAsyncTask extends AsyncTask<String, Integer, String> {

	String strResult;
	Intent intent;
	String httpUrl;
	Context context;
	Class<?> myClass;
	ProgressDialog progressDialog;

	AsyncHttpClient client;

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
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... httpUrls) {
		httpUrl = httpUrls[0];
		System.out.println(httpUrl);

		client.post(httpUrl, new AsyncHttpResponseHandler() {

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

				super.onFinish();

				System.out.println(strResult);
			}

		});

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
