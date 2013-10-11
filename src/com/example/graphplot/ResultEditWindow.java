package com.example.graphplot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ResultEditWindow  extends Activity{
	
	private EditText moduleCode;
	private EditText semester;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_edit_window);
		
		
	    moduleCode=(EditText)findViewById(R.id.editGradeValue);
		semester=(EditText)findViewById(R.id.editSemesterValue);
		enter();
	}

}
