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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
	private AlertDialog.Builder ad;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_enter_window);
		results=new LinkedList<Result>();
		gradeDatabase=new GradeDatabase(this);
	    moduleCode=(EditText)findViewById(R.id.moduleCode);
		grade=(EditText)findViewById(R.id.grade);
		semester=(EditText)findViewById(R.id.semesterNumber);
		credit=(EditText)findViewById(R.id.credit);
		/////////////////Dialog unregister-display when user needs to unregister///////////////////////////////////
        
		Context context =this;
		String title = "UOM-Student Info System";
		
		String button1String = "Unregister";
		String button2String = "Go Back";

ad = new AlertDialog.Builder(context);
ad.setTitle(title);


ad.setPositiveButton(button1String,new DialogInterface.OnClickListener() {
public void onClick(DialogInterface dialog, int arg1) {
//unregister();
}
}
);



ad.setNegativeButton(button2String,new DialogInterface.OnClickListener(){
  public void onClick(DialogInterface dialog, int arg1) {
             // do nothing user has decided not to unregister
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
	
	public void enter(View view){
		String regex1="[a-zA-Z]{2}[0-9]{4}";
		String regex2="[abcdiABCDI]";
		String regex3="[abcdiABCDI][+-]";
	  if(semester.getText().toString().isEmpty()||moduleCode.getText().toString().isEmpty() ||  grade.getText().toString().isEmpty() || credit.getText().toString().isEmpty() ){
		  moduleCode.requestFocus(); 
		  Toast toast = Toast.makeText(this,"Please Fill All The Fields", Toast.LENGTH_LONG);
		  toast.show();
				
		 
		 }
	  else if(semester.getText().toString().length()>1 ){
		  semester.requestFocus();
		  Toast toast = Toast.makeText(this,"Semester should be a number between 1 -8", Toast.LENGTH_LONG);
			toast.show();
	  }
	  else if(semester.getText().toString().equals("0") ){
		  semester.requestFocus();
		  Toast toast = Toast.makeText(this,"Semester should be a number between 1 -8", Toast.LENGTH_LONG);
			toast.show();
	  }
	  
	 
	  else if(!moduleCode.getText().toString().matches(regex1)){
		  moduleCode.requestFocus();
		  Toast toast = Toast.makeText(this,"Module code is not in proper format", Toast.LENGTH_LONG);
			toast.show();
	  }
	  else if(grade.getText().toString().length()>2){
		  grade.requestFocus();
		  Toast toast = Toast.makeText(this,"Grade is invalid", Toast.LENGTH_LONG);
		  toast.show();
	  }
	  else if(!(grade.getText().toString().matches(regex2) || grade.getText().toString().matches(regex3))){
		  grade.requestFocus();
		  Toast toast = Toast.makeText(this,"Grade is invalid", Toast.LENGTH_LONG);
		  toast.show();
	  }
	  else if(credit.getText().toString().length()>3){
		  credit.requestFocus();
		  Toast toast = Toast.makeText(this,"Credit value is invalid", Toast.LENGTH_LONG);
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
		   
		   Result result=new Result(moduleCode.getText().toString().toUpperCase(),credit, grade.getText().toString().toUpperCase());
		   results.add(result);
		   moduleCode.setText("");
		   moduleCode.requestFocus();
		   grade.setText("");
		   this.credit.setText("");
		
		}
	}
	
	public void finish(View view){
		
		String regex1="[a-zA-Z]{2}[0-9]{4}";
		String regex2="[abcdiABCDI]";
		String regex3="[abcdiABCDI][+-]";
	  if(moduleCode.getText().toString().isEmpty() &&  grade.getText().toString().isEmpty() && credit.getText().toString().isEmpty() ){
		  exam=new Exam(Ssemester, results.size(), results);
			gradeDatabase.addResults(exam);
			 finish();
			
		 
		 }
	  else if(moduleCode.getText().toString().isEmpty() ||  grade.getText().toString().isEmpty() || credit.getText().toString().isEmpty() ){
		  moduleCode.requestFocus(); 
		  Toast toast = Toast.makeText(this,"Please Fill All The Fields", Toast.LENGTH_LONG);
		  toast.show();
				
		 
		 }
	  else if(semester.getText().toString().length()>1 ){
		  semester.requestFocus();
		  Toast toast = Toast.makeText(this,"Semester should be a number between 1 -8", Toast.LENGTH_LONG);
			toast.show();
	  }
	  
	  else if(semester.getText().toString().equals("0") ){
		  semester.requestFocus();
		  Toast toast = Toast.makeText(this,"Semester should be a number between 1 -8", Toast.LENGTH_LONG);
			toast.show();
	  }
	  else if(!moduleCode.getText().toString().matches(regex1)){
		  moduleCode.requestFocus();
		  Toast toast = Toast.makeText(this,"Module code is not in proper format", Toast.LENGTH_LONG);
			toast.show();
	  }
	  else if(grade.getText().toString().length()>2){
		  grade.requestFocus();
		  Toast toast = Toast.makeText(this,"Grade is invalid", Toast.LENGTH_LONG);
		  toast.show();
	  }
	  else if(!(grade.getText().toString().matches(regex2) || grade.getText().toString().matches(regex3))){
		  grade.requestFocus();
		  Toast toast = Toast.makeText(this,"Grade is invalid", Toast.LENGTH_LONG);
		  toast.show();
	  }
	  else if(credit.getText().toString().length()>3){
		  credit.requestFocus();
		  Toast toast = Toast.makeText(this,"Credit value is invalid", Toast.LENGTH_LONG);
		  toast.show();
	  }
	  else{
		    String temp=credit.getText().toString();
		    int credit=temp.charAt(0)-48;
		    Result result=new Result(moduleCode.getText().toString().toUpperCase(),credit, grade.getText().toString().toUpperCase());
		    results.add(result);
		    Ssemester=semester.getText().toString().charAt(0)-48;
		    exam=new Exam(Ssemester, results.size(), results);
		    gradeDatabase.addResults(exam);
		    finish();
		
		 }
	 
		
	}

}
