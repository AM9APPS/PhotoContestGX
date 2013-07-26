package com.gxzzb.gxphotocontest.data.photoenjoy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gxzzb.gxphotocontest.bean.BeanImageArrayitem;
import com.gxzzb.gxphotocontest.bean.BeanImageContent;

public class DataJsonAnalysisPhotoenjoy {
	String strResUlt;
	BeanImageContent beanImageContent;
	BeanImageArrayitem beanImageArrayitem;
	ArrayList< BeanImageArrayitem> beanImageArrayitems ; 

	public DataJsonAnalysisPhotoenjoy(String strResUlt) {
		this.strResUlt = strResUlt;
		beanImageContent = new BeanImageContent();
		beanImageArrayitems = new ArrayList<BeanImageArrayitem>();
		
		dataAnalysis();
	}

	public void dataAnalysis() {
		try {
			strResUlt = URLDecoder.decode(strResUlt, "GBK");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println(strResUlt);
		try {
			JSONObject jsonObject = new JSONObject(strResUlt);
			JSONArray jsonArray = jsonObject.optJSONArray("tu");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObjectimageArrayitem = jsonArray.optJSONObject(i);
				beanImageArrayitem = new BeanImageArrayitem();
				beanImageArrayitem.setTpnum(jsonObjectimageArrayitem.optInt("tpnum"));
				beanImageArrayitem.setTu(jsonObjectimageArrayitem.optString("tu"));
				beanImageArrayitem.setTuid(jsonObjectimageArrayitem.optInt("tuid"));
				beanImageArrayitem.setTusm(jsonObjectimageArrayitem.optString("tusm"));
				beanImageArrayitems.add(beanImageArrayitem);

			}

			beanImageContent.setId(jsonObject.optInt("id"));
			beanImageContent.setUser(jsonObject.optString("user"));
			beanImageContent.setSj(jsonObject.optInt("sj"));
			beanImageContent.setDz(jsonObject.optString("dz"));
			beanImageContent.setWeb(jsonObject.optString("web"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public BeanImageContent getbeanImageContent(){
		return beanImageContent;
	}
	
	public ArrayList< BeanImageArrayitem> getArrayList(){
		return beanImageArrayitems;
		
	}

}
