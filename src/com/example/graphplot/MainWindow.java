package com.example.graphplot;
/**
 * Description of MainWIndow
 * 
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainWindow extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
		
		public void enterResults(View view){
			
			Intent intent = new Intent(this, ResultEnterWindow.class);
			startActivity(intent);
			
		}
		
       public void editResults(View view){
			
			Intent intent = new Intent(this, ResultEditWindow.class);
			startActivity(intent);
			
		}
		
		
		public void showSGPA(View view){
			
			Intent intent = new Intent(this, SGPAWindow.class);
			startActivity(intent);
			
		}
       public void compareWithNumOfSubjects(View view){
			
			Intent intent = new Intent(this, SGPAComparsionWindow.class);
			startActivity(intent);
			
		}
		
		public void checkResults(View view){
			
			Intent intent = new Intent(this, ResultsWindow1.class);
			startActivity(intent);
			
		}
		
}
	
	
