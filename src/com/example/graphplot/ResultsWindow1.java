package com.example.graphplot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ResultsWindow1 extends Activity{
	EditText semester;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_window_1);
		semester=(EditText)findViewById(R.id.checkSemesterValue);
		 
		
	}
	
	public void check(View view){
		Intent intent = new Intent(this, ResultsWindow2.class);
		int semester=this.semester.getText().toString().charAt(0)-48;
		intent.putExtra("semester",semester);
		startActivity(intent);
		
	}
	public void back(View view){
		finish();
	}
	
}