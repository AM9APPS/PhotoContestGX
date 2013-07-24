package com.gxzzb.gxphotocontest.data.photoflow;

import com.gxzzb.gxphotocontest.bean.BeanImageitem;

public class DataJsonAnalysis {
	String strResUlt;
	BeanImageitem beanImageitem = new BeanImageitem();

	public DataJsonAnalysis(String strResUlt) {
		this.strResUlt = strResUlt;
		dataAnalysis();
	}

	public void dataAnalysis() {

	}

	public BeanImageitem getbean() {

		return beanImageitem;

	}

}
