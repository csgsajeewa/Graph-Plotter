package com.example.graphplot;
/**
 * Description of GradesAdapter
 * This is used to input items in a collection to list view
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */


import java.util.LinkedList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GradesAdapter extends BaseAdapter{
	
	private LinkedList<Result>list;

	
	public GradesAdapter () {
		
	}
	
	
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int index) {
		
		return list.get(index);
	}

	@Override
	public long getItemId(int index) {
		
		return index;
	}

	@Override
	public View getView(int index, View view,ViewGroup parent) {
		
		if (view == null) {
			
			LayoutInflater inflater =LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.grade_list, parent, false);
		}
		
		Result result=list.get(index);
		
		TextView moduleCode=(TextView)view.findViewById(R.id.listModuleCode);
		moduleCode.setTextColor(0xff000000);
		moduleCode.setText(result.getModuleCode());
		
		TextView grade=(TextView)view.findViewById(R.id.listGrade);
		grade.setTextColor(0xff000000);
		grade.setText(result.getGrade());
		
		
		TextView credit=(TextView)view.findViewById(R.id.listCredit);
		credit.setTextColor(0xff000000);
		credit.setText(Double.toString(result.getCredit()));
		
		
		
		return view;
		
	}

	public void setList(LinkedList<Result> results) {
		this.list = results;
		
	}

	
}
