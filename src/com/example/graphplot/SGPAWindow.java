package com.example.graphplot;
/**
 * Description of SGPAWindow
 * 
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */

import android.app.Activity;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.WindowManager;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

public class SGPAWindow extends Activity{
	
	private XYPlot plot;
	private List<Double> SGPAS; 
	private List<Integer> numOfSubjects;
	 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.simple_xy_plot_example);
        SGPAS =new ArrayList<Double>();
    	numOfSubjects=new ArrayList<Integer>();
    	
    	///////////////////////////////////configure graph/////////////////////////
        // initialize  XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        //set up the two axis labels
        plot.setDomainLabel("Semesters");
        plot.setRangeLabel("GPA");
         // remove the decimal places:
        plot.setDomainValueFormat(new DecimalFormat("#"));
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);// set configuration for x axis
        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 0.1);// set configuration for y axis
        plot.setDomainLeftMax(0); //set left hand side end points of domain
        plot.setDomainRightMin(9);//set end points of domain
        plot.setRangeTopMin(4.2);//set top value of y as maximum GPA value is 4.2
        ////////////////////////////////end of configuration//////////////////////////
        
        getSGPAS();// get SGPA values
        
       ///////////////////////Series-SGPA///////////////////////////////////
       //give values to plot
      
       List <Integer> series1Numbers=new ArrayList<Integer>();
        for(int i=0; i< numOfSubjects.size(); i++){
        	series1Numbers.add(i+1);
        }
        
       XYSeries series2 = new SimpleXYSeries( series1Numbers,SGPAS, "Your GPA");// Set the display title of the series
 
       // Create a formatter to use for drawing a series using LineAndPointRenderer and configure it from xml:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),R.layout.line_point_formatter_with_plf2);
 
        // add a new series' to the xy plot:
        plot.addSeries(series2, series2Format);
 
       //////////////////////////end of series///////////////////////////////////////////////////////
 
        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
 
    }

    //obtain SGPA values
    void getSGPAS(){
    	Exam exam;
    	GradeDatabase gradeDatabase=new GradeDatabase(this);
    	int semester=1;
    	
    	while(true){
    		exam=gradeDatabase.getResults(semester);
    		if(!exam.getResults().isEmpty()){
    		 numOfSubjects.add(exam.getNumOfSubjects());
    		double SGPA= Math.round(exam.calculateSGPA()*10000.0)/10000.0;
    		 SGPAS.add(SGPA);
    		 semester++;
    		}
    		else{
    			break;
    		}
    		
    	}
    	
    	
    }
}
