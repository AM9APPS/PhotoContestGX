package com.gxzzb.gxphotocontest.ui.photocamera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gxzzb.gxphotocontest.R;
import com.gxzzb.gxphotocontest.net.URLHelper;
import com.gxzzb.gxphotocontest.support.bins.SupportBitmap;
import com.gxzzb.gxphotocontest.support.bins.SupportMD5;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.RequestParams;

public class FragmentPhotoCamera extends Fragment {

	LayoutInflater inflater;
	View view;
	Button buttoncamera;
	Bitmap bitmap;

	String base64;

	ImageView imageViewtu1;
	EditText editTexttusm1;

	LinearLayout linearLayoutaddress;
	LinearLayout linearLayoutupload;

	EditText editTextdz1;
	EditText editTextdz2;
	EditText editTextdz3;
	EditText editTextdz4;

	String tusm1;

	String dz1;
	String dz2;
	String dz3;
	String dz4;
	String dz5;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		view = inflater.inflate(R.layout.fragment_photocamera, container, false);
		// startActivity(new Intent(getActivity(), CameraUpLoad.class));
		buttoncamera = (Button) view.findViewById(R.id.button_camera);
		buttoncamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 0);

			}
		});

		return view;
	}
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && null != data) {
           
			// 获取bitmap
			Bundle extras = data.getExtras();
			bitmap = (Bitmap) extras.get("data");
			// bitmap保存到SD卡
			SupportBitmap supportBitmap = new SupportBitmap(bitmap);
			base64 = supportBitmap.getbase64();
			//
			buttoncamera.setVisibility(View.INVISIBLE);

			imageViewtu1 = (ImageView) view.findViewById(R.id.imageView_tu1);
			imageViewtu1.setImageBitmap(bitmap);

			editTexttusm1 = (EditText) view.findViewById(R.id.editText_tusm1);
			editTexttusm1.setVisibility(View.VISIBLE);

			linearLayoutaddress = (LinearLayout) view
					.findViewById(R.id.linearyout_address);
			linearLayoutaddress.setVisibility(View.VISIBLE);

			editTextdz1 = (EditText) linearLayoutaddress
					.findViewById(R.id.editText_dz1);
			editTextdz2 = (EditText) linearLayoutaddress
					.findViewById(R.id.editText_dz2);
			editTextdz3 = (EditText) linearLayoutaddress
					.findViewById(R.id.editText_dz3);
			editTextdz4 = (EditText) linearLayoutaddress
					.findViewById(R.id.editText_dz4);
			
			dz1 = null;
			dz2 = null;
			dz3 = null;
			dz4 = null;
			dz5 = null;

			linearLayoutupload = (LinearLayout) view
					.findViewById(R.id.linearlayout_upload);
			linearLayoutupload.setVisibility(View.VISIBLE);

			Button buttoncancel = (Button) linearLayoutupload
					.findViewById(R.id.button_cancel);
			Button buttonupload = (Button) linearLayoutupload
					.findViewById(R.id.button_upload);

			buttonupload.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					tusm1 = editTexttusm1.getText().toString();
					dz1 = editTextdz1.getText().toString();
					dz2 = editTextdz2.getText().toString();
					dz3 = editTextdz3.getText().toString();
					dz4 = editTextdz4.getText().toString();
					dz5 = dz1 + dz2 + dz3 + dz4;

					String httpUrl = URLHelper.URL_SJ_ADD;
					TelephonyManager telephonyManager = (TelephonyManager) inflater
							.getContext().getSystemService(
									Context.TELEPHONY_SERVICE);
					// 获取设备号
					String deviceId = telephonyManager.getDeviceId();
					// 获取手机号码
					String telephonyNumber = telephonyManager.getLine1Number();
					// 时间戳
					Long ltimeMillis = System.currentTimeMillis() / 1000;
					Long timeMillis = (ltimeMillis * 2 - 3);
					SupportMD5 supportMD5 = new SupportMD5();
					// 获取32位MD5加密验证码
					String lzm = supportMD5.GetMD5Code(timeMillis.toString());

					// 类别 1为拍照上传，2为相册上传
					String lb = "1";
					//
					if (tusm1.equals("")) {
						Toast.makeText(inflater.getContext(), "图片说明不能为空!",
								Toast.LENGTH_SHORT).show();
					} else {
						RequestParams params = new RequestParams();

						// 参数设置
						params.put("tu1", base64);
						params.put("tusm1", tusm1);
						params.put("tel", telephonyNumber);
						params.put("sj", ltimeMillis.toString());
						params.put("dz1", dz1);
						params.put("dz2", dz2);
						params.put("dz3", dz3);
						params.put("dz4", dz4);
						params.put("dz5", dz5);
						params.put("lb", lb);
						params.put("did", deviceId);
						params.put("lzm", lzm);
						// 开始上传图片
						AsyncHttpClient client = new AsyncHttpClient();
						client.post(httpUrl, params, new HandlerCamera(inflater.getContext(),view));

					}
				}
			});

			buttoncancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					imageViewtu1.setImageResource(R.drawable.ic_launcher);
					editTexttusm1.setVisibility(View.INVISIBLE);
					linearLayoutaddress.setVisibility(View.INVISIBLE);
					linearLayoutupload.setVisibility(View.INVISIBLE);
					buttoncamera.setVisibility(View.VISIBLE);
				}
			});

		}

	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
