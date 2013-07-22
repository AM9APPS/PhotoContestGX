package com.gxzzb.gxphotocontest.ui.photouser;

import com.gxzzb.gxphotocontest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapterPhotoUser extends BaseAdapter {

	String[] data = { "ÄãºÃ£¡", "0.0", "o(¡É_¡É)o ¹þ¹þ", "1", "2", "1", "2", "1", "1",
	"2" };

	Context context;
	LayoutInflater layoutInflater;
	public ListAdapterPhotoUser(Context context){
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
	}
	public ListAdapterPhotoUser(LayoutInflater layoutInflater){
		this.layoutInflater = layoutInflater;
	}
	
	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = layoutInflater.inflate(R.layout.view_list_item, null);
		}
		TextView textView = (TextView) convertView.findViewById(R.id.textView_list_item);
		textView.setText(data[position]);
		return convertView;
	}

}
