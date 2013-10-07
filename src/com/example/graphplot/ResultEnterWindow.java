package com.example.graphplot;
/**
 * Description of SGPAComparsionWindow
 * 
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */
import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ResultEnterWindow extends Activity {
	
	private EditText moduleCode;
	private EditText grade;
	private EditText semester;
	private EditText credit;
	private LinkedList<Result> results;
	private Exam exam;
	private int Ssemester;
	private GradeDatabase gradeDatabase;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_enter_window);
		results=new LinkedList<Result>();
		gradeDatabase=new GradeDatabase(this);
	    moduleCode=(EditText)findViewById(R.id.moduleCode);
		grade=(EditText)findViewById(R.id.grade);
		semester=(EditText)findViewById(R.id.semesterNumber);
		credit=(EditText)findViewById(R.id.credit);
	}
	
	public void enter(View view){
		
	  if(moduleCode.getText().toString().isEmpty() ||  grade.getText().toString().isEmpty() || credit.getText().toString().isEmpty() ){
			 Toast toast = Toast.makeText(this,"Please Fill All The Fields", Toast.LENGTH_LONG);
				toast.show();
		 
		 }
	  else{
		  Ssemester=semester.getText().toString().charAt(0)-48;
		  String temp=credit.getText().toString();
		  double credit=0;
		  if(temp.length()==1){
			   credit=(temp.charAt(0)-48);
			}
		   if(temp.length()==3){
		       credit=(temp.charAt(0)-48)+((temp.charAt(2)-48)/10);
		    }
		   Result result=new Result(moduleCode.getText().toString(),credit, grade.getText().toString());
		   results.add(result);
		   moduleCode.setText("");
		   moduleCode.requestFocus();
		   grade.setText("");
		   this.credit.setText("");
		
		}
	}
	
	public void finish(View view){
		
	  
	  if(moduleCode.getText().toString().isEmpty() &&  grade.getText().toString().isEmpty() && credit.getText().toString().isEmpty() ){
		  exam=new Exam(Ssemester, results.size(), results);
			gradeDatabase.addResults(exam);
			
		 
		 }
	  
	  else if(semester.getText().toString().isEmpty()||moduleCode.getText().toString().isEmpty() ||  grade.getText().toString().isEmpty() || credit.getText().toString().isEmpty() ){
			 Toast toast = Toast.makeText(this,"Please Fill All The Fields", Toast.LENGTH_LONG);
			 toast.show();
		 
		 }
	  
	  else{
		    String temp=credit.getText().toString();
		    int credit=temp.charAt(0)-48;
		    Result result=new Result(moduleCode.getText().toString(),credit, grade.getText().toString());
		    results.add(result);
		    Ssemester=semester.getText().toString().charAt(0)-48;
		    exam=new Exam(Ssemester, results.size(), results);
		    gradeDatabase.addResults(exam);
		
		 }
	  finish();
		
	}

}
