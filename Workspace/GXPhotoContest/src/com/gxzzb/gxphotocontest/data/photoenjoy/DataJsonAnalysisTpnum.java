package com.gxzzb.gxphotocontest.data.photoenjoy;

import org.json.JSONException;
import org.json.JSONObject;

import com.gxzzb.gxphotocontest.bean.BeanTpnum;

public class DataJsonAnalysisTpnum {

	String strResUlt;
	BeanTpnum beanTpnum = new BeanTpnum();

	public DataJsonAnalysisTpnum(String strResUlt) {
		this.strResUlt = strResUlt;
		System.out.println("wo destrResUlt " + strResUlt);
		dataAnalysis();
	}

	public void dataAnalysis() {

		try {
			JSONObject jsonObject = new JSONObject(strResUlt);

			beanTpnum.setTpnum(jsonObject.optString("tpnum"));
			beanTpnum.setData(jsonObject.optString("data"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BeanTpnum getBeanTpnum() {
		return beanTpnum;

	}

}
