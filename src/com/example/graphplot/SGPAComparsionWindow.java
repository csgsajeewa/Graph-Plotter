package com.example.graphplot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

public class SGPAComparsionWindow extends Activity{
	
	private XYPlot plot;
	private GPADatabase gpaDatabase;
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
        // initialize  XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        //set up the two axis labels
        plot.setDomainLabel("Semesters");
        plot.setRangeLabel("GPA,Subject");
         // remove the decimal places:
       plot.setDomainValueFormat(new DecimalFormat("#"));
       
       
      plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);// set configuration for x axis
      plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 0.1);// set configuration for y axis
      plot.setDomainLeftMax(0); //set end points of domain
      plot.setDomainRightMin(7);//set end points of domain
      plot.setRangeTopMin(9);//set top value of y as maximum GPA value is 4.2
      getSGPAS();
      ///////////////////////first series///////////////////////////////////
      //give values to plot - Number of subjects
      
       List <Integer> series2Numbers=new ArrayList<Integer>();
        for(int i=0; i< numOfSubjects.size(); i++){
        	series2Numbers.add(i+1);
        }
        // Turn the above two arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
              series2Numbers,          // SimpleXYSeries takes a List so turn our array into a List
                numOfSubjects, 
                "Number of Subjects");                             // Set the display title of the series
 
       
 
        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.layout.line_point_formatter_with_plf1);
 
        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
     //////////////////////end of first series//////////////////////////////////////
        
     ////////////////////////second series//////////////////////////////////////////////
      //give values to plot - My GPA
       // Number[] series3Numbers = (Number[])SGPAS.toArray();
        Number[] series4Numbers = {1, 2, 3,4, 5, 6};
 
        // Turn the above two arrays into XYSeries':
        XYSeries series2 = new SimpleXYSeries(
                series2Numbers,          // SimpleXYSeries takes a List so turn our array into a List
                SGPAS, 
                "Your GPA");                             // Set the display title of the series
 
       
 
        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.layout.line_point_formatter_with_plf2);
 
        // add a new series' to the xyplot:
        plot.addSeries(series2, series2Format);
 
       //////////////////////////end///////////////////////////////////////////////////////
 
        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
 
    }

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
