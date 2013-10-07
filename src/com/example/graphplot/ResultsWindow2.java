package com.example.graphplot;



import java.util.Iterator;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ResultsWindow2 extends Activity{
	
	private GradeDatabase gradeDatabase;
	private Exam exam;
	private int semester;
	private Handler handler;
	private GradesAdapter adapter ;
	private TextView tSemester,SGPA;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_window_2);
		gradeDatabase=new GradeDatabase(this);
		adapter=new GradesAdapter();
		handler=new Handler();
		tSemester=(TextView)findViewById(R.id.listSemester);
		SGPA=(TextView)findViewById(R.id.listSGPA);
		Intent intent = getIntent();
		semester=intent.getIntExtra("semester",0);
		 getResults(semester);
		
	}
	
	
	private void getResults(int semester){
		
		exam=gradeDatabase.getResults(semester);
		Exam exam1=exam;
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// give news items to list view via adapter
				tSemester.setText(Integer.toString(exam.getSemester()));
				
				SGPA.setText(String.format("%.2f", exam.calculateSGPA()));
				adapter.setList(exam.getResults());
				ListView list=(ListView)findViewById(R.id.gradeList1);
				list.setAdapter(adapter);
				  
				
				
			}
	  });
	}
	
	private double calculateSGPA(){
		LinkedList<Result>results=exam.getResults();
		Iterator<Result>iter=results.listIterator();
		String grade;
		double totalCredit=0;
		double obtainedValue=0;
		double gradeCredit=0;
		Result result;
		while(iter.hasNext()){
			result=iter.next();
			grade=result.getGrade();
			//I-CA etc
			if(grade.length()==3){
				gradeCredit=0;
			}
			if(grade.length()==2){
				if(grade.charAt(0)=='A' && grade.charAt(1)=='+')
					gradeCredit=4.2;
				if(grade.charAt(0)=='A' && grade.charAt(1)=='-')
					gradeCredit=3.7;
				if(grade.charAt(0)=='B' && grade.charAt(1)=='+')
					gradeCredit=3.3;
				if(grade.charAt(0)=='B' && grade.charAt(1)=='-')
					gradeCredit=2.7;
				if(grade.charAt(0)=='C' && grade.charAt(1)=='+')
					gradeCredit=2.3;
				if(grade.charAt(0)=='C' && grade.charAt(1)=='-')
					gradeCredit=1.5;
				
			}
			else{
				if(grade.charAt(0)=='A' )
					gradeCredit=4.0;
				if(grade.charAt(0)=='B' )
					gradeCredit=3.0;
				if(grade.charAt(0)=='C' )
					gradeCredit=2.0;
				if(grade.charAt(0)=='D' )
					gradeCredit=1.0;
				if(grade.charAt(0)=='I' )
					gradeCredit=0;
				
				
			}
			obtainedValue=obtainedValue+ gradeCredit*result.getCredit();
			totalCredit=totalCredit+result.getCredit();
		}
		
		double GPA=obtainedValue/totalCredit;
		return GPA;
	}
	
	public void back(View view){
	    finish();
	}
}
