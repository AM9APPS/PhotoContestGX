package com.gxzzb.gxphotocontest.ui.bins;

import android.app.ProgressDialog;
import android.content.Context;

public class UiItemProgressDialog {
	private ProgressDialog progressDialog = null;

	public ProgressDialog getprogressDialog(Context context) {
		if (null == progressDialog) {
			progressDialog = new ProgressDialog(context);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setCancelable(false);
			progressDialog.setTitle("");
			progressDialog.setMessage("Ŭ�������У����Ե�...");
		}
		return progressDialog;

	}
}
