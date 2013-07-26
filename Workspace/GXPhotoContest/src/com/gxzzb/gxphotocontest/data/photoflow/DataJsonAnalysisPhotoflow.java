package com.gxzzb.gxphotocontest.data.photoflow;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gxzzb.gxphotocontest.bean.BeanImageitem;

public class DataJsonAnalysisPhotoflow {
	String strResUlt;
	ArrayList<BeanImageitem> beanImageitems;

	public DataJsonAnalysisPhotoflow(String strResUlt) {
		this.strResUlt = strResUlt;
		beanImageitems = new ArrayList<BeanImageitem>();
		dataAnalysis();
	}

	public void dataAnalysis() {
		// ת�����뼯
		try {
			strResUlt = URLDecoder.decode(strResUlt, "GBK");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// �ӵ�15���ַ���ʼ��ȡ
		strResUlt = strResUlt.substring(15, strResUlt.length());

		try {

			JSONObject jsonObject = new JSONObject(strResUlt);
			System.out.println("strResUlt:" + strResUlt);
			JSONArray jsonArray = jsonObject.optJSONArray("listitem");

			for (int i = 0; i < jsonArray.length(); i++) {
				BeanImageitem beanImageitem = new BeanImageitem();
				JSONObject objectitem = jsonArray.optJSONObject(i);
				// System.out.println("arrayObject:"+arrayObject);
				// ��ʼ��bean����bean�浽������
				beanImageitem.setId(objectitem.optInt("id"));
				beanImageitem.setTu(objectitem.optString("tu"));
				beanImageitem.setTusm(objectitem.optString("tusm"));
				beanImageitem.setTuid(objectitem.optInt("tuid"));
				beanImageitem.setTpnum(objectitem.optInt("tpnum"));
				beanImageitem.setSj(objectitem.optString("sj"));
				beanImageitem.setDz(objectitem.optString("dz"));
				beanImageitem.setDid(objectitem.optInt("did"));
				beanImageitem.setTuw(objectitem.optInt("tuw"));
				beanImageitem.setTuh(objectitem.optInt("tuh"));
				beanImageitems.add(beanImageitem);

				System.out.println(beanImageitem.getDz());
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<BeanImageitem> getArrayList() {
		return beanImageitems;
	}

}
