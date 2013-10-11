package com.example.graphplot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResultEditWindow  extends Activity{
	
	private EditText moduleCode;
	private EditText semester;
	private EditText grade;
	private String testMessage; //testing
	private AlertDialog.Builder ad;
	
	private GradeDatabase gradeDatabase;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_edit_window);
		gradeDatabase=new GradeDatabase(this);
		testMessage="ok";
	    moduleCode=(EditText)findViewById(R.id.editModuleCodeValue);
		semester=(EditText)findViewById(R.id.editSemesterValue);
		grade=(EditText)findViewById(R.id.editGradeValue);
		
		
/////////////////Dialog -display subject is not existing///////////////////////////////////
        
		Context context =this;
		String title = "CalcGPA";
		String message = "That Subject Is Not Existing!!!!!!!!!!!";
		String button1String = "Change Module";
		String button2String = "Go Back";

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setMessage(message);


        ad.setPositiveButton(button1String,new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int arg1) {
        		//change Module
        	}
          }
        );



        ad.setNegativeButton(button2String,new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface dialog, int arg1) {
                finish();
        	}
        }
    );

        	ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
        	public void onCancel(DialogInterface dialog) {
 
        	}
        }
       );
//////////////////////////////////////////////////////////////
		
	}
	
		
	
	
	public void submit(View view){
	
		
		
		String regex1="[a-zA-Z]{2}[0-9]{4}";
		String regex2="[abcdiABCDI]";
		String regex3="[abcdiABCDI][+-]";
		int Ssemester;
	  if(semester.getText().toString().isEmpty()||moduleCode.getText().toString().isEmpty() ||  grade.getText().toString().isEmpty()  ){
		  moduleCode.requestFocus(); 
		  Toast toast = Toast.makeText(this,"Please Fill All The Fields", Toast.LENGTH_LONG);
		  toast.show();
			testMessage="Please Fill All The Fields";	
		 
		 }
	  else if(semester.getText().toString().length()>1 ){
		  semester.requestFocus();
		  Toast toast = Toast.makeText(this,"Semester should be a number between 1 -8", Toast.LENGTH_LONG);
			toast.show();
			testMessage="Semester should be a number between 1 -8";
	  }
	  else if(semester.getText().toString().equals("0") || semester.getText().toString().equals("9")){
		  semester.requestFocus();
		  Toast toast = Toast.makeText(this,"Semester should be a number between 1 -8", Toast.LENGTH_LONG);
			toast.show();
			testMessage="Semester should be a number between 1 -8";
	  }
	  
	 
	  else if(!moduleCode.getText().toString().matches(regex1)){
		  moduleCode.requestFocus();
		  Toast toast = Toast.makeText(this,"Module code is not in proper format", Toast.LENGTH_LONG);
			toast.show();
			testMessage="Module code is not in proper format";
	  }
	  else if(grade.getText().toString().length()>2){
		  grade.requestFocus();
		  Toast toast = Toast.makeText(this,"Grade is invalid", Toast.LENGTH_LONG);
		  toast.show();
		  testMessage="Grade is invalid";
	  }
	  else if(!(grade.getText().toString().matches(regex2) || grade.getText().toString().matches(regex3))){
		  grade.requestFocus();
		  Toast toast = Toast.makeText(this,"Grade is invalid", Toast.LENGTH_LONG);
		  toast.show();
		  testMessage="Grade is invalid";
	  }
	 
	  else{
		  Ssemester=semester.getText().toString().charAt(0)-48;
		  int number=gradeDatabase.updateGradeInfo(Ssemester, moduleCode.getText().toString().toUpperCase(), grade.getText().toString().toUpperCase());
		  if(number>0){
		     Toast toast = Toast.makeText(this,"Grade has been successfully edited", Toast.LENGTH_LONG);
		     toast.show();
		     testMessage="Grade has been successfully edited";
		     finish();
		  }
		  else{
			  testMessage="That Subject Is Not Existing";
			  ad.show();
			  moduleCode.requestFocus();
		  }
		 }
		
		
	}
	
    public void back(View view){
		finish();
	}
	
	//testing 
    public String getTestMessage() {
		return testMessage;
	}

}
