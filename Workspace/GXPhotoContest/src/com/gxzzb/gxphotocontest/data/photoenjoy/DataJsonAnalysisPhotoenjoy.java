package com.gxzzb.gxphotocontest.data.photoenjoy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gxzzb.gxphotocontest.bean.BeanImageContentitem;
import com.gxzzb.gxphotocontest.bean.BeanImageContent;

public class DataJsonAnalysisPhotoenjoy {
	String strResUlt;
	BeanImageContent beanImageContent;
	BeanImageContentitem beanImageContentitem;
	ArrayList< BeanImageContentitem> beanImageContentitems ; 

	public DataJsonAnalysisPhotoenjoy(String strResUlt) {
		this.strResUlt = strResUlt;
		beanImageContent = new BeanImageContent();
		beanImageContentitems = new ArrayList<BeanImageContentitem>();
		
		dataAnalysis();
	}

	public void dataAnalysis() {
		try {
			strResUlt = URLDecoder.decode(strResUlt, "GBK");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println("wo de 1111"+strResUlt);
		try {
			JSONObject jsonObject = new JSONObject(strResUlt);
			JSONArray jsonArray = jsonObject.optJSONArray("tu");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObjectimageArrayitem = jsonArray.optJSONObject(i);
				beanImageContentitem = new BeanImageContentitem();
				beanImageContentitem.setTpnum(jsonObjectimageArrayitem.optInt("tpnum"));
				beanImageContentitem.setTu(jsonObjectimageArrayitem.optString("tu"));
				beanImageContentitem.setTuid(jsonObjectimageArrayitem.optInt("tuid"));
				beanImageContentitem.setTusm(jsonObjectimageArrayitem.optString("tusm"));
				beanImageContentitems.add(beanImageContentitem);

			}

			beanImageContent.setId(jsonObject.optInt("id"));
			beanImageContent.setUser(jsonObject.optString("user"));
			beanImageContent.setSj(jsonObject.optString("sj"));
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
	
	public ArrayList< BeanImageContentitem> getArrayList(){
		return beanImageContentitems;
		
	}

}
