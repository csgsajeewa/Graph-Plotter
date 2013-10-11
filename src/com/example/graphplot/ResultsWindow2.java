package com.example.graphplot;
/**
 * Description of SGPAComparsionWindow
 * 
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */


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
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// give news items to list view via adapter
				tSemester.setText(Integer.toString(exam.getSemester()));
				if(exam.getResults().isEmpty()){
					SGPA.setText("Results have not been entered");
				}
				else{
				   SGPA.setText(String.format("%.2f", exam.calculateSGPA()));
				}
				adapter.setList(exam.getResults());
				ListView list=(ListView)findViewById(R.id.gradeList1);
				list.setAdapter(adapter);
				  
				
				
			}
	  });
	}
	
	
	public void back(View view){
	    finish();
	}
}
