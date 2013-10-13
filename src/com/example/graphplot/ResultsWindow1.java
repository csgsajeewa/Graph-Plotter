package com.example.graphplot;
/**
 * Description of ResultEnterWindow1
 * This interface provide functionality for the user to enter semester number that 
 * he needs to check results
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResultsWindow1 extends Activity{
	private EditText semester;
	String testMessage; // for testing purposes
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_window_1);
		semester=(EditText)findViewById(R.id.checkSemesterValue);
		testMessage="ok";
		 
		
	}
	
    // calls to check results of a particular semester	
	public void check(View view){
		
		if(semester.getText().toString().length()>1 || semester.getText().toString().equals("0") || semester.getText().toString().equals("9")){
			  semester.requestFocus();
			  Toast toast = Toast.makeText(this,"Semester should be a number between 1 -8", Toast.LENGTH_LONG);
			  testMessage="Semester should be a number between 1 -8";
			  toast.show();
		}
		else if(semester.getText().toString().isEmpty()){
			semester.requestFocus();
			  Toast toast = Toast.makeText(this,"Please Fill Semester Field", Toast.LENGTH_LONG);
			  testMessage="Please Fill Semester Field";
			  toast.show();
		}
		else{
		Intent intent = new Intent(this, ResultsWindow2.class);
		int semester=this.semester.getText().toString().charAt(0)-48;
		intent.putExtra("semester",semester);
		startActivity(intent);
		}
		
	}
	public void back(View view){
		finish();
	}
	
	//testing purposes
	public String getTestMessage() {
		return testMessage;
	}
	
}